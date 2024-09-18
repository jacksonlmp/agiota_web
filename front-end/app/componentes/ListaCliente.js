'use client';
import { useEffect, useState } from "react";
import { listarClientes} from "../lib/funcoes";

export default function ListaCliente () {
    const [clientes, setClientes] = useState([]);


    useEffect(()=>{
        const carregarClientes = async () => {
            listarClientes()
                .then(response => {
                    console.log("loaded")
                    setClientes(response);
                })
        };
        carregarClientes();
    }, []);



    return ( 
            <div className="bg-red-300">
                <h1>Lista de Clientes</h1>
                    <ul>
                        {
                            clientes.map(cliente => {
                                return <li key={cliente.id}> {cliente.id} - {cliente.nome}</li>
                            })
                        }
                    </ul>
            </div>
    );
}