import React from 'react';
import { useNavigate } from 'react-router-dom';

const RoleSelection = ({ handleRoleSelection, handlePrevious }) => {
    const navigate = useNavigate(); // Hook to navigate programmatically

    return (
        <div className="bg-[#141414] rounded-2xl w-full max-w-md mx-auto p-6">
            <h2 className="text-center text-2xl font-semibold text-white mb-6">
                Selecione seu tipo
            </h2>
            <div className="flex flex-col space-y-4 mb-6">
                <button
                    onClick={() => handleRoleSelection('agiota')}
                    className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-2 px-4 rounded-md focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50"
                >
                    Agiota
                </button>
                <button
                    onClick={() => handleRoleSelection('cliente')}
                    className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-2 px-4 rounded-md focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50"
                >
                    Cliente
                </button>
            </div>
            <div className="flex justify-center">
                <button
                    type="button"
                    onClick={handlePrevious}
                    className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-2 px-4 rounded-md focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50"
                >
                    Voltar
                </button>
            </div>
        </div>
    );
};

export default RoleSelection;