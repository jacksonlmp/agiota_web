import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { DataGrid } from '@mui/x-data-grid';
import { useParams } from 'react-router-dom';

const ListagemParcelas = () => {
  const { emprestimoId } = useParams(); 
  const user = JSON.parse(localStorage.getItem('@Auth:user'));
  const [parcelas, setParcelas] = useState([]);
  const [emprestimoDetalhes, setEmprestimoDetalhes] = useState(null);

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
      }
    };

    if (emprestimoId) {
      fetchParcelas();
    }
  }, [emprestimoId]);

  const formatarValor = (valor) => {
    return new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(valor);
  };

  const formatarData = (data) => {
    return new Date(data).toLocaleDateString('pt-BR', { year: 'numeric', month: 'long', day: 'numeric' });
  };

  return (
    <div style={{ padding: '20px' }}>
      {emprestimoDetalhes && (
        <div style={{ marginBottom: '20px', padding: '10px', border: '1px solid #ccc', borderRadius: '5px' }}>
          <h3 style={{ fontSize: '1.5rem', fontWeight: 'bold' }}>Detalhes do Empréstimo</h3>
          <p>ID: {emprestimoDetalhes.id}</p>
          <p>Data do Empréstimo: {formatarData(emprestimoDetalhes.dataEmprestimo)}</p>
          <p>Valor do Empréstimo: {formatarValor(emprestimoDetalhes.valorEmprestimo)}</p>
          <p>Garantia: {emprestimoDetalhes.garantia}</p>
          <p>Status: {emprestimoDetalhes.status}</p>
        </div>
      )}
      <div style={{ height: 400, width: '100%' }}>
        <DataGrid
          rows={parcelas}
          columns={columns}
          pageSize={5}
          rowsPerPageOptions={[5]}
          getRowId={(row) => row.id}
        />
      </div>
    </div>
  );
};

export default ListagemParcelas;
