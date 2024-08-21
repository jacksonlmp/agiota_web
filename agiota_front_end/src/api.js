const API_URL = 'http://localhost:3000/users';

export const registerUser = async (user) => {
    const response = await fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    });
    if (!response.ok) {
        throw new Error('Erro ao registrar o usuário1');
    }
    return await response.json();
};

export const loginUser = async (email, senha) => {
    const response = await fetch(`${API_URL}?email=${email}&senha=${senha}`);
    const users = await response.json();
    if (users.length > 0) {
        return users[0]; // Retorna o usuário correspondente
    } else {
        throw new Error('Credenciais inválidas');
    }
};