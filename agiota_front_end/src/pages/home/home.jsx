import React from 'react';
import { useNavigate } from 'react-router-dom';
import { BsPerson as IconAgiota, BsCash as IconDinheiro } from "react-icons/bs";

const CardItem = ({ to, icon: Icon, title, onClick }) => (
    <div className="w-full">
        <div
            className="h-[180px] flex flex-col justify-between p-4 bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 rounded-lg shadow-md transition-transform transform hover:scale-105 duration-300"
            onClick={onClick}
        >
            <div className="flex flex-col items-center flex-grow">
                <Icon className="text-white w-16 h-16" />
                <h2 className="mt-2 text-center text-lg font-semibold text-white">{title}</h2>
            </div>
        </div>
    </div>
);

const Home = () => {
    const user = JSON.parse(localStorage.getItem('@Auth:user'));
    const navigate = useNavigate();

    if (!user || !user.usuario) {
        navigate('/login');
        return null;
    }

    return (
        <div className="bg-white min-h-screen p-6">
            <h1 className="text-3xl font-bold text-gray-800 mb-8 text-center">Bem-vindo ao A.G.I.O.T.A.</h1>
            <p className="text-gray-700 mb-6 text-center">Olá, {user.usuario.nome}!</p>

            <div className="flex gap-6 w-full max-w-4xl mx-auto">
                {user.usuario.tipo === 'Cliente' && (
                    <>
                        <CardItem
                            to="/app/emprestimos"
                            icon={IconDinheiro}
                            title="Ver Empréstimos"
                            onClick={() => navigate('/app/emprestimos')}
                        />
                        <CardItem
                            to="/app/agiotas"
                            icon={IconAgiota}
                            title="Ver Lista de Agiotas"
                            onClick={() => navigate('/app/agiotas')}
                        />
                        <CardItem
                            to="/app/solicitar-emprestimo"
                            icon={IconDinheiro}
                            title="Solicitar Empréstimos"
                            onClick={() => navigate('/app/solicitar-emprestimo')}
                        />
                    </>
                )}

                {user.usuario.tipo === 'Agiota' && (
                    <CardItem
                        to="/app/emprestimos"
                        icon={IconDinheiro}
                        title="Ver Empréstimos"
                        onClick={() => navigate('/app/emprestimos')}
                    />
                )}
            </div>
        </div>
    );
};

export default Home;
