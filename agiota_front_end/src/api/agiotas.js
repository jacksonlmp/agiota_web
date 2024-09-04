import axios from 'axios';

const API_URL = 'http://localhost:8080/agiotas';

export const onCreateAgiota = async (userData, enderecoData, roleData) => {
    try {
        const user = {
            ...userData,
            endereco: enderecoData,
            ...roleData,
        };

        const response = await axios.post(API_URL, user, {
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