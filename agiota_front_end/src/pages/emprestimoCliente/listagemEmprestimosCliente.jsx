import React, { useState, useEffect } from "react";
import axios from 'axios';
import {DataGrid} from "@mui/x-data-grid";

function ListagemEmprestimosCliente() {
    const [emprestimos, setEmprestimos] = useState([]);
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
                const user = JSON.parse(localStorage.getItem('@Auth:user'));
                const response = await axios.get('http://localhost:8080/cliente/emprestimos', {
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
            />
        </div>
    );
}

export default ListagemEmprestimosCliente;