import React, { useState } from 'react';
import { onLogin } from '../../api/login';


const Login = ({ onLogin, goToRegister }) => {
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handleSenhaChange = (e) => {
        setSenha(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await onLogin(email, senha);
        } catch (error) {
            alert('Login falhou. E-mail ou senha incorretos..');
        }
    };

    return (
        <div className="flex items-center justify-center min-h-screen bg-[#003b5c]">
            <div className="bg-[#002d45] shadow-lg rounded-lg px-8 pt-6 pb-8 w-full max-w-sm sm:max-w-md md:max-w-lg lg:max-w-xl xl:max-w-2xl">
                <h1 className="text-2xl sm:text-3xl font-bold text-[#ffffff] mb-6 text-center">
                    Login
                </h1>
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="email">
                            E-mail
                        </label>
                        <input
                            id="email"
                            name="email"
                            type="email"
                            value={email}
                            onChange={handleEmailChange}
                            className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                            placeholder="Digite seu e-mail"
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="senha">
                            Senha
                        </label>
                        <input
                            id="senha"
                            name="senha"
                            type="password"
                            value={senha}
                            onChange={handleSenhaChange}
                            className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] mb-3 leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                            placeholder="Digite sua senha"
                        />
                    </div>
                    <div className="flex justify-center mb-6">
                        <button
                            type="button"
                            onClick={handleSubmit}
                            className="bg-[#ffffff] hover:bg-[#e0e0e0] text-[#003b5c] font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-[#ffffff]"
                        >
                            Entrar
                        </button>
                    </div>
                    <div className="text-center">
                        <button
                        type="button"
                        onClick={goToRegister}
                        className="text-[#ffffff] hover:text-[#e0e0e0] font-semibold underline"
                        >
                            Não tem uma conta? Cadastre-se
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Login;