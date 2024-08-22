import React, { useState } from 'react';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import Header from './components/Header';
import Login from './components/login';
import Register from './components/cadastro';
import { loginUser, registerUser } from './api';

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isRegistering, setIsRegistering] = useState(false);
    const navigate = useNavigate();

    const handleLogin = async (username, password) => {
        try {
            const user = await loginUser(username, password);
            if (user) {
                setIsAuthenticated(true);
                navigate('/');
            }
        } catch (error) {
            alert('Login falhou. Verifique suas credenciais.');
        }
    };

    const handleRegister = async (newUser) => {
        try {
            await registerUser(newUser);
            alert('Cadastro realizado com sucesso!');
            setIsRegistering(false);
            navigate('/login');
        } catch (error) {
            alert('Erro ao registrar o usuário.');

        }
    };

    return (
        <>
            {isAuthenticated && <Header />}
            <nav>
                {/* <Link to="/">Home</Link> |  */}
                <Link to="/login">Login</Link> | 
                <Link to="/cadastro">Cadastro</Link>
            </nav>
            <div className="container">
                {isAuthenticated ? (
                    <Outlet />
                ) : isRegistering ? (
                    <Register onRegister={handleRegister} goToLogin={() => setIsRegistering(false)} />
                ) : (
                    <Login onLogin={handleLogin} goToRegister={() => setIsRegistering(true)} />
                )}
            </div>
        </>
    );
}

export default App;