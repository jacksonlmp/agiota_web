import React, { useState } from 'react';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import Header from './components/Header';
import Login from './components/login';
import Cadastro from './components/cadastro/cadastroForm';
import { onCreateAgiota } from './api/agiotas';
import { onCreateCliente } from './api/clientes';
import { onLogin } from './api/login';

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isRegistering, setIsRegistering] = useState(false);
    const [userType, setUserType] = useState('cliente'); // 'cliente' ou 'agiota'
    const navigate = useNavigate();

    const handleLogin = async (email, senha) => {
        try {
            await onLogin(email, senha);
            setIsAuthenticated(true);
            navigate('/');
        } catch (error) {
            alert('E-mail ou senha incorretos.');
        }
    };

    const handleRegister = async (userData, enderecoData, roleData) => {
        try {
            const newUser = {
                ...userData,
                endereco: enderecoData,
                ...(userType === 'cliente' ? { ...roleData, role: 'cliente' } : { ...roleData, role: 'agiota' })
            };
            if (userType === 'cliente') {
                await onCreateCliente(newUser);
            } else if (userType === 'agiota') {
                await onCreateAgiota(newUser);
            }
            alert('Cadastro realizado com sucesso!');
            setIsRegistering(false);
            navigate('/login');
        } catch (error) {
            alert('Erro ao registrar o usuário.');
        }
    };

    const handleUserTypeChange = (type) => {
        setUserType(type);
    };

    return (
        <div className="flex flex-col min-h-screen">
            {isAuthenticated && <Header />}
            <main className="flex-1 bg-gray-100 p-4">
                {isAuthenticated ? (
                    <Outlet />
                ) : isRegistering ? (
                    <Cadastro 
                        onRegister={handleRegister} 
                        goToLogin={() => setIsRegistering(false)} 
                        userType={userType}
                        onUserTypeChange={handleUserTypeChange}
                    />
                ) : (
                    <Login onLogin={handleLogin} goToRegister={() => setIsRegistering(true)} />
                )}
            </main>
        </div>
    );
}

export default App;