import axios from 'axios';

const API_URL = 'http://localhost:8080/agiotas';

export const onCreateAgiota = async (requestData) => {
    try {
        const response = await axios.post(API_URL, requestData, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        
        return response.data;
    } catch (error) {
        console.error('Erro ao criar agiota:', error.response ? error.response.data : error.message);
        throw new Error('Erro ao criar agiota');
    }
};

export const aceitarEmprestimo = async (emprestimoId, token) => {
    try {
        const response = await axios.patch(
            `http://localhost:8080/agiota/emprestimos/${emprestimoId}/aprovar`,
            {},
            {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                }
            }
        );

        return response;
    } catch (error) {
        window.alert('Erro ao buscar empréstimos: ' + error?.response?.data);
    }
}

export const rejeitarEmprestimo = async (body, emprestimoId, token) => {
    try {
        const response = await axios.patch(
            `http://localhost:8080/agiota/emprestimos/${emprestimoId}/rejeitar`,
            body,
            {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                }
            }
        );

        return response.data;
    } catch (error) {
        console.log(error);
        window.alert('Error: ' + error?.response?.data);
    }
}