import React from 'react';
import ReactDOM from 'react-dom/client';
import './styles/index.css';
import App from './App';
import Login from './components/login/FormularioLogin';
import Cadastro from './components/cadastro/Cadastro';
import { createBrowserRouter, RouterProvider, Navigate } from 'react-router-dom';
import EmprestimosCliente from "./pages/EmprestimosCliente";
import EmprestimosAgiota from "./pages/EmprestimosAgiota";
import Agiotas from "./pages/Agiotas";

const root = ReactDOM.createRoot(document.getElementById('root'));

const router = createBrowserRouter([
    {
        path: "/",
        element: <Navigate to="/app/login" replace />
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
                path: "emprestimos-cliente",
                element: <EmprestimosCliente />
            },
            {
                path: "emprestimos-agiota",
                element: <EmprestimosAgiota />
            },
            {
                path: "agiotas",
                element: <Agiotas />
            }
        ]
    }
]);

root.render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);