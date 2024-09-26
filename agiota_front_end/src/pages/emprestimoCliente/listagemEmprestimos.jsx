import React, { useState, useEffect } from "react";
import axios from 'axios';
import { DataGrid } from "@mui/x-data-grid";
import { useNavigate } from 'react-router-dom';

function ListagemEmprestimos() {
    const [emprestimos, setEmprestimos] = useState([]);
    const user = JSON.parse(localStorage.getItem('@Auth:user'));
    const navigate = useNavigate();

    const columns = [
        { field: 'id', headerName: 'ID', width: 50 },
        { field: 'garantia', headerName: 'Garantia', width: 130 },
        { field: 'valorEmprestimo', headerName: 'Valor do Empréstimo', width: 200 },
        { field: 'agiotaId', headerName: 'ID Agiota', width: 80 },
        { field: 'dataEmprestimo', headerName: 'Data de Empréstimo', width: 250 },
        { field: 'dataDeVencimentoInicial', headerName: 'Data de Vencimento', width: 250 },
        { field: 'quantidadeParcelas', headerName: 'Nº Parcelas', width: 110 },
        { field: 'periodoParcelas', headerName: 'Periodo', width: 80 },
        {
            field: 'acoes',
            headerName: 'Ações',
            width: 150,
            renderCell: (params) => (
                <button
                    onClick={() => navigate(`/app/listar-parcelas/${params.row.id}`)}
                    style={{ 
                        backgroundColor: '#007bff',
                        color: 'white', 
                        border: 'none', 
                        borderRadius: '8px', 
                        cursor: 'pointer', 
                        display: 'flex', 
                        padding: '0px 10px',
                        fontSize: '0.8rem' 
                    }}
                >
                    Ver Parcelas
                </button>
            ),
        },
    ];

    useEffect(() => {
        const fetchEmprestimos = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/${user.usuario.tipo.toLowerCase()}/emprestimos`, {
                    headers: {
                        'Authorization': `Bearer ${user.token}`
                    }
                });
                setEmprestimos(response.data);
            } catch (error) {
                console.error('Erro ao buscar Empréstimos:', error);
            }
        };

        fetchEmprestimos();
    }, []);

    return (
        <div style={{ height: "auto", width: '100%' }}>
            <DataGrid
                rows={emprestimos}
                columns={columns}
                pageSize={5}
                rowsPerPageOptions={[5]}
                getRowId={(row) => row.id}
            />
        </div>
    );
}

export default ListagemEmprestimos;