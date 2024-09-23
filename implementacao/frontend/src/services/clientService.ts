import { Cliente } from "../models/client";

// Mock de dados de clientes para simular uma API
let clientes: Cliente[] = [
  {
    id: 1,
    nome: "João Silva",
    rg: "12.345.678-9",
    cpf: "123.456.789-00",
    endereco: "Rua A, 123",
    profissao: "Engenheiro",
    login: "joaosilva",
    senha: "senha123",
    entidadeEmpregadora: "Empresa XYZ",
    rendimento: 5000,
  },
  {
    id: 2,
    nome: "Maria Souza",
    rg: "98.765.432-1",
    cpf: "987.654.321-00",
    endereco: "Av. B, 456",
    profissao: "Médica",
    login: "mariasouza",
    senha: "senha456",
    entidadeEmpregadora: "Hospital ABC",
    rendimento: 10000,
  },
];

// Simulação de GET para obter todos os clientes
export const getClients = async (): Promise<Cliente[]> => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(clientes);
    }, 500); // Simulando um pequeno atraso de rede
  });
};

// Simulação de POST para adicionar um novo cliente
export const createClient = async (novoCliente: Cliente): Promise<Cliente> => {
  return new Promise((resolve) => {
    setTimeout(() => {
      const novoId = clientes.length + 1;
      const clienteComId = { ...novoCliente, id: novoId };
      clientes.push(clienteComId);
      resolve(clienteComId);
    }, 500);
  });
};

// Simulação de PUT para atualizar um cliente
export const updateClient = async (
  id: number,
  clienteAtualizado: Cliente
): Promise<void> => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const index = clientes.findIndex((cliente) => cliente.id === id);
      if (index !== -1) {
        clientes[index] = { ...clientes[index], ...clienteAtualizado };
        resolve();
      } else {
        reject("Cliente não encontrado");
      }
    }, 500);
  });
};

// Simulação de DELETE para remover um cliente
export const deleteClient = async (id: number): Promise<void> => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const index = clientes.findIndex((cliente) => cliente.id === id);
      if (index !== -1) {
        clientes.splice(index, 1);
        resolve();
      } else {
        reject("Cliente não encontrado");
      }
    }, 500);
  });
};
