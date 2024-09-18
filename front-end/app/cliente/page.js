import Link from "next/link";
import ListaCliente from "../componentes/ListaCliente";
import { listarClientes } from "../lib/funcoes";

export default async function Cliente() {
  const clientes = await listarClientes();
    return (
      
      <div className="flex flex-col items-center justify-between p-24">
       <h1>Lista de Clientes</h1>
       <ul>
        {
          clientes.map(cliente => {
            return <li><Link href={`/cliente/update/${cliente.id}`}> {cliente.id} - {cliente.nome}</Link></li>
           })
        }
       </ul>

       </div>
    );
  }