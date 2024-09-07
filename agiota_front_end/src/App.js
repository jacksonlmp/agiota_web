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
    const [userType, setUserType] = useState('cliente');
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

    return (
        <div>
            {isAuthenticated && <Header />}
            <main>
                {isAuthenticated ? (
                    <Outlet />
                ) : isRegistering ? (
                    <Cadastro goToLogin={() => setIsRegistering(false)}  />
                ) : (
                    <Login onLogin={handleLogin} goToRegister={() => setIsRegistering(true)} />
                )}
            </main>
        </div>
    );
}

export default App;