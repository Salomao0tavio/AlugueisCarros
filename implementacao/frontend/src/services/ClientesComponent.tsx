import React, { useEffect, useState } from "react";
import {
  getClients,
  createClient,
  updateClient,
  deleteClient,
} from "../services/clientService";
import { Cliente } from "../models/client";

const ClientesComponent: React.FC = () => {
  const [clientes, setClientes] = useState<Cliente[]>([]);
  const [clienteAtual, setClienteAtual] = useState<Cliente>({
    id: 0,
    nome: "",
    rg: "",
    cpf: "",
    endereco: "",
    profissao: "",
    login: "",
    senha: "",
    entidadeEmpregadora: "",
    rendimento: 0,
  });
  const [isEditMode, setIsEditMode] = useState(false);

  // Carregar clientes da API ao montar o componente
  useEffect(() => {
    carregarClientes();
  }, []);

  const carregarClientes = async () => {
    try {
      const data = await getClients();
      setClientes(data);
    } catch (error) {
      console.error("Erro ao carregar clientes:", error);
    }
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setClienteAtual({ ...clienteAtual, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (isEditMode) {
        await updateClient(clienteAtual.id, clienteAtual);
      } else {
        await createClient(clienteAtual);
      }
      limparFormulario();
      carregarClientes();
    } catch (error) {
      console.error("Erro ao salvar cliente:", error);
    }
  };

  const handleEdit = (cliente: Cliente) => {
    setClienteAtual(cliente);
    setIsEditMode(true);
  };

  const handleDelete = async (id: number) => {
    try {
      await deleteClient(id);
      carregarClientes();
    } catch (error) {
      console.error("Erro ao deletar cliente:", error);
    }
  };

  const limparFormulario = () => {
    setClienteAtual({
      id: 0,
      nome: "",
      rg: "",
      cpf: "",
      endereco: "",
      profissao: "",
      login: "",
      senha: "",
      entidadeEmpregadora: "",
      rendimento: 0,
    });
    setIsEditMode(false);
  };

  return (
    <div>
      <h1>Gestão de Clientes</h1>

      <form onSubmit={handleSubmit}>
        <h2>{isEditMode ? "Editar Cliente" : "Adicionar Cliente"}</h2>
        <label>Nome:</label>
        <input
          type="text"
          name="nome"
          value={clienteAtual.nome}
          onChange={handleInputChange}
          required
        />
        <label>RG:</label>
        <input
          type="text"
          name="rg"
          value={clienteAtual.rg}
          onChange={handleInputChange}
          required
        />
        <label>CPF:</label>
        <input
          type="text"
          name="cpf"
          value={clienteAtual.cpf}
          onChange={handleInputChange}
          required
        />
        <label>Endereço:</label>
        <input
          type="text"
          name="endereco"
          value={clienteAtual.endereco}
          onChange={handleInputChange}
        />
        <label>Profissão:</label>
        <input
          type="text"
          name="profissao"
          value={clienteAtual.profissao}
          onChange={handleInputChange}
        />
        <label>Login:</label>
        <input
          type="text"
          name="login"
          value={clienteAtual.login}
          onChange={handleInputChange}
          required
        />
        <label>Senha:</label>
        <input
          type="password"
          name="senha"
          value={clienteAtual.senha}
          onChange={handleInputChange}
          required
        />
        <label>Entidade Empregadora:</label>
        <input
          type="text"
          name="entidadeEmpregadora"
          value={clienteAtual.entidadeEmpregadora}
          onChange={handleInputChange}
        />
        <label>Rendimento:</label>
        <input
          type="number"
          name="rendimento"
          value={clienteAtual.rendimento}
          onChange={handleInputChange}
          step="0.01"
          min="0"
        />
        <button type="submit">{isEditMode ? "Atualizar" : "Adicionar"}</button>
        {isEditMode && <button onClick={limparFormulario}>Cancelar</button>}
      </form>

      <div id="clientesList">
        {clientes.map((cliente) => (
          <div key={cliente.id}>
            <p>
              <strong>ID:</strong> {cliente.id}
            </p>
            <p>
              <strong>Nome:</strong> {cliente.nome}
            </p>
            <p>
              <strong>CPF:</strong> {cliente.cpf}
            </p>
            <p>
              <strong>Entidade Empregadora:</strong>{" "}
              {cliente.entidadeEmpregadora}
            </p>
            <p>
              <strong>Rendimento:</strong> R$ {cliente.rendimento.toFixed(2)}
            </p>
            <button onClick={() => handleEdit(cliente)}>Editar</button>
            <button onClick={() => handleDelete(cliente.id)}>Deletar</button>
            <hr />
          </div>
        ))}
      </div>
    </div>
  );
};

export default ClientesComponent;
