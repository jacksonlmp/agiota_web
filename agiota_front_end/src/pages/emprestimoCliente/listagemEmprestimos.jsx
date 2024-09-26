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
        { field: 'periodoParcelas', headerName: 'Período', width: 80 },
    ];

    const handleCreateEmprestimo = () => {
        navigate("/app/solicitar-emprestimo");
    };

    return (
        <div className="flex flex-col items-center w-full p-4">
            <div className="flex justify-between items-center w-full mb-4">
                <h1 className="text-xl font-bold">Listagem de Empréstimos</h1>
                <button
                    type="button"
                    className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50"
                    onClick={handleCreateEmprestimo}
                >
                    Criar Empréstimo
                </button>
            </div>
            <div className="h-[400px] w-full">
                <DataGrid
                    rows={emprestimos}
                    columns={columns}
                    pageSize={5}
                    rowsPerPageOptions={[5]}
                    autoHeight 
                    className="bg-white shadow-md rounded-lg"
                />
            </div>
        </div>
    );
}

export default ListagemEmprestimos;
