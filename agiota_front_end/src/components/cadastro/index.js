import React, { useState } from 'react';
import { registerUser } from '../../api';

const Register = ({ onRegister, goToLogin }) => {
    const [step, setStep] = useState(1);
    const [role, setRole] = useState('');
    const [userData, setUserData] = useState({
        nome: '',
        email: '',
        senha: '',
        telefone: '',
        cpf: ''
    });
    const [enderecoData, setEnderecoData] = useState({
        cep: '',
        cidade: '',
        uf: '',
        logradouro: '',
        bairro: '',
        numero: ''
    });
    const [roleData, setRoleData] = useState({
        profissao: '',
        localDeTrabalho: '',
        taxaDeJuros: '',
        metodoCobranca: '',
        periodoTaxa: ''
    });

    const handleUserChange = (e) => {
        const { name, value } = e.target;
        setUserData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleEnderecoChange = (e) => {
        const { name, value } = e.target;
        setEnderecoData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleRoleChange = (e) => {
        const { name, value } = e.target;
        setRoleData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleCepBlur = async () => {
        if (enderecoData.cep.length === 8) { // Verifica se o CEP tem 8 caracteres
            try {
                const response = await fetch(`https://viacep.com.br/ws/${enderecoData.cep}/json/`);
                const data = await response.json();

                if (data.erro) {
                    alert('CEP não encontrado');
                    return;
                }

                setEnderecoData({
                    ...enderecoData,
                    logradouro: data.logradouro,
                    bairro: data.bairro,
                    cidade: data.localidade,
                    uf: data.uf
                });
            } catch (error) {
                console.error('Erro ao buscar CEP:', error);
                alert('Erro ao buscar CEP');
            }
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const newUser = {
            ...userData,
            endereco: enderecoData,
            ...(role === 'cliente' ? { ...roleData, role: 'cliente' } : { ...roleData, role: 'agiota' })
        };
        onRegister(newUser);
    };

    return (
        <div className="flex items-center justify-center min-h-screen bg-[#003b5c]">
            <div className="bg-[#002d45] shadow-lg rounded-lg px-8 pt-6 pb-8 w-full max-w-sm sm:max-w-md md:max-w-lg lg:max-w-xl xl:max-w-2xl">
                <h1 className="text-2xl sm:text-3xl font-bold text-[#ffffff] mb-6 text-center">
                    Cadastro
                </h1>
                <form onSubmit={handleSubmit}>
                    {step === 1 && (
                        <div>
                            <div className="mb-4">
                                <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="role">
                                    Tipo de Cadastro
                                </label>
                                <select
                                    id="role"
                                    name="role"
                                    value={role}
                                    onChange={(e) => setRole(e.target.value)}
                                    className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                >
                                    <option value="" disabled>Selecione</option>
                                    <option value="cliente">Cliente</option>
                                    <option value="agiota">Agiota</option>
                                </select>
                            </div>
                            <div className="flex justify-between mb-6">
                                <button
                                    type="button"
                                    onClick={() => setStep(step + 1)}
                                    className="bg-[#ffffff] hover:bg-[#e0e0e0] text-[#003b5c] font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-[#ffffff]"
                                >
                                    Próximo
                                </button>
                            </div>
                        </div>
                    )}
                    {step === 2 && (
                        <div>
                            <div className="mb-4">
                                <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="nome">
                                    Nome
                                </label>
                                <input
                                    id="nome"
                                    name="nome"
                                    type="text"
                                    value={userData.nome}
                                    onChange={handleUserChange}
                                    className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                    placeholder="Digite seu nome"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="email">
                                    E-mail
                                </label>
                                <input
                                    id="email"
                                    name="email"
                                    type="email"
                                    value={userData.email}
                                    onChange={handleUserChange}
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
                                    value={userData.senha}
                                    onChange={handleUserChange}
                                    className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] mb-3 leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                    placeholder="Escolha uma senha"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="telefone">
                                    Telefone
                                </label>
                                <input
                                    id="telefone"
                                    name="telefone"
                                    type="text"
                                    value={userData.telefone}
                                    onChange={handleUserChange}
                                    className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                    placeholder="Digite seu telefone"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="cpf">
                                    CPF
                                </label>
                                <input
                                    id="cpf"
                                    name="cpf"
                                    type="text"
                                    value={userData.cpf}
                                    onChange={handleUserChange}
                                    className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                    placeholder="Digite seu CPF"
                                />
                            </div>
                            <div className="flex justify-between mb-6">
                                <button
                                    type="button"
                                    onClick={() => setStep(step - 1)}
                                    className="bg-[#ffffff] hover:bg-[#e0e0e0] text-[#003b5c] font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-[#ffffff]"
                                >
                                    Anterior
                                </button>
                                <button
                                    type="button"
                                    onClick={() => setStep(step + 1)}
                                    className="bg-[#ffffff] hover:bg-[#e0e0e0] text-[#003b5c] font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-[#ffffff]"
                                >
                                    Próximo
                                </button>
                            </div>
                        </div>
                    )}
                    {step === 3 && (
                        <div>
                            <div className="mb-4">
                                <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="cep">
                                    CEP
                                </label>
                                <input
                                    id="cep"
                                    name="cep"
                                    type="text"
                                    value={enderecoData.cep}
                                    onChange={handleEnderecoChange}
                                    className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                    placeholder="Digite seu CEP"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="cidade">
                                    Cidade
                                </label>
                                <input
                                    id="cidade"
                                    name="cidade"
                                    type="text"
                                    value={enderecoData.cidade}
                                    onChange={handleEnderecoChange}
                                    className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                    placeholder="Digite sua cidade"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="uf">
                                    UF
                                </label>
                                <input
                                    id="uf"
                                    name="uf"
                                    type="text"
                                    value={enderecoData.uf}
                                    onChange={handleEnderecoChange}
                                    className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                    placeholder="Digite sua UF"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="logradouro">
                                    Logradouro
                                </label>
                                <input
                                    id="logradouro"
                                    name="logradouro"
                                    type="text"
                                    value={enderecoData.logradouro}
                                    onChange={handleEnderecoChange}
                                    className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                    placeholder="Digite seu logradouro"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="bairro">
                                    Bairro
                                </label>
                                <input
                                    id="bairro"
                                    name="bairro"
                                    type="text"
                                    value={enderecoData.bairro}
                                    onChange={handleEnderecoChange}
                                    className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                    placeholder="Digite seu bairro"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="numero">
                                    Número
                                </label>
                                <input
                                    id="numero"
                                    name="numero"
                                    type="text"
                                    value={enderecoData.numero}
                                    onChange={handleEnderecoChange}
                                    className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                    placeholder="Digite o número"
                                />
                            </div>
                            <div className="flex justify-between mb-6">
                                <button
                                    type="button"
                                    onClick={() => setStep(step - 1)}
                                    className="bg-[#ffffff] hover:bg-[#e0e0e0] text-[#003b5c] font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-[#ffffff]"
                                >
                                    Anterior
                                </button>
                                <button
                                    type="button"
                                    onClick={() => setStep(step + 1)}
                                    className="bg-[#ffffff] hover:bg-[#e0e0e0] text-[#003b5c] font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-[#ffffff]"
                                >
                                    Próximo
                                </button>
                            </div>
                        </div>
                    )}
                    {step === 4 && (
                        <div>
                            {role === 'cliente' ? (
                                <div>
                                    <div className="mb-4">
                                        <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="profissao">
                                            Profissão
                                        </label>
                                        <input
                                            id="profissao"
                                            name="profissao"
                                            type="text"
                                            value={roleData.profissao}
                                            onChange={handleRoleChange}
                                            className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                            placeholder="Digite sua profissão"
                                        />
                                    </div>
                                    <div className="mb-4">
                                        <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="localDeTrabalho">
                                            Local de Trabalho
                                        </label>
                                        <input
                                            id="localDeTrabalho"
                                            name="localDeTrabalho"
                                            type="text"
                                            value={roleData.localDeTrabalho}
                                            onChange={handleRoleChange}
                                            className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                            placeholder="Digite seu local de trabalho"
                                        />
                                    </div>
                                </div>
                            ) : (
                                <div>
                                    <div className="mb-4">
                                        <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="taxaDeJuros">
                                            Taxa de Juros
                                        </label>
                                        <input
                                            id="taxaDeJuros"
                                            name="taxaDeJuros"
                                            type="number"
                                            step="0.01"
                                            value={roleData.taxaDeJuros}
                                            onChange={handleRoleChange}
                                            className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                            placeholder="Digite a taxa de juros"
                                        />
                                    </div>
                                    <div className="mb-4">
                                        <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="metodoCobranca">
                                            Método de Cobrança
                                        </label>
                                        <input
                                            id="metodoCobranca"
                                            name="metodoCobranca"
                                            type="text"
                                            value={roleData.metodoCobranca}
                                            onChange={handleRoleChange}
                                            className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                            placeholder="Digite o método de cobrança"
                                        />
                                    </div>
                                    <div className="mb-4">
                                        <label className="block text-[#ffffff] text-sm font-medium mb-2" htmlFor="periodoTaxa">
                                            Período da Taxa
                                        </label>
                                        <input
                                            id="periodoTaxa"
                                            name="periodoTaxa"
                                            type="text"
                                            value={roleData.periodoTaxa}
                                            onChange={handleRoleChange}
                                            className="shadow-sm appearance-none border border-[#ffffff] rounded-md w-full py-3 px-4 text-[#ffffff] bg-[#003b5c] leading-tight focus:outline-none focus:ring-1 focus:ring-[#ffffff] focus:border-[#ffffff]"
                                            placeholder="Digite o período da taxa"
                                        />
                                    </div>
                                </div>
                            )}
                            <div className="flex justify-between mb-6">
                                <button
                                    type="button"
                                    onClick={() => setStep(step - 1)}
                                    className="bg-[#ffffff] hover:bg-[#e0e0e0] text-[#003b5c] font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-[#ffffff]"
                                >
                                    Anterior
                                </button>
                                <button
                                    type="submit"
                                    className="bg-[#ffffff] hover:bg-[#e0e0e0] text-[#003b5c] font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-[#ffffff]"
                                >
                                    Concluir
                                </button>
                            </div>
                        </div>
                    )}
                </form>
            </div>
        </div>
    );
};

export default Register;