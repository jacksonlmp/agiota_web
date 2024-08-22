import axios from 'axios';

const API_URL = 'http://localhost:8080';

export const registerUser = async (user) => {
  try {
    if (!user.nome || !user.email || !user.senha || !user.telefone || !user.cpf || !user.endereco) {
      throw new Error('Dados incompletos');
    }

    if (user.role === 'cliente' && (!user.profissao || !user.localDeTrabalho)) {
      throw new Error('Dados do cliente incompletos');
    }

    if (user.role === 'agiota' && (!user.taxaDeJuros || !user.metodoCobranca || !user.periodoTaxa)) {
      throw new Error('Dados do agiota incompletos');
    }

    const response = await axios.post(`${API_URL}/cadastro`, user);
    console.log('Usuário cadastrado:', response.data);
    return response.data;
  } catch (error) {
    console.error('Erro ao cadastrar:', error.response ? error.response.data : error.message);
    throw error;
  }
};

export const loginUser = async (username, password) => {
  try {
    const response = await axios.get(`${API_URL}/login`, {
      params: { username, password },
    });
    console.log('Login bem-sucedido:', response.data);
    return response.data;
  } catch (error) {
    console.error('Erro no login:', error.response ? error.response.data : error.message);
    throw error;
  }
};
