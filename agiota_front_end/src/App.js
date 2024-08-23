import React, { useState } from 'react';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import Header from './components/Header';
import Login from './components/login';
import Register from './components/cadastro';
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

    const handleRegister = async (newUser) => {
        try {
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
        <>
            {isAuthenticated && <Header />}
            <nav>
                <Link to="/login">Login</Link> | 
                <Link to="/cadastro">Cadastro</Link>
            </nav>
            <div className="container">
                {isAuthenticated ? (
                    <Outlet />
                ) : isRegistering ? (
                    <Register 
                        onRegister={handleRegister} 
                        goToLogin={() => setIsRegistering(false)} 
                        userType={userType}
                        onUserTypeChange={handleUserTypeChange}
                    />
                ) : (
                    <Login onLogin={handleLogin} goToRegister={() => setIsRegistering(true)} />
                )}
            </div>
        </>
    );
}

export default App;