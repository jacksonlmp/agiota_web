import { useState, useEffect } from "react";
import axios from 'axios';

function Emprestimos() {
    const [emprestimos, setEmprestimos] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchEmprestimos = async () => {
            setIsLoading(true);
            try {
                const response = await axios.get("http://localhost:8080/emprestimos");
                setEmprestimos(response.data);
            } catch (error) {
                setError(error.message);
            } finally {
                setIsLoading(false);
            }
        };

        fetchEmprestimos();
    }, []);

    return (
        <div>
            {isLoading ? (
                <p>Carregando...</p>
            ) : error ? (
                <p>Erro ao carregar os empréstimos: {error}</p>
            ) : (
                <div>
                    <h2>Empréstimos</h2>
                    <ul>
                        {emprestimos.map((emprestimo) => (
                            <li key={emprestimo.id}>
                                <p>{emprestimo.garantia}</p>
                                <p>{emprestimo.valorEmprestimo}</p>
                                <p>{emprestimo.status}</p>
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}

export default Emprestimos;