import React from 'react';
import ReactDOM from 'react-dom/client';
import './styles/index.css';
import App from './App';
import Login from './components/login/FormularioLogin';
import Cadastro from './components/cadastro/Cadastro';
import { createBrowserRouter, RouterProvider, Navigate } from 'react-router-dom';
import ListagemEmprestimos from "./pages/emprestimoCliente/listagemEmprestimos";
import Agiotas from "./pages/agiota/listagemAgiotas";
import Clientes from "./pages/cliente/listagemClientes";
import CadastroCliente from "./pages/cliente/cadastroCliente";
import ListagemParcelas from "./pages/parcela/listagemParcela"

const root = ReactDOM.createRoot(document.getElementById('root'));

const router = createBrowserRouter([
    {
        path: "/",
        element: <Navigate to="/app" replace />
    },
    {
        path: "/app",
        element: <App />,
        errorElement: <div>Rota inexistente ou com problema!</div>,
        children: [
            {
                path: "login",
                element: <Login />
            },
            {
                path: "cadastro",
                element: <Cadastro />
            },
            {
                path: "/app",
                element: <ListagemEmprestimos />
            },
            {
                path: "agiotas",
                element: <Agiotas />
            },
            {
                path: "clientes",
                element: <Clientes />
            },
            {
                path: "cadastrar-cliente",
                element: <CadastroCliente />
            },
            {
                path: "listar-parcelas/:emprestimoId",
                element: <ListagemParcelas />
            }
        ]
    }
]);

root.render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);