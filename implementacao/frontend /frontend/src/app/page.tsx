"use client"; // Marcar como componente cliente

import { useState, useEffect } from "react";
import {
  getClients,
  createClient,
  updateClient,
  deleteClient,
} from "../services/clientService"; // Mock API
import { Cliente } from "../models/client"; // Importar modelo de cliente

export default function CrudClientes() {
  const [clientes, setClientes] = useState<Cliente[]>([]);
  const [formData, setFormData] = useState<Cliente>({
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
  const [editando, setEditando] = useState<boolean>(false);
  const [clienteId, setClienteId] = useState<number | null>(null);
  const [loading, setLoading] = useState<boolean>(true); // Estado de carregamento
  const [message, setMessage] = useState<string | null>(null); // Estado para mensagens

  // Carregar os clientes da "API" mockada
  useEffect(() => {
    getClients().then((data) => {
      setClientes(data);
      setLoading(false); // Dados carregados
    });
  }, []);

  // Função para lidar com as mudanças no formulário
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // Função para adicionar um novo cliente ou editar
  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (editando && clienteId !== null) {
      updateClient(clienteId, formData).then(() => {
        setClientes(
          clientes.map((cliente) =>
            cliente.id === clienteId ? { ...cliente, ...formData } : cliente
          )
        );
        setMessage("Cliente atualizado com sucesso!");
      });
      setEditando(false);
      setClienteId(null);
    } else {
      createClient(formData).then((newClient) => {
        setClientes([...clientes, newClient]);
        setMessage("Cliente adicionado com sucesso!");
      });
    }
    setFormData({
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
    setTimeout(() => setMessage(null), 3000); // Limpar a mensagem após 3 segundos
  };

  // Função para excluir um cliente
  const handleDelete = (id: number) => {
    deleteClient(id).then(() => {
      setClientes(clientes.filter((cliente) => cliente.id !== id));
      setMessage("Cliente excluído com sucesso!");
    });
    setTimeout(() => setMessage(null), 3000); // Limpar a mensagem após 3 segundos
  };

  // Função para editar um cliente
  const handleEdit = (cliente: Cliente) => {
    setFormData(cliente);
    setEditando(true);
    setClienteId(cliente.id!);
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <h1 className="text-3xl font-bold text-center mb-3 mt-3">
        Gestão de Clientes
      </h1>

      {/* Exibir mensagens de sucesso */}
      {message && (
        <div className="bg-green-100 text-green-800 px-4 py-2 rounded mb-4">
          {message}
        </div>
      )}

      {/* Formulário para adicionar/editar */}
      <form onSubmit={handleSubmit} className="mb-8">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-2 mb-4">
          <div>
            <label htmlFor="nome" className="block text-gray-700 mb-2">
              Nome:
            </label>
            <input
              type="text"
              name="nome"
              id="nome"
              value={formData.nome}
              onChange={handleChange}
              className="border p-2 w-full rounded-md"
              required
            />
          </div>
          <div>
            <label htmlFor="rg" className="block text-gray-700 mb-2">
              RG:
            </label>
            <input
              type="text"
              name="rg"
              id="rg"
              value={formData.rg}
              onChange={handleChange}
              className="border p-2 w-full rounded-md"
              required
            />
          </div>
          <div>
            <label htmlFor="cpf" className="block text-gray-700 mb-2">
              CPF:
            </label>
            <input
              type="text"
              name="cpf"
              id="cpf"
              value={formData.cpf}
              onChange={handleChange}
              className="border p-2 w-full rounded-md"
              required
            />
          </div>
          <div>
            <label htmlFor="endereco" className="block text-gray-700 mb-2">
              Endereço:
            </label>
            <input
              type="text"
              name="endereco"
              id="endereco"
              value={formData.endereco}
              onChange={handleChange}
              className="border p-2 w-full rounded-md"
            />
          </div>
          <div>
            <label htmlFor="profissao" className="block text-gray-700 mb-2">
              Profissão:
            </label>
            <input
              type="text"
              name="profissao"
              id="profissao"
              value={formData.profissao}
              onChange={handleChange}
              className="border p-2 w-full rounded-md"
            />
          </div>
          <div>
            <label htmlFor="login" className="block text-gray-700 mb-2">
              Login:
            </label>
            <input
              type="text"
              name="login"
              id="login"
              value={formData.login}
              onChange={handleChange}
              className="border p-2 w-full rounded-md"
              required
            />
          </div>
          <div>
            <label htmlFor="senha" className="block text-gray-700 mb-2">
              Senha:
            </label>
            <input
              type="password"
              name="senha"
              id="senha"
              value={formData.senha}
              onChange={handleChange}
              className="border p-2 w-full rounded-md"
              required
            />
          </div>
          <div>
            <label
              htmlFor="entidadeEmpregadora"
              className="block text-gray-700 mb-2"
            >
              Entidade Empregadora:
            </label>
            <input
              type="text"
              name="entidadeEmpregadora"
              id="entidadeEmpregadora"
              value={formData.entidadeEmpregadora}
              onChange={handleChange}
              className="border p-2 w-full rounded-md"
            />
          </div>
          <div>
            <label htmlFor="rendimento" className="block text-gray-700 mb-2">
              Rendimento:
            </label>
            <input
              type="number"
              name="rendimento"
              id="rendimento"
              value={formData.rendimento}
              onChange={handleChange}
              step="0.01"
              min="0"
              className="border p-2 w-full rounded-md"
            />
          </div>
        </div>
        <button
          type="submit"
          className={`w-full py-2 px-4 rounded-md ${
            editando ? "bg-yellow-500" : "bg-blue-500"
          } text-white`}
        >
          {editando ? "Salvar Alterações" : "Adicionar Cliente"}
        </button>
      </form>

      {/* Exibir estado de carregamento */}
      {loading ? (
        <div className="text-center">
          <div
            className="spinner-border animate-spin inline-block w-8 h-8 border-4 rounded-full text-blue-500"
            role="status"
          ></div>
          <p>Carregando...</p>
        </div>
      ) : (
        /* Tabela de Clientes */
        <div className="overflow-x-auto w-full mt-4 shadow-lg rounded-lg">
          <table className="table-auto w-full border-collapse border border-gray-300 text-sm bg-white">
            <thead>
              <tr className="bg-gray-200">
                <th className="border px-4 py-2">ID</th>
                <th className="border px-4 py-2">Nome</th>
                <th className="border px-4 py-2">RG</th>
                <th className="border px-4 py-2">CPF</th>
                <th className="border px-4 py-2">Endereço</th>
                <th className="border px-4 py-2">Profissão</th>
                <th className="border px-4 py-2">Login</th>
                <th className="border px-4 py-2">Entidade Empregadora</th>
                <th className="border px-4 py-2">Rendimento</th>
                <th className="border px-4 py-2">Ações</th>
              </tr>
            </thead>
            <tbody>
              {clientes.map((cliente) => (
                <tr key={cliente.id} className="text-center">
                  <td className="border px-4 py-2">{cliente.id}</td>
                  <td className="border px-4 py-2">{cliente.nome}</td>
                  <td className="border px-4 py-2">{cliente.rg}</td>
                  <td className="border px-4 py-2">{cliente.cpf}</td>
                  <td className="border px-4 py-2">{cliente.endereco}</td>
                  <td className="border px-4 py-2">{cliente.profissao}</td>
                  <td className="border px-4 py-2">{cliente.login}</td>
                  <td className="border px-4 py-2">
                    {cliente.entidadeEmpregadora}
                  </td>
                  <td className="border px-4 py-2">
                    {cliente.rendimento?.toFixed(2)}
                  </td>
                  <td className="border px-4 py-2 flex justify-center space-x-2">
                    <button
                      onClick={() => handleEdit(cliente)}
                      className="bg-yellow-500 hover:bg-yellow-600 text-white px-4 py-2 rounded shadow-md"
                    >
                      Editar
                    </button>
                    <button
                      onClick={() => handleDelete(cliente.id!)}
                      className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded shadow-md"
                    >
                      Excluir
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
