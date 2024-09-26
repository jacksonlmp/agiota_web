import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function SolicitarEmprestimo() {
  const [emprestimo, setEmprestimo] = useState({
    clienteId: '',
    agiotaId: '',
    dataEmprestimo: '',
    dataDeVencimentoInicial: '',
    valorEmprestimo: '',
    garantia: '',
    quantidadeParcelas: '',
    periodoParcelas: ''
  });
  const [agiotas, setAgiotas] = useState([]); // Estado para a lista de agiotas
  const navigate = useNavigate();

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('@Auth:user'));
    if (user) {
      setEmprestimo((prev) => ({
        ...prev,
        clienteId: user.usuario.id,
        dataEmprestimo: new Date().toISOString().split('T')[0],
      }));
    }

    // Chama a função para buscar agiotas
    fetchAgiotas();
  }, []);

  const fetchAgiotas = async () => {
    try {
      const response = await axios.get('http://localhost:8080/agiotas'); // URL da sua API
      setAgiotas(response.data);
    } catch (error) {
      console.error('Erro ao buscar agiotas:', error.response ? error.response.data : error.message);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (['valorEmprestimo', 'quantidadeParcelas'].includes(name) && isNaN(value)) return;
    setEmprestimo((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const user = JSON.parse(localStorage.getItem('@Auth:user'));

    try {
      const response = await axios.post('http://localhost:8080/cliente/emprestimos', emprestimo, {
        headers: {
          'Authorization': `Bearer ${user.token}`,
          'Content-Type': 'application/json',
        },
      });
      console.log('Empréstimo solicitado com sucesso:', response.data);
    } catch (error) {
      console.error('Erro ao solicitar empréstimo:', error.response ? error.response.data : error.message);
    }
  };

  return (
    <div className="flex flex-col items-center p-8 rounded-lg shadow-lg bg-white w-full max-w-3xl mx-auto mt-8">
      <h2 className="mb-5 text-gray-800 text-2xl font-semibold">Solicitar Empréstimo</h2>
      <form onSubmit={handleSubmit} className="flex flex-col w-full">
        <div className="grid grid-cols-2 gap-4 mb-5">
          <InputField
            label="ID do Cliente:"
            type="number"
            name="clienteId"
            value={emprestimo.clienteId}
            readOnly
          />
          <div className="flex-grow">
            <label className="mb-1 font-semibold">ID do Agiota:</label>
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
                  {agiota.nome} {/* Substitua 'nome' pelo campo correto que representa o nome do agiota */}
                </option>
              ))}
            </select>
          </div>
        </div>
        {/* ... O restante do formulário continua aqui ... */}
      </form>
    </div>
  );
}

const InputField = ({ label, type, name, value, onChange, required, readOnly }) => (
  <div className="flex-grow">
    <label className="mb-1 font-semibold">{label}</label>
    <input
      type={type}
      name={name}
      value={value}
      onChange={onChange}
      required={required}
      readOnly={readOnly}
      className="w-full p-3 border rounded-md"
    />
  </div>
);

export default SolicitarEmprestimo;