import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import Login from "./components/login";
import Cadastro from "./components/cadastro";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById('root'));

const router = createBrowserRouter([
    {
        element: <App/>,
        children: [
            { path: "/"},
            { path: "/login", element: <Login /> },
            { path: "/cadastro", element: <Cadastro /> }
        ]
    }
]);

root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);