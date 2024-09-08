import axios from 'axios';

const API_URL = 'http://localhost:8080/login';

export const onLogin = async (email, senha) => {
    try {
        const response = await axios.post(API_URL, {
            email: email,
            senha: senha
        });

        if (response.data) {
            return response;
        } else {
            throw new Error('E-mail ou senha incorretos.');
        }
    } catch (error) {
        throw new Error('Erro ao fazer login');
    }
};