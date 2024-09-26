import React, { useState, useEffect } from "react";
import axios from 'axios';
import { DataGrid } from "@mui/x-data-grid";
import { useNavigate } from 'react-router-dom'; // Para navegação

function ListagemEmprestimos() {
    const [emprestimos, setEmprestimos] = useState([]);
    const user = JSON.parse(localStorage.getItem('@Auth:user'));
    const navigate = useNavigate(); // Hook de navegação do React Router

    const columns = [
        { field: 'id', headerName: 'ID', width: 50 },
        { field: 'garantia', headerName: 'Garantia', width: 130 },
        { field: 'valorEmprestimo', headerName: 'Valor do Empréstimo', width: 200 },
        { field: 'agiotaId', headerName: 'ID Agiota', width: 80 },
        { field: 'dataEmprestimo', headerName: 'Data de Empréstimo', width: 250 },
        { field: 'dataDeVencimentoInicial', headerName: 'Data de Vencimento', width: 250 },
        { field: 'quantidadeParcelas', headerName: 'Nº Parcelas', width: 110 },
        { field: 'periodoParcelas', headerName: 'Periodo', width: 80 },
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

    const handleCreateEmprestimo = () => {
        navigate("/app/solicitar-emprestimo");
    };

    return (
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-start', width: '100%', padding: '20px' }}>
            <div style={{ width: '100%', display: 'flex', justifyContent: 'flex-start', marginBottom: '20px' }}>
                <button
                    type="button"
                    className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50"
                    onClick={handleCreateEmprestimo}
                >
                    Criar Empréstimo
                </button>
            </div>
            <div style={{ height: "400px", width: '100%' }}>
                <DataGrid
                    rows={emprestimos}
                    columns={columns}
                    pageSize={5}
                    rowsPerPageOptions={[5]}
                    autoHeight 
                    style={{ textAlign: 'center', width: '100%' }}
                />
            </div>
        </div>
    );
}

export default ListagemEmprestimos;
