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
        let mensagemErro;

        console.log(error.response);

        if (error?.response?.data?.erro?.mensagem) {
            // Maior que o valor da parcela
            mensagemErro = error.response.data.erro.mensagem;
        } else if (error?.response?.data && error.response.data.length > 0) {
            // Valor negativo
            mensagemErro = 'Valor do pagamento ' + error?.response?.data[0]?.erro;
        } else {
            mensagemErro = 'Erro desconhecido';
        }

        window.alert('Erro: ' + mensagemErro);
    }
}