import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { DataGrid } from '@mui/x-data-grid';

function ClienteList() {
  const [clientes, setClientes] = useState([]);
  const columns = [
    { field: 'id', headerName: 'ID', width: 70 },
    { field: 'nome', headerName: 'Nome', width: 130 },
    { field: 'email', headerName: 'Email', width: 200 },
    { field: 'telefone', headerName: 'Telefone', width: 150 },
    { field: 'cpf', headerName: 'CPF', width: 130 },
    { field: 'reputacao', headerName: 'Reputação', width: 90 },
    { field: 'enderecoFormatado', headerName: 'Endereço', width: '400' },
    { field: 'profissao', headerName: 'Profissão', width: 150 },
    { field: 'localDeTrabalho', headerName: 'Local de trabalho', width: 130 },
  ];

  useEffect(() => {
    const fetchClientes = async () => {
      try {
        const user = JSON.parse(localStorage.getItem('@Auth:user'));
        const response = await axios.get('http://localhost:8080/clientes', {
          headers: {
            'Authorization': `Bearer ${user.token}`
          }
        });

        const clientesComEndereco = response.data.map(cliente => ({
          ...cliente,
          enderecoFormatado: `${cliente.endereco.logradouro}, ${cliente.endereco.numero}, ${cliente.endereco.bairro}, ${cliente.endereco.cidade} - ${cliente.endereco.uf}, ${cliente.endereco.cep}`
        }));

        setClientes(clientesComEndereco);
      } catch (error) {
        console.error('Erro ao buscar clientes:', error);
      }
    };

    fetchClientes();
  }, []);

  return (
      <div style={{ height: "auto", width: '100%' }}>
        <DataGrid
            rows={clientes}
            columns={columns}
            pageSize={5}
            rowsPerPageOptions={[5]}
        />
      </div>
  );
}

export default ClienteList;
