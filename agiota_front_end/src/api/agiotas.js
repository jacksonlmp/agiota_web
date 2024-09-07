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