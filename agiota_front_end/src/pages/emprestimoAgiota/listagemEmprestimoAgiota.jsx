import React, { useState, useEffect } from "react";
import axios from 'axios';
import {DataGrid} from "@mui/x-data-grid";

function ListagemEmprestimoAgiota() {
    const [emprestimos, setEmprestimos] = useState([]);
    const columns = [
        { field: 'id', headerName: 'ID', width: 50 },
        //TODO - Verificar com os meninos problema no BACK-END da listagem
        //Error 404 - Not Allowed

    ];

    useEffect(() => {
        const fetchEmprestimos = async () => {
            try {
                const response = await axios.get('http://localhost:8080/agiota/emprestimos');
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