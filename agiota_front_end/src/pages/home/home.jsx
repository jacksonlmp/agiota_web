import React from "react";
import { useNavigate } from 'react-router-dom'; // Para navegação
import { BsPerson as IconAgiota, BsCash as IconDinheiro } from "react-icons/bs"; // Alterando para ícone de dinheiro

function Home() {
    const navigate = useNavigate();

    const handleNavigate = (path) => {
        navigate(path);
    };

    return (
        <div className="flex flex-col items-center justify-center h-screen bg-gradient-to-r from-gray-100 to-gray-200 p-6">
            <h1 className="text-3xl font-bold mb-8 text-gray-800">Bem-vindo ao Sistema de Empréstimos</h1>
            <div className="flex gap-6 w-full max-w-6xl">
                <button
                    onClick={() => handleNavigate("/app/emprestimos")}
                    className="flex-grow bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-5 px-10 rounded-lg shadow-md flex items-center transition-transform transform hover:scale-105 duration-300 focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50"
                >
                    <IconDinheiro className="mr-2 w-6 h-6" /> {/* Ícone de dinheiro */}
                    Ver Empréstimos
                </button>
                <button
                    onClick={() => handleNavigate("/app/agiotas")}
                    className="flex-grow bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-5 px-10 rounded-lg shadow-md flex items-center transition-transform transform hover:scale-105 duration-300 focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50"
                >
                    <IconAgiota className="mr-2 w-6 h-6" /> {/* Ícone de pessoa */}
                    Ver Agiotas
                </button>
                <button
                    onClick={() => handleNavigate("/app/solicitar-emprestimo")}
                    className="flex-grow bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-5 px-10 rounded-lg shadow-md flex items-center transition-transform transform hover:scale-105 duration-300 focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50"
                >
                    <IconDinheiro className="mr-2 w-6 h-6" /> {/* Ícone de dinheiro */}
                    Solicitar Empréstimo
                </button>
            </div>
        </div>
    );
}

export default Home;