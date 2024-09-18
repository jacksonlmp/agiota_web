'use client';
import { listarClientes, sayHello } from "../lib/funcoes";
import {emprestimosCliente} from "@/app/api/emprestimos";

export default function Botao () {


    return ( <button 
                className="btn btn-primary"
                onClick={() => emprestimosCliente().then((response) => {console.log(response)})}
             >
                Click me
            </button>
    );
}