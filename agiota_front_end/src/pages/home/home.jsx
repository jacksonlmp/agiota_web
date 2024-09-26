import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Card, CardContent } from '@mui/material';

const Home = () => {
    const user = JSON.parse(localStorage.getItem('@Auth:user'));
    const navigate = useNavigate();

    if (!user || !user.usuario) {
        navigate('/login');
        return null; 
    }

    return (
        <div className="bg-white min-h-screen p-4">
            <h1 className="text-4xl font-bold text-black mb-4">Bem-vindo ao A.G.I.O.T.A.</h1>
            <p className="text-gray-700 mb-6">Olá, {user.usuario.nome}!</p>

            {user.usuario.tipo === 'Cliente' && (
                <Card className="shadow-lg mb-4" style={{ backgroundColor: '#f5f5f5' }}>
                    <CardContent className="flex flex-col items-center">
                        <button
                            type="button"
                            className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-3 rounded-lg focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50 w-full mt-2"
                            style={{ margin: '10mm' }} // Margem de 10mm
                            onClick={() => navigate('/app/emprestimos')}
                        >
                            Ver Empréstimos
                        </button>
                        <button
                            type="button"
                            className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-3 rounded-lg focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50 w-full mt-2"
                            style={{ margin: '10mm' }} // Margem de 10mm
                            onClick={() => navigate('/app/agiotas')}
                        >
                            Ver Lista de Agiotas
                        </button>
                        <button
                            type="button"
                            className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-3 rounded-lg focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50 w-full mt-2"
                            style={{ margin: '10mm' }} // Margem de 10mm
                            onClick={() => navigate('/app/solicitar-emprestimo')}
                        >
                            Solicitar Empréstimos
                        </button>
                    </CardContent>
                </Card>
            )}

            {user.usuario.tipo === 'Agiota' && (
                <Card className="shadow-lg mb-4" style={{ backgroundColor: '#f5f5f5' }}>
                    <CardContent className="flex flex-col items-center">
                        <button
                            type="button"
                            className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-3 rounded-lg focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50 w-full mt-2"
                            style={{ margin: '10mm' }} // Margem de 10mm
                            onClick={() => navigate('/app/emprestimos')}
                        >
                            Ver Empréstimos
                        </button>
                    </CardContent>
                </Card>
            )}
        </div>
    );
};

export default Home;
