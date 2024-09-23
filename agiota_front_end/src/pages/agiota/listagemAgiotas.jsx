import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { DataGrid } from '@mui/x-data-grid';

function AgiotaList() {
  const [agiotas, setAgiotas] = useState([]);
  const columns = [
    { field: 'id', headerName: 'ID', width: 70 },
    { field: 'nome', headerName: 'Nome', width: 130 },
    { field: 'email', headerName: 'Email', width: 200 },
    { field: 'telefone', headerName: 'Telefone', width: 150 },
    { field: 'cpf', headerName: 'CPF', width: 130 },
    { field: 'reputacao', headerName: 'Reputação', width: 90 },
    { field: 'taxaDeJuros', headerName: 'Taxa de Juros', width: 110 },
    { field: 'metodoCobranca', headerName: 'Método de cobrança', width: 150 },
    { field: 'periodoTaxa', headerName: 'Período de taxa', width: 130 },
  ];

  useEffect(() => {
    const fetchAgiotas = async () => {
        const user = JSON.parse(localStorage.getItem('@Auth:user'));
          try {
            const response = await axios.get('http://localhost:8080/agiotas', {
                headers: {
                    'Authorization': `Bearer ${user.token}`
                }
            });
            setAgiotas(response.data);
        } catch (error) {
            console.error('Erro ao buscar agiotas:', error.response ? error.response.data : error.message);
        }
    };

    fetchAgiotas();
}, []);

  return (
      <div style={{ height: "auto", width: '100%' }}>
        <DataGrid
            rows={agiotas}
            columns={columns}
            pageSize={5}
            rowsPerPageOptions={[5]}
        />
      </div>
  );
}

export default AgiotaList;