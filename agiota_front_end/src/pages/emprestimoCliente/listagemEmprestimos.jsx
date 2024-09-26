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
        { field: 'id', headerName: 'ID', width: 50 },
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
                            onClick={() => navigate(`/app/listar-parcelas/${params.row.id}`)}
                        >
                            Ver Parcelas
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
        <div className="flex flex-col items-start w-full p-4">
            {/* Adicionada margem acima do cabeçalho e botão */}
            <div className="flex justify-between items-center w-full mb-4 mt-4">
                <h1 className="text-xl font-bold">Listagem de Empréstimos</h1>
                {/* Botão de Solicitar Empréstimo para Cliente */}
                {user?.usuario?.tipo === "Cliente" && (
                    <Button
                        variant="contained"
                        color="primary"
                        className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50"
                        onClick={() => navigate('/app/solicitar-emprestimo')}
                    >
                        Solicitar Empréstimo
                    </Button>
                )}
            </div>

            <div className="h-[400px] w-full bg-white shadow-md rounded-lg mb-4">
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
