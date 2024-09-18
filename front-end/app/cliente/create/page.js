'use client'
import { cadastrarCliente } from "@/app/lib/funcoes";
import { useState } from "react";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { format } from 'date-fns';
import { useRouter } from 'next/navigation'

export default function CadastrarCliente() {
  const router = useRouter();

  const [formData, setFormData] = useState(
  {
      nome: '',
      nascimento: '',
      cpf: '',
      celular: '',
      email: '',
      senha: '',
      profissao: '',
      localTrabalho: '',
      telefoneTrabalho: '',
      endereco: {
        logradouro: '',
        numero: '',
        bairro: '',
        cep: '',
        cidade: '',
        uf: ''
      }
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    const [parent, child] = name.split('.');

    if (child) {
      setFormData((prevState) => ({
        ...prevState,
        [parent]: {
          ...prevState[parent],
          [child]: value,
        },
      }));
    } else {
      setFormData({ ...formData, [name]: value });
    }
    console.log(formData);
  };

  const handleDateChange = (date) => {
    setFormData({ ...formData, nascimento: date });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(formData);
    const formattedData = {
      ...formData,
      nascimento: formData.nascimento ? format(formData.nascimento, 'dd/MM/yyyy') : null,
    };

    cadastrarCliente(formattedData)
      .then(
        (result) => {
          console.log('Success:', result);
          router.push('/cliente')
        }
      )
      .catch (
        (error) => {console.error('Error:', error); }
      )
  }   

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100">
      <div className="card w-full max-w-lg shadow-2xl bg-base-100">
        <div className="card-body">
          <h1 className="card-title text-center">Cadastrar Cliente</h1>
          <form onSubmit={handleSubmit}>
            <div className="form-control">
              <label className="label">
                <span className="label-text">Nome</span>
              </label>
              <input
                type="text"
                name="nome"
                value={formData.nome}
                onChange={handleChange}
                className="input input-bordered"
              />
            </div>
            <div className="form-control">
              <label className="label">
                <span className="label-text">Nascimento</span>
              </label>
              <DatePicker
                selected={formData.nascimento}
                onChange={handleDateChange}
                dateFormat="dd/MM/yyyy"
                className="input input-bordered"
                showPopperArrow={false}
              />
            </div>
            <div className="form-control">
              <label className="label">
                <span className="label-text">CPF</span>
              </label>
              <input
                type="text"
                name="cpf"
                value={formData.cpf}
                onChange={handleChange}
                className="input input-bordered"
              />
            </div>
            <div className="form-control">
              <label className="label">
                <span className="label-text">Celular</span>
              </label>
              <input
                type="text"
                name="celular"
                value={formData.celular}
                onChange={handleChange}
                className="input input-bordered"
              />
            </div>
            <div className="form-control">
              <label className="label">
                <span className="label-text">Email</span>
              </label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                className="input input-bordered"
              />
            </div>
            <div className="form-control">
              <label className="label">
                <span className="label-text">Senha</span>
              </label>
              <input
                type="password"
                name="senha"
                value={formData.senha}
                onChange={handleChange}
                className="input input-bordered"
              />
            </div>
            <div className="form-control">
              <label className="label">
                <span className="label-text">Profissão</span>
              </label>
              <input
                type="text"
                name="profissao"
                value={formData.profissao}
                onChange={handleChange}
                className="input input-bordered"
              />
            </div>
            <div className="form-control">
              <label className="label">
                <span className="label-text">Local de Trabalho</span>
              </label>
              <input
                type="text"
                name="localTrabalho"
                value={formData.localTrabalho}
                onChange={handleChange}
                className="input input-bordered"
              />
            </div>
            <div className="form-control">
              <label className="label">
                <span className="label-text">Telefone do Trabalho</span>
              </label>
              <input
                type="text"
                name="telefoneTrabalho"
                value={formData.telefoneTrabalho}
                onChange={handleChange}
                className="input input-bordered"
              />
            </div>
            <fieldset className="form-control">
              <legend className="label-text mb-2">Endereço</legend>
              <label className="label">
                <span className="label-text">Logradouro</span>
              </label>
              <input
                type="text"
                name="endereco.logradouro"
                value={formData.endereco.logradouro}
                onChange={handleChange}
                className="input input-bordered"
              />
              <label className="label">
                <span className="label-text">Número</span>
              </label>
              <input
                type="text"
                name="endereco.numero"
                value={formData.endereco.numero}
                onChange={handleChange}
                className="input input-bordered"
              />
              <label className="label">
                <span className="label-text">Bairro</span>
              </label>
              <input
                type="text"
                name="endereco.bairro"
                value={formData.endereco.bairro}
                onChange={handleChange}
                className="input input-bordered"
              />
              <label className="label">
                <span className="label-text">CEP</span>
              </label>
              <input
                type="text"
                name="endereco.cep"
                value={formData.endereco.cep}
                onChange={handleChange}
                className="input input-bordered"
              />
              <label className="label">
                <span className="label-text">Cidade</span>
              </label>
              <input
                type="text"
                name="endereco.cidade"
                value={formData.endereco.cidade}
                onChange={handleChange}
                className="input input-bordered"
              />
              <label className="label">
                <span className="label-text">UF</span>
              </label>
              <input
                type="text"
                name="endereco.uf"
                value={formData.endereco.uf}
                onChange={handleChange}
                className="input input-bordered"
              />
            </fieldset>
            <div className="form-control mt-6">
              <button type="submit" className="btn btn-primary">Submit</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}