import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const CadastroCliente = () => {
  const [cliente, setCliente] = useState({
    nome: '',
    email: '',
    senha: '',
    telefone: '',
    cpf: '',
    profissao: '',
    localDeTrabalho: '',
    endereco: {
      cep: '',
      cidade: '',
      uf: '',
      logradouro: '',
      bairro: '',
      numero: ''
    }
  });
  const [mensagemSucesso, setMensagemSucesso] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name.includes('endereco.')) {
      const [_, key] = name.split('.');
      setCliente((prevState) => ({
        ...prevState,
        endereco: {
          ...prevState.endereco,
          [key]: value
        }
      }));
    } else {
      setCliente({
        ...cliente,
        [name]: value
      });
    }
  };

  const handleCepChange = async (e) => {
    const cep = e.target.value.replace(/\D/g, '');
    setCliente((prevState) => ({
      ...prevState,
      endereco: {
        ...prevState.endereco,
        cep: e.target.value
      }
    }));

    if (cep.length === 8) {
      try {
        const response = await axios.get(`https://viacep.com.br/ws/${cep}/json/`);
        const { logradouro, bairro, localidade, uf } = response.data;
        setCliente((prevState) => ({
          ...prevState,
          endereco: {
            ...prevState.endereco,
            logradouro,
            bairro,
            cidade: localidade,
            uf
          }
        }));
      } catch (error) {
        console.error('Erro ao buscar endereço:', error);
      }
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/clientes', cliente, {
        headers: {
          'Content-Type': 'application/json'
        }
      });
      console.log('Cliente cadastrado com sucesso:', response.data);
      setMensagemSucesso('Cliente criado com sucesso! Redirecionando para login...');
      setTimeout(() => {
        navigate('/app/login');
      }, 3000); // Redireciona após 3 segundos
    } catch (error) {
      console.error('Erro ao cadastrar cliente:', error.response ? error.response.data : error.message);
    }
  };

  return (
    <div className="max-w-md mx-auto bg-white p-8 rounded-md shadow-md">
      <h2 className="text-2xl font-bold mb-4">Cadastrar Cliente</h2>
      {mensagemSucesso && (
        <div className="mb-4 p-4 text-green-800 bg-green-100 border-green-300 rounded">
          {mensagemSucesso}
        </div>
      )}
      <form onSubmit={handleSubmit}>
        <div className="mb-4">
          <label className="block text-gray-700">Nome</label>
          <input
            type="text"
            name="nome"
            value={cliente.nome}
            onChange={handleChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Email</label>
          <input
            type="email"
            name="email"
            value={cliente.email}
            onChange={handleChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Senha</label>
          <input
            type="password"
            name="senha"
            value={cliente.senha}
            onChange={handleChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Telefone</label>
          <input
            type="text"
            name="telefone"
            value={cliente.telefone}
            onChange={handleChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">CPF</label>
          <input
            type="text"
            name="cpf"
            value={cliente.cpf}
            onChange={handleChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Profissão</label>
          <input
            type="text"
            name="profissao"
            value={cliente.profissao}
            onChange={handleChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Local de Trabalho</label>
          <input
            type="text"
            name="localDeTrabalho"
            value={cliente.localDeTrabalho}
            onChange={handleChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
        </div>
        <div className="mb-4">
          <h3 className="text-lg font-semibold">Endereço</h3>
          <label className="block text-gray-700">CEP</label>
          <input
            type="text"
            name="endereco.cep"
            value={cliente.endereco.cep}
            onChange={handleCepChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
          <label className="block text-gray-700">Cidade</label>
          <input
            type="text"
            name="endereco.cidade"
            value={cliente.endereco.cidade}
            onChange={handleChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
          <label className="block text-gray-700">UF</label>
          <input
            type="text"
            name="endereco.uf"
            value={cliente.endereco.uf}
            onChange={handleChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
          <label className="block text-gray-700">Logradouro</label>
          <input
            type="text"
            name="endereco.logradouro"
            value={cliente.endereco.logradouro}
            onChange={handleChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
          <label className="block text-gray-700">Bairro</label>
          <input
            type="text"
            name="endereco.bairro"
            value={cliente.endereco.bairro}
            onChange={handleChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
          <label className="block text-gray-700">Número</label>
          <input
            type="text"
            name="endereco.numero"
            value={cliente.endereco.numero}
            onChange={handleChange}
            className="w-full mt-2 p-2 border-black rounded-md transition duration-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />
        </div>
        <button
          type="submit"
          className="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 transition-colors"
        >
          Cadastrar
        </button>
      </form>
    </div>
  );
};

export default CadastroCliente;