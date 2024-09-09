import {createContext, useState} from "react";
import {onLogin} from "../api/login";
import {api} from "../api/api";
import {useNavigate} from "react-router-dom";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const navigate = useNavigate();

    const handleLogin = async ({ email, senha }) => {
        try {
            const response = await onLogin(email, senha);

            localStorage.setItem('isAuthenticated', 'true');
            localStorage.setItem('@Auth:token', response.data.token);
            localStorage.setItem('@Auth:user', JSON.stringify(response.data.usuario));
            setUser(response.data.usuario);

            console.log(response.data.usuario)
            console.log(user);

            api.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
            navigate('/');
        } catch (error) {
            alert('E-mail ou senha incorretos.');
        }
    };

    return <AuthContext.Provider value={{ user, handleLogin }} >
        {children}
    </AuthContext.Provider>;
}