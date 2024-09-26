import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { DataGrid } from '@mui/x-data-grid';
import { useParams, useLocation } from 'react-router-dom';
import { Button } from '@mui/material';
import AvaliacaoModal from "./components/avaliacaoModal";

const ListagemParcelas = () => {
  const { emprestimoId } = useParams(); 
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const agiotaId = queryParams.get('agiota_id');
  const clienteId = queryParams.get('cliente_id');
  const user = JSON.parse(localStorage.getItem('@Auth:user'));
  const [parcelas, setParcelas] = useState([]);
  const [emprestimoDetalhes, setEmprestimoDetalhes] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [idAvaliado, setIdAvaliado] = useState(null);

  const columns = [
    { field: 'id', headerName: 'ID', width: 70 },
    { field: 'dataVencimento', headerName: 'Data de Vencimento', width: 240 },
    { field: 'valor', headerName: 'Valor', width: 130 }
  ];

  useEffect(() => {
    const fetchParcelas = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/parcelas/emprestimo/${emprestimoId}`, {
          headers: {
            'Authorization': `Bearer ${user?.token}`
          }
        });

        if (response.data.length > 0) {
          setEmprestimoDetalhes(response.data[0].emprestimo);
        }

        setParcelas(response.data);
      } catch (error) {
        console.error('Erro ao buscar parcelas:', error.response ? error.response.data : error.message);
      } finally {
        setIsLoading(false);
      }
    };

    if (emprestimoId) {
      fetchParcelas();
    }
  }, [emprestimoId]);

  const formatarValor = (valor) => {
    return new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(valor);
  };

  const handleAvaliar = () => {
    if (user.usuario.tipo === "Cliente") {
      setIdAvaliado(agiotaId); 
    } else if (user.usuario.tipo === "Agiota") {
      setIdAvaliado(clienteId); 
    }
    setIsModalOpen(true);
  };

  return (
    <div style={{ padding: '20px' }}>
      {emprestimoDetalhes && (
        <div style={{ marginBottom: '20px', padding: '10px', border: '1px solid #ccc', borderRadius: '5px' }}>
          <h3 style={{ fontSize: '1.5rem', fontWeight: 'bold' }}>Detalhes do Empréstimo</h3>
          <p>ID: {emprestimoDetalhes.id}</p>
          <p>Data do Empréstimo: {emprestimoDetalhes.dataEmprestimo}</p>
          <p>Valor do Empréstimo: {formatarValor(emprestimoDetalhes.valorEmprestimo)}</p>
          <p>Garantia: {emprestimoDetalhes.garantia}</p>
          <p>Status: {emprestimoDetalhes.status}</p>
          <Button
            variant="contained"
            color="primary"
            onClick={handleAvaliar}
            style={{ marginTop: '10px' }}
          >
            Avaliar {user.usuario.tipo === "Cliente" ? "Agiota" : "Cliente"}
          </Button>
        </div>
      )}
      <div style={{ height: 400, width: '100%' }}>
        {isLoading ? (
          <p style={{ textAlign: 'center' }}>Carregando parcelas...</p>
        ) : parcelas.length > 0 ? (
          <DataGrid
            rows={parcelas}
            columns={columns}
            pageSize={5}
            rowsPerPageOptions={[5]}
            getRowId={(row) => row.id}
          />
        ) : (
          <p style={{ textAlign: 'center', color: 'red', fontWeight: 'bold' }}>Não há parcelas a serem exibidas.</p>
        )}
      </div>
      <AvaliacaoModal
        isOpen={isModalOpen}
        closeModal={() => setIsModalOpen(false)}
        idAvaliado={idAvaliado}
      />
    </div>
  );
};

export default ListagemParcelas;