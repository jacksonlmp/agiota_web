import React, { useState } from 'react';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import Header from './components/Header/Header';
import Login from './components/login/FormularioLogin';
import Cadastro from './components/cadastro/Cadastro';
import { onLogin } from './api/login';

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(
        () => localStorage.getItem('isAuthenticated') === 'true'
    );
    const [isRegistering, setIsRegistering] = useState(false);
    const [userType, setUserType] = useState('cliente');
    const navigate = useNavigate();

    const handleLogin = async (email, senha) => {
        try {
            await onLogin(email, senha);
            setIsAuthenticated(true);
            localStorage.setItem('isAuthenticated', 'true');
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