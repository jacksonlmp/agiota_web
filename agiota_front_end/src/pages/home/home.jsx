import React from "react";
import { useNavigate, Link } from 'react-router-dom';
import { BsPerson as IconAgiota, BsCash as IconDinheiro } from "react-icons/bs";

const CardItem = ({ to, icon: Icon, title }) => (
    <Link to={to} className="w-full">
        <div className="h-[180px] flex flex-col justify-between p-4 bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 rounded-lg shadow-md transition-transform transform hover:scale-105 duration-300">
            <div className="flex flex-col items-center flex-grow">
                <Icon className="text-white w-16 h-16" />
                <h2 className="mt-2 text-center text-lg font-semibold text-white">{title}</h2>
            </div>
        </div>
    </Link>
);

function Home() {
    const navigate = useNavigate();

    return (
        <div className="flex flex-col items-center justify-start h-screen bg-white p-6">
            {/* Título posicionado no topo */}
            <h1 className="text-3xl font-bold mb-8 text-gray-800 text-center mt-4">Bem-vindo ao Sistema de Empréstimos</h1>
            <div className="flex gap-6 w-full max-w-4xl mt-4">
                <CardItem
                    to="/app/emprestimos"
                    icon={IconDinheiro}
                    title="Ver Empréstimos"
                />
                <CardItem
                    to="/app/agiotas"
                    icon={IconAgiota}
                    title="Ver Agiotas"
                />
                <CardItem
                    to="/app/solicitar-emprestimo"
                    icon={IconDinheiro}
                    title="Solicitar Empréstimo"
                />
            </div>
        </div>
    );
}

export default Home;
