import { Cliente } from "../models/client";

// URL base da API
const API_URL = "http://localhost:8080/clientes"; // Substitua pelo URL correto da sua API

// Função para obter todos os clientes (GET)
export const getClients = async (): Promise<Cliente[]> => {
  const response = await fetch(API_URL);

  if (!response.ok) {
    throw new Error("Erro ao buscar clientes");
  }

  return response.json();
};

// Função para adicionar um novo cliente (POST)
export const createClient = async (novoCliente: Cliente): Promise<Cliente> => {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(novoCliente),
  });

  if (!response.ok) {
    throw new Error("Erro ao adicionar cliente");
  }

  return response.json();
};

// Função para atualizar um cliente (PUT)
export const updateClient = async (
  id: number,
  clienteAtualizado: Cliente
): Promise<void> => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(clienteAtualizado),
  });

  if (!response.ok) {
    throw new Error("Erro ao atualizar cliente");
  }
};

// Função para deletar um cliente (DELETE)
export const deleteClient = async (id: number): Promise<void> => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "DELETE",
  });

  if (!response.ok) {
    throw new Error("Erro ao deletar cliente");
  }
};
