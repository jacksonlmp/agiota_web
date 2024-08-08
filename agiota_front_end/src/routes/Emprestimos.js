import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

function Emprestimos() {
    const [ emprestimos, setEmprestimos ] = useState([]);

    const getPosts = async () => {
        try {
            const response = await axios.get(
                "http://localhost:8080/emprestimos",
                {
                    params: {
                        clienteId: 1
                    }
                }
            )

            const data = response.data;

            console.log(data);

            return data;
        } catch(error) {
            console.error("Error: " + error.message)
        }
    }

    useEffect(() => {
        getPosts().then(data => setEmprestimos(data));
    }, []);

    return (
        <>
            <h1>Empréstimos</h1>
            {emprestimos?.length > 0 && (
                emprestimos.map((emprestimo) => (
                    <div className="container" key={emprestimo.id}>
                        <a href="#"
                           className="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700">
                            <p>{emprestimo.id}</p>
                            <p>{emprestimo.garantia}</p>
                            <p>{emprestimo.valorEmprestimo}</p>
                            <p>{emprestimo.status}</p>
                        </a>
                    </div>
                ))
            )}
        </>
    );
}

export default Emprestimos;
