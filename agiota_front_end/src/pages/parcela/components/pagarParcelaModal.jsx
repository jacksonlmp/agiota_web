import React, { useState } from 'react';
import {rejeitarEmprestimo} from "../../../api/agiotas";
import {pagarParcela} from "../../../api/clientes";

const PagarParcelaModal = ({ isOpen, closeModal, record, user, refreshList }) => {
    const [valor, setValor] = useState(0);
    const [metodoPagamento, setMetodoPagamento] = useState("Dinheiro");

    const handle = async (event) => {
        event.preventDefault();

        const result = await pagarParcela({valor, metodoPagamento, parcelaId: record.id}, user.token)

        if (result) {
            refreshList()
            window.alert("Pagamento efetuado com sucesso!")
            closeModal(false);
        }
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
            <div className="relative bg-white rounded-lg shadow dark:bg-gray-700 w-full max-w-md p-4">
                <button
                    type="button"
                    className="absolute top-3 right-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
                    onClick={closeModal}
                >
                    <svg
                        className="w-3 h-3"
                        aria-hidden="true"
                        xmlns="http://www.w3.org/2000/svg"
                        fill="none"
                        viewBox="0 0 14 14"
                    >
                        <path
                            stroke="currentColor"
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            strokeWidth="2"
                            d="M1 1l6 6m0 0l6 6M7 7l6-6M7 7L1 1"
                        />
                    </svg>
                    <span className="sr-only">Close modal</span>
                </button>
                <div className="p-4 md:p-5 text-center">
                    <h3 className="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">
                        Insira os dados do pagamento
                    </h3>
                    <form onSubmit={handle} className="p-4 md:p-5">
                        <div className="grid gap-4 mb-4 grid-cols-2">
                            <div className="col-span-2">
                                <input
                                    id="valor"
                                    type="number"
                                    name="valor"
                                    value={valor}
                                    onChange={(e) => setValor(parseInt(e.target.value))}
                                    className="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    placeholder="Insira aqui os seus motivos"
                                ></input>
                            </div>
                            <div className="col-span-2">
                                <input
                                    id="metodoPagamento"
                                    type="text"
                                    name="metodoPagamento"
                                    value={metodoPagamento}
                                    onChange={(e) => setMetodoPagamento(e.target.value)}
                                    className="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    placeholder="Insira o método de pagamento"
                                ></input>
                            </div>
                        </div>
                        <button
                            type="submit"
                            className="text-white bg-green-600 hover:bg-green-800 focus:ring-4 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 font-medium rounded-lg text-sm inline-flex items-center px-5 py-2.5 text-center"
                        >
                            Pagar
                        </button>
                        <button
                            type="button"
                            className="py-2.5 px-5 ml-3 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
                            onClick={closeModal}
                        >
                            Cancelar
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default PagarParcelaModal;
