import React, { useState } from 'react';
import UsuarioForm from './usuarioForm';
import EnderecoForm from './enderecoForm'; 
import RoleForm from './roleForm'; 
import RoleSelection from './roleForm/selecaoRole';
import { onCreateCliente } from '../../api/clientes';
import { onCreateAgiota } from '../../api/agiotas';

const Cadastro = () => {
    const [step, setStep] = useState(1);
    const [role, setRole] = useState(null);
    const [userData, setUserData] = useState({
        nome: '',
        email: '',
        senha: '',
        telefone: '',
        cpf: '',
    });
    const [enderecoData, setEnderecoData] = useState({
        cep: '',
        cidade: '',
        uf: '',
        logradouro: '',
        bairro: '',
        numero: '',
    });
    const [roleData, setRoleData] = useState({
        profissao: '',
        localDeTrabalho: '',
        taxaDeJuros: '',
        metodoCobranca: '',
        periodoTaxa: '',
    });

    const handleUserDataChange = (e) => {
        setUserData({ ...userData, [e.target.name]: e.target.value });
        console.log('Dados do usuário:', { ...userData, [e.target.name]: e.target.value });
    };

    const handleEnderecoChange = (e) => {
        setEnderecoData({ ...enderecoData, [e.target.name]: e.target.value });
    };

    const handleRoleChange = (e) => {
        setRoleData({ ...roleData, [e.target.name]: e.target.value });
    };

    const handleNext = () => setStep(step + 1);
    const handlePrevious = () => setStep(step - 1);

    const handleRoleSelection = (selectedRole) => {
        setRole(selectedRole);
        handleNext();
    };    

    const handleCepSearch = async (cep) => {
        if (!cep) return;

        try {
            const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
            const data = await response.json();
            if (data.erro) {
                alert('CEP não encontrado.');
                return;
            }

            setEnderecoData({
                cep: data.cep || '',
                cidade: data.localidade || '',
                uf: data.uf || '',
                logradouro: data.logradouro || '',
                bairro: data.bairro || '',
                numero: '',
            });
        } catch (error) {
            console.error('Erro ao buscar o CEP:', error);
            alert('Erro ao buscar o CEP.');
        }
    };

    const handleSubmit = async () => {
        console.log('Dados do roleData:', roleData);
    
        try {
            if (role === 'cliente') {
                await onCreateCliente(userData, enderecoData, roleData);
            } else if (role === 'agiota') {
                await onCreateAgiota(userData, enderecoData, roleData); 
            }
            alert('Cadastro realizado com sucesso!');
        } catch (error) {
            console.error('Erro ao criar usuário:', error);
            alert('Erro ao realizar o cadastro.');
        }
    };

    return (
        <div className="flex items-center justify-center min-h-screen bg-[#0f0f0f]">
            <form className="bg-[#141414] rounded-2xl shadow-lg p-8 w-full max-w-md mx-auto">
                {step === 1 && (
                    <UsuarioForm
                        userData={userData}
                        handleUserDataChange={handleUserDataChange}
                        handleNext={handleNext}
                    />
                )}
                {step === 2 && (
                    <EnderecoForm
                        enderecoData={enderecoData}
                        handleEnderecoChange={handleEnderecoChange}
                        handleCepSearch={handleCepSearch}
                        handlePrevious={handlePrevious}
                        handleNext={handleNext}
                    />
                )}
                {step === 3 && (
                    <RoleSelection
                        handleRoleSelection={handleRoleSelection}
                        handlePrevious={handlePrevious}
                    />
                )}
                {step === 4 && (
                    <RoleForm
                        role={role}
                        roleData={roleData}
                        handleRoleChange={handleRoleChange}
                        handlePrevious={handlePrevious}
                        handleSubmit={handleSubmit}
                    />
                )}
            </form>
        </div>
    );
};

export default Cadastro;