import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const InputField = ({ label, type, name, value, onChange, required, readOnly, placeholder }) => (
  <div className="flex-grow">
    <label className="mb-1 font-semibold">{label}</label>
    <input
      type={type} name={name} value={value} onChange={onChange} required={required} readOnly={readOnly} placeholder={placeholder} className="w-full p-3 border rounded-md"
    />
  </div>
);

const SolicitarEmprestimo = () => {
  const [emprestimo, setEmprestimo] = useState({
    agiotaId: '',
    dataEmprestimo: '',
    dataDeVencimentoInicial: '',
    valorEmprestimo: '',
    garantia: '',
    quantidadeParcelas: '',
    periodoParcelas: ''
  });

  const [agiotas, setAgiotas] = useState([]);
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem('@Auth:user'));

  useEffect(() => {
    fetchAgiotas();
    const hoje = new Date().toISOString().split('T')[0];
    setEmprestimo(prev => ({ ...prev, dataEmprestimo: hoje }));
  }, []);

  const fetchAgiotas = async () => {
    try {
      const response = await axios.get('http://localhost:8080/agiotas', {
        headers: {
          'Authorization': `Bearer ${user.token}`
        }
      });
      setAgiotas(response.data);
    } catch (error) {
      console.error('Erro ao buscar agiotas:', error.response ? error.response.data : error.message);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if ((name === 'valorEmprestimo' || name === 'quantidadeParcelas') && isNaN(value)) return;
    setEmprestimo(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/cliente/emprestimos', emprestimo, {
        headers: {
          'Authorization': `Bearer ${user.token}`,
          'Content-Type': 'application/json',
        },
      });
      console.log('Empréstimo solicitado com sucesso:', response.data);
      navigate('/app/emprestimos');
    } catch (error) {
      console.error('Erro ao solicitar empréstimo:', error.response ? error.response.data : error.message);
    }
  };

  return (
    <div className="flex flex-col items-center p-8 rounded-lg shadow-lg bg-white w-full max-w-3xl mx-auto mt-8">
      <h2 className="mb-5 text-gray-800 text-2xl font-semibold">Solicitar Empréstimo</h2>
      <form onSubmit={handleSubmit} className="flex flex-col w-full">
        <div className="grid grid-cols-2 gap-4 mb-5">
          <div className="flex-grow">
            <label className="mb-1 font-semibold">Agiota:</label>
            <select
              name="agiotaId"
              value={emprestimo.agiotaId}
              onChange={handleChange}
              required
              className="w-full p-3 border rounded-md"
            >
              <option value="">Selecione um Agiota...</option>
              {agiotas.map(agiota => (
                <option key={agiota.id} value={agiota.id}>
                  {agiota.nome}
                </option>
              ))}
            </select>
          </div>
        </div>
        <div className="grid grid-cols-2 gap-4 mb-5">
          <InputField
            label="Data de Solicitação:"
            type="date"
            name="dataSolicitacao"
            value={emprestimo.dataEmprestimo}
            onChange={handleChange}
            required
            readOnly
          />
          <InputField
            label="Data de Vencimento Inicial:"
            type="date"
            name="dataDeVencimentoInicial"
            value={emprestimo.dataDeVencimentoInicial}
            onChange={handleChange}
            required
          />
        </div>
        <div className="grid grid-cols-2 gap-4 mb-5">
          <InputField
            label="Valor do Empréstimo:"
            type="number"
            name="valorEmprestimo"
            value={emprestimo.valorEmprestimo}
            onChange={handleChange}
            required
          />
          <InputField
            label="Garantia:"
            type="text"
            name="garantia"
            value={emprestimo.garantia}
            onChange={handleChange}
            required
          />
        </div>
        <div className="grid grid-cols-2 gap-4 mb-5">
          <InputField
            label="Quantidade de Parcelas:"
            type="number"
            name="quantidadeParcelas"
            value={emprestimo.quantidadeParcelas}
            onChange={handleChange}
            required
          />
          <InputField
            label="Período das Parcelas:"
            type="number"
            name="periodoParcelas"
            value={emprestimo.periodoParcelas}
            onChange={handleChange}
            required
          />
        </div>
        <div className="flex justify-between mb-5">
          <button
            type="button"
            onClick={() => navigate('/app')}
            className="bg-gradient-to-r from-gray-300 to-gray-400 hover:from-gray-400 hover:to-gray-500 text-white font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-gray-500 focus:ring-opacity-50 w-full mr-2"
          >
            Voltar
          </button>
          <button
            type="submit"
            className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50 w-full"
          >
            Solicitar Empréstimo
          </button>
        </div>

      </form>
    </div>
  );
};

export default SolicitarEmprestimo;
