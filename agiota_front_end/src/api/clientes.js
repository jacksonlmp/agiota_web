import axios from 'axios';

const API_URL = 'http://localhost:8080/clientes';

export const onCreateCliente = async (userData) => {
    try {
        const response = await axios.post(API_URL, userData, {
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