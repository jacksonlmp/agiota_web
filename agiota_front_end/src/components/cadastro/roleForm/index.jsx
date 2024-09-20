import React from 'react';
import { FiTag, FiDollarSign, FiCalendar, FiMapPin, FiUser } from 'react-icons/fi';

const Input = ({ label, icon: Icon, type, ...rest }) => (
    <div className="w-full mb-4">
        <label className="block text-[#9CA3AF] font-bold mb-1 text-sm">
            {label}
        </label>
        <div className="flex items-center p-2 bg-[#141414] border border-[#333] rounded-lg transition-all duration-300 focus-within:border-blue-500">
            {Icon && <Icon className="text-white mr-2 text-lg" />}
            <input
                type={type}
                {...rest}
                className="w-full bg-transparent text-white placeholder-[#9CA3AF] text-sm outline-none"
            />
        </div>
    </div>
);

const RoleForm = ({ role, roleData, handleRoleChange, handlePrevious, handleSubmit }) => {
    return (
            <div className="bg-[#141414] rounded-2xl w-full max-w-md">
                <h2 className="text-center text-2xl font-semibold text-white mb-6">
                    {role === 'cliente' ? 'Dados do Cliente' : 'Dados do Agiota'}
                </h2>
                {role === 'cliente' ? (
                    <div>
                        <Input
                            label="Profissão"
                            icon={FiUser}
                            type="text"
                            name="profissao"
                            value={roleData.profissao}
                            onChange={handleRoleChange}
                            placeholder="Digite sua profissão"
                        />
                        <Input
                            label="Local de Trabalho"
                            icon={FiMapPin}
                            type="text"
                            name="localDeTrabalho"
                            value={roleData.localDeTrabalho}
                            onChange={handleRoleChange}
                            placeholder="Digite seu local de trabalho"
                        />
                    </div>
                ) : (
                    <div>
                        <Input
                            label="Taxa de Juros"
                            icon={FiDollarSign}
                            type="number"
                            step="0.01"
                            name="taxaDeJuros"
                            value={roleData.taxaDeJuros}
                            onChange={handleRoleChange}
                            placeholder="Digite a taxa de juros"
                        />
                        <Input
                            label="Método de Cobrança"
                            icon={FiTag}
                            type="text"
                            name="metodoCobranca"
                            value={roleData.metodoCobranca}
                            onChange={handleRoleChange}
                            placeholder="Digite o método de cobrança"
                        />
                        <Input
                            label="Período da Taxa"
                            icon={FiCalendar}
                            type="text"
                            name="periodoTaxa"
                            value={roleData.periodoTaxa}
                            onChange={handleRoleChange}
                            placeholder="Digite o período da taxa"
                        />
                    </div>
                )}
                <div className="flex space-x-4 justify-between mt-6">
                    <button
                        type="button"
                        onClick={handlePrevious}
                        className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-2 px-4 rounded-md focus:outline-none focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50"
                    >
                        Voltar
                    </button>
                    <button
                        type="button"
                        onClick={handleSubmit}
                        className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-2 px-4 rounded-md focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50"
                    >
                        Concluir
                    </button>
                </div>
            </div>
    );
};

export default RoleForm;