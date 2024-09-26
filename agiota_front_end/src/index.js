import React from 'react';
import ReactDOM from 'react-dom/client';
import './styles/index.css';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import App from './App';
import Login from './components/login';
import Cadastro from './components/cadastro/cadastroForm';
import Home from './pages/home/Home';
import ListagemEmprestimos from "./pages/emprestimoCliente/listagemEmprestimos";
import SolicitarEmprestimo from './pages/emprestimoCliente/solicitarEmprestimos';

const root = ReactDOM.createRoot(document.getElementById('root'));

const router = createBrowserRouter([
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
                path: "",
                element: <Home />
            },
            {
                path: "emprestimos",
                element: <ListagemEmprestimos />
            },
            // {
            //     path: "agiotas",
            //     element: <Agiotas />
            // },
            // {
            //     path: "clientes",
            //     element: <Clientes />
            // },
            // {
            //     path: "cadastrar-cliente",
            //     element: <CadastroCliente />
            // },
            {
                path: "solicitar-emprestimo",
                element: <SolicitarEmprestimo />
            }
        ]
    }
]);

root.render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);