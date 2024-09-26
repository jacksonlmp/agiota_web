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
        { field: 'metodoCobranca', headerName: 'Método de Cobrança', width: 150 },
        { field: 'periodoTaxa', headerName: 'Período de Taxa', width: 130 },
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
        <div className="flex flex-col items-start w-full p-4"> 
            <h1 className="text-xl font-bold mb-4">Listagem de Agiotas</h1>
            <div className="h-[400px] w-full bg-white shadow-md rounded-lg">
                <DataGrid
                    rows={agiotas}
                    columns={columns}
                    pageSize={5}
                    rowsPerPageOptions={[5]}
                    autoHeight
                />
            </div>
        </div>
    );
}

export default AgiotaList;
