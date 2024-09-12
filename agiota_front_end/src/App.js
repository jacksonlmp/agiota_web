import React, {useContext, useState} from 'react';
import { Outlet } from 'react-router-dom';
import Header from './components/Header';
import Login from './components/login';
import Cadastro from './components/cadastro/cadastroForm';
import {AuthContext, AuthProvider} from "./context/auth";
import kc from "./keycloack/kc";

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(
        () => kc.authenticated
    );

    console.log(kc.authenticated)

    const [isRegistering, setIsRegistering] = useState(false);

    return (
        <AuthProvider>
            <div>
                {isAuthenticated && <Header/>}
                <main>
                    {isAuthenticated ? (
                        <Outlet/>
                    ) : isRegistering ? (
                        <Cadastro goToLogin={() => setIsRegistering(false)}/>
                    ) : (
                        <Login goToRegister={() => setIsRegistering(true)}/>
                    )}
                </main>
            </div>
        </AuthProvider>
    );
}

export default App;