import React from 'react';
import { FiMapPin } from 'react-icons/fi';

const EnderecoForm = ({ enderecoData, handleEnderecoChange, handleNext, handlePrevious, handleCepSearch }) => {
    const handleBlur = () => {
        handleCepSearch(enderecoData.cep);
    };

    return (
            <div className="bg-[#141414] rounded-2xl shadow-lg p-8 w-full max-w-md">
                <h2 className="text-center text-2xl font-semibold text-white mb-6">Endereço</h2>
                <div className="mb-4">
                    <Input
                        label="CEP"
                        icon={FiMapPin}
                        type="text"
                        name="cep"
                        value={enderecoData.cep}
                        onChange={(e) => handleEnderecoChange(e)}
                        onBlur={handleBlur}
                        placeholder="Digite o CEP"
                    />
                </div>
                <div className="mb-4">
                    <Input
                        label="Cidade"
                        icon={FiMapPin}
                        type="text"
                        name="cidade"
                        value={enderecoData.cidade}
                        onChange={handleEnderecoChange}
                        placeholder="Digite a cidade"
                    />
                </div>
                <div className="mb-4">
                    <Input
                        label="UF"
                        icon={FiMapPin}
                        type="text"
                        name="uf"
                        value={enderecoData.uf}
                        onChange={handleEnderecoChange}
                        placeholder="Digite o UF"
                    />
                </div>
                <div className="mb-4">
                    <Input
                        label="Logradouro"
                        icon={FiMapPin}
                        type="text"
                        name="logradouro"
                        value={enderecoData.logradouro}
                        onChange={handleEnderecoChange}
                        placeholder="Digite o logradouro"
                    />
                </div>
                <div className="mb-4">
                    <Input
                        label="Bairro"
                        icon={FiMapPin}
                        type="text"
                        name="bairro"
                        value={enderecoData.bairro}
                        onChange={handleEnderecoChange}
                        placeholder="Digite o bairro"
                    />
                </div>
                <div className="mb-4">
                    <Input
                        label="Número"
                        icon={FiMapPin}
                        type="text"
                        name="numero"
                        value={enderecoData.numero}
                        onChange={handleEnderecoChange}
                        placeholder="Digite o número"
                    />
                </div>
                <div className="flex space-x-2 justify-between mb-6">
                    <button
                        type="button"
                        onClick={handlePrevious}
                        className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-2 px-4 rounded-md focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50"
                    >
                        Voltar
                    </button>
                    <button
                        type="button"
                        onClick={handleNext}
                        className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-2 px-4 rounded-md focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50"
                    >
                        Próximo
                    </button>
                </div>
            </div>
    );
};

const Input = ({ label, icon: Icon, type, ...rest }) => {
    return (
        <div className="w-full text-[11pt] text-left">
            <div className="w-full mb-1 pl-4 text-[#9CA3AF] font-bold whitespace-nowrap overflow-ellipsis overflow-hidden">
                {label}
            </div>
            <section className="w-full h-[2.5rem] flex items-center p-[.5rem] transition-all bg-[#111112] rounded-[.5rem] px-3">
                {Icon && <Icon className="text-[14pt] text-white mr-2" />}
                <input
                    type={type}
                    {...rest}
                    className="w-[80%] flex-1 border-none outline-none bg-transparent text-[10pt] text-white placeholder:text-[#9CA3AF] no-spin"
                />
            </section>
        </div>
    );
};

export default EnderecoForm;