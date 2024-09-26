import axios from 'axios';

const API_URL = 'http://localhost:8080/clientes';

export const onCreateCliente = async (requestData) => {
    try {
        const response = await axios.post(API_URL, requestData, {
            headers: {
                'Content-Type': 'application/json'
            }
        });

        return response.data;
    } catch (error) {
        console.error('Erro ao criar cliente:', error.response ? error.response.data : error.message);
        throw error;
    }
};

export const pagarParcela = async (requestData, token) => {
    try {
        const response = await axios.post(
            `http://localhost:8080/transacoes`,
            requestData,
            {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                }
            }
        );

        return response.data;
    } catch (error) {
        window.alert('Erro: ' + error?.response?.data?.erro?.mensagem);
    }
}