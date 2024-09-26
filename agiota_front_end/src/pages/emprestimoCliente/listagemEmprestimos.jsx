import React, { useEffect, useState } from "react";
import { DataGrid } from "@mui/x-data-grid";
import axios from "axios";
import AceitarModal from "./components/aceitarModal";
import { Button } from "@mui/material";
import RejeitarModal from "./components/rejeitarModal";
import { useNavigate } from 'react-router-dom';

function ListagemEmprestimos() {
    const [emprestimos, setEmprestimos] = useState([]);
    const user = JSON.parse(localStorage.getItem('@Auth:user'));
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isRejeitarModalOpen, setisRejeitarModalOpen] = useState(false);
    const [selectedRecord, setSelectedRecord] = useState(null);
    const navigate = useNavigate();

    const columns = [
        { field: 'id', headerName: 'ID Empréstimo', width: 100 },
        { field: 'garantia', headerName: 'Garantia', width: 130 },
        { field: 'valorEmprestimo', headerName: 'Valor do Empréstimo', width: 200 },
        { field: 'status', headerName: 'Situação do Empréstimo', width: 220 },
        { field: 'dataEmprestimo', headerName: 'Data de Empréstimo', width: 250 },
        { field: 'dataDeVencimentoInicial', headerName: 'Data de Vencimento', width: 250 },
        { field: 'quantidadeParcelas', headerName: 'Nº Parcelas', width: 110 },
        { field: 'periodoParcelas', headerName: 'Periodo', width: 80 },
        {
            field: 'actions',
            headerName: 'Ações',
            width: 250,
            renderCell: (params) => (
                <div style={{ display: 'flex', gap: '10px', justifyContent: 'center' }}>
                    {user?.usuario?.tipo === "Agiota" && (
                        <>
                            {params.row.status === "AGUARDANDO_APROVACAO" && (
                                <>
                                    <Button
                                        variant="contained"
                                        color="primary"
                                        size="small"
                                        onClick={async () => {
                                            setSelectedRecord(params.row);
                                            setIsModalOpen(true);
                                        }}
                                    >
                                        Aceitar
                                    </Button>
                                    <Button
                                        variant="contained"
                                        color="warning"
                                        size="small"
                                        onClick={() => {
                                            setSelectedRecord(params.row);
                                            setisRejeitarModalOpen(true);
                                        }}
                                    >
                                        Rejeitar
                                    </Button>
                                </>
                            )}
                        </>
                    )}
                    {params.row.status === "APROVADO" && (
                                <Button
                                    variant="contained"
                                    color="secondary"
                                    size="small"
                                    onClick={() => navigate(`/app/listar-parcelas/${params.row.id}?agiota_id=${params.row.agiota_id}&cliente_id=${params.row.cliente_id}`)}
                                >
                                    Detalhes do Empréstimo
                                </Button>
                            )}    
                </div>
            )
        }
    ];

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

    useEffect(() => {
        fetchEmprestimos();
    }, []);

    return (
        <div style={{ display: 'flex', justifyContent: 'center', paddingTop: '20px' }}>
            <div style={{ width: '80%' }}>
                <DataGrid
                    rows={emprestimos}
                    columns={columns}
                    pageSize={5}
                    rowsPerPageOptions={[5]}
                    autoHeight
                />
            </div>

            <AceitarModal
                isOpen={isModalOpen}
                closeModal={() => setIsModalOpen(false)}
                record={selectedRecord}
                user={user}
                refreshList={() => fetchEmprestimos()}
            />
            <RejeitarModal
                isOpen={isRejeitarModalOpen}
                closeModal={() => setisRejeitarModalOpen(false)}
                record={selectedRecord}
                user={user}
                refreshList={() => fetchEmprestimos()}
            />
        </div>
    );
}

export default ListagemEmprestimos;
