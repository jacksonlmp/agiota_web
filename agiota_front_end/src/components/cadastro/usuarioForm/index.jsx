import React, { useState } from 'react';
import { FiUser, FiMail, FiLock, FiEye, FiEyeOff, FiPhone } from 'react-icons/fi';

const formatCPF = (cpf) => {
    cpf = cpf.replace(/\D/g, ''); 

    if (cpf.length > 11) {
        cpf = cpf.slice(0, 11);
    }

    if (cpf.length > 9) {
        cpf = cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{1,2})/, "$1.$2.$3-$4");
    } else if (cpf.length > 6) {
        cpf = cpf.replace(/(\d{3})(\d{3})(\d{1,3})/, "$1.$2.$3");
    } else if (cpf.length > 3) {
        cpf = cpf.replace(/(\d{3})(\d{1,3})/, "$1.$2");
    }

    return cpf;
};

const UsuarioForm = ({ userData, handleUserDataChange, handleNext }) => {
    const [isPasswordVisible, setIsPasswordVisible] = useState(false);

    const togglePasswordVisibility = () => setIsPasswordVisible(!isPasswordVisible);

    const handleInputChange = (e) => {
        const { name, value } = e.target;

        if (name === "cpf") {
            handleUserDataChange({
                target: {
                    name,
                    value: formatCPF(value),
                },
            });
        } else {
            handleUserDataChange(e);
        }
    };

    return (
        <div className="bg-[#141414] rounded-2xl shadow-lg p-8 w-full max-w-md">
            <h2 className="text-center text-2xl font-semibold text-white mb-6">
                Cadastre-se
            </h2>
            <div className="mb-4">
                <Input
                    label="Nome"
                    icon={FiUser}
                    type="text"
                    name="nome"
                    value={userData.nome}
                    onChange={handleInputChange}
                />
            </div>
            <div className="mb-4">
                <Input
                    label="E-mail"
                    icon={FiMail}
                    type="email"
                    name="email"
                    value={userData.email}
                    onChange={handleInputChange}
                />
            </div>
            <div className="mb-4">
                <Input
                    label="Senha"
                    icon={FiLock}
                    type="password"
                    name="senha"
                    value={userData.senha}
                    onChange={handleInputChange}
                    isPasswordVisible={isPasswordVisible}
                    togglePasswordVisibility={togglePasswordVisibility}
                />
            </div>
            <div className="mb-4">
                <Input
                    label="Telefone"
                    icon={FiPhone}
                    type="tel"
                    name="telefone"
                    value={userData.telefone}
                    onChange={handleInputChange}
                />
            </div>
            <div className="mb-4">
                <Input
                    label="CPF"
                    icon={FiUser}
                    type="text"
                    name="cpf"
                    value={userData.cpf}
                    onChange={handleInputChange}
                    maxLength={14}
                />
            </div>
            <div className="flex justify-center mb-6">
                <button
                    type="button"
                    onClick={handleNext}
                    className="bg-gradient-to-r from-blue-400 to-green-500 hover:from-blue-500 hover:to-green-600 text-white font-semibold py-3 px-6 rounded-lg focus:outline-none focus:ring-4 focus:ring-green-500 focus:ring-opacity-50 w-full"
                >
                    Próximo
                </button>
            </div>
            <div className="text-center">
                <a href="/login" className="text-[#b5b5b5] text-sm underline">
                    Já possuo uma conta
                </a>
            </div>
        </div>
    );
};

const Input = ({ label, icon: Icon, type, isPasswordVisible, togglePasswordVisibility, ...rest }) => {
    const [isFocus, setIsFocus] = useState(false);
    const inputClass = `w-[80%] flex-1 items-center border-none outline-none bg-transparent text-[10pt] text-white placeholder:text-[#9CA3AF] no-spin`;

    return (
        <div className="w-full text-[11pt] text-left">
            <div className="w-full mb-1 pl-4 text-[#9CA3AF] font-bold whitespace-nowrap overflow-ellipsis overflow-hidden">
                {label}
            </div>

            <section
                className={`w-full h-[2.5rem] flex items-center p-[.5rem] transition-all bg-[#111112] rounded-[.5rem] px-3 ${
                    isFocus && "custom-shadow"
                }`}
            >
                {Icon && (
                    <Icon className="text-[14pt] text-white mr-2" />
                )}

                {type === "password" ? (
                    <>
                        <input
                            type={isPasswordVisible ? "text" : "password"}
                            {...rest}
                            className={inputClass}
                            onFocus={() => setIsFocus(true)}
                            onBlur={() => setIsFocus(false)}
                        />
                        {isPasswordVisible ? (
                            <FiEye
                                onClick={togglePasswordVisibility}
                                className="text-[14pt] text-white cursor-pointer"
                            />
                        ) : (
                            <FiEyeOff
                                onClick={togglePasswordVisibility}
                                className="text-[14pt] text-white cursor-pointer"
                            />
                        )}
                    </>
                ) : (
                    <input
                        type={type}
                        {...rest}
                        className={inputClass}
                        onFocus={() => setIsFocus(true)}
                        onBlur={() => setIsFocus(false)}
                    />
                )}
            </section>
        </div>
    );
};

export default UsuarioForm;