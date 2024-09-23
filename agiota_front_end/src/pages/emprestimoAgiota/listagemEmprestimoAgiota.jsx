import React, { useState, useEffect } from "react";
import axios from 'axios';
import {DataGrid} from "@mui/x-data-grid";

function ListagemEmprestimoAgiota() {
    const [emprestimos, setEmprestimos] = useState([]);
    const columns = [
        { field: 'id', headerName: 'ID', width: 50 },
        { field: 'dataEmprestimo', headerName: 'Data de Empréstimo', width: 230 },
        { field: 'valorEmprestimo', headerName: 'Valor do Empréstimo', width: 150 },
        { field: 'dataDeVencimentoInicial', headerName: 'Data de Vencimento', width: 150 },
        { field: 'garantia', headerName: 'Garantia', width: 100 },
        { field: 'quantidadeParcelas', headerName: 'Nº Parcelas', width: 100 },
        { field: 'periodoParcelas', headerName: 'Périodo de Parcelas', width: 150 },
        { field: 'taxaJuros', headerName: 'Taxa de Juros', width: 120 },
        { field: 'motivoRecusa', headerName: 'Motivo de Recusa', width: 140 },
        { field: 'status', headerName: 'Status', width: 100 },
    ];

    useEffect(() => {
        const fetchEmprestimos = async () => {
            try {
                const user = JSON.parse(localStorage.getItem('@Auth:user'));
                const response = await axios.get('http://localhost:8080/agiota/emprestimos', {
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

export default ListagemEmprestimoAgiota;