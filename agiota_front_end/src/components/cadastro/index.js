import React, { useState } from 'react';

const Register = ({ onRegister, goToLogin }) => {
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        const newUser = { email, senha };
        onRegister(newUser);
    };

    return (
        <div className="flex items-center justify-center min-h-screen bg-[#003b5c]">
            <div className="bg-[#002d45] shadow-lg rounded-lg px-8 pt-6 pb-8 w-full max-w-sm sm:max-w-md md:max-w-lg lg:max-w-xl xl:max-w-2xl">
                <h1 className="text-2xl sm:text-3xl font-bold text-[#ffffff] mb-6 text-center">
                    Cadastro
                </h1>
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="email">
                            E-mail
                        </label>
                        <input 
                            id="email"
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                            placeholder="Digite seu e-mail"
                        />
                    </div>
                    <div className="mb-6">
                        <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="senha">
                            Senha
                        </label>
                        <input 
                            id="senha"
                            type="password"
                            value={senha}
                            onChange={(e) => setSenha(e.target.value)}
                            className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] mb-3 leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                            placeholder="Escolha uma senha"
                        />
                    </div>
                    <div className="flex justify-center mb-6">
                        <button 
                            type="submit" 
                            className="bg-[#ffffff] hover:bg-[#e0e0e0] text-[#003b5c] font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-[#ffffff]"
                        >
                            Cadastrar-se
                        </button>
                    </div>
                    <div className="text-center">
                        <p className="text-[#ffffff]">
                            Já tem uma conta?{' '}
                            <button 
                                type="button" 
                                onClick={goToLogin}
                                className="text-[#ffffff] hover:text-[#e0e0e0] underline"
                            >
                                Entrar
                            </button>
                        </p>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Register;
