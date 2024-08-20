import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import Emprestimos from "./routes/Emprestimos";
import {createBrowserRouter, RouterProvider, Route} from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById('root'));

const router = createBrowserRouter([
    {
        element: <App/>,
        children: [
            { path: "/" },
            { path: "/emprestimos", element: <Emprestimos /> }
        ]
    }
])

root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
