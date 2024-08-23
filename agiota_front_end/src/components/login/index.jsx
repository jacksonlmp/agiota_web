import React, { useState } from 'react';
import { FiMail, FiLock } from 'react-icons/fi';
import { onLogin, goToRegister } from '../../api/login';

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
            alert('Login falhou. E-mail ou senha incorretos.');
        }
    };

    return (
        <div className="flex items-center justify-center min-h-screen bg-[#0f0f0f]">
            <div className="bg-[#141414] rounded-2xl shadow-lg p-8 w-full max-w-md">
                <h2 className="text-center text-2xl font-semibold text-white mb-6">
                    Login
                </h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <Input
                            label="E-mail"
                            icon={FiMail}
                            type="email"
                            value={email}
                            onChange={handleEmailChange}
                            placeholder="Digite seu e-mail"
                        />
                    </div>
                    <div className="mb-4">
                        <Input
                            label="Senha"
                            icon={FiLock}
                            type="password"
                            value={senha}
                            onChange={handleSenhaChange}
                            placeholder="Digite sua senha"
                        />
                    </div>
                    <div className="flex justify-center mb-6">
                        <button
                            type="submit"
                            onClick={handleSubmit}
                            className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50 w-full"
                        >
                            Entrar
                        </button>
                    </div>
                    <div className="text-center">
                        <button
                            type="button"
                            onClick={goToRegister}
                            className="text-[#b5b5b5] text-sm underline"
                        >
                            Não tem uma conta? Cadastre-se
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

const Input = ({ label, icon: Icon, type, ...rest }) => {
    const [isFocus, setIsFocus] = useState(false);
    const inputClass = `w-[80%] flex-1 items-center border-none outline-none bg-transparent text-[10pt] text-white placeholder:text-[#9CA3AF] no-spin`;

    return (
        <div className="w-full text-[11pt] text-left">
            <div className="w-full mb-1 pl-4 text-[#9CA3AF] font-bold whitespace-nowrap overflow-ellipsis overflow-hidden">
                {label}
            </div>

            <section
                className={`w-full h-[2.5rem] flex items-center p-[.5rem] transition-all bg-[#111112] rounded-[.5rem] px-3 ${
                    isFocus && "custom-shadow"
                }`}
            >
                {Icon && (
                    <Icon className="text-[14pt] text-white mr-2" />
                )}

                <input
                    type={type}
                    {...rest}
                    className={inputClass}
                    onFocus={() => setIsFocus(true)}
                    onBlur={() => setIsFocus(false)}
                />
            </section>
        </div>
    );
};

export default Login;