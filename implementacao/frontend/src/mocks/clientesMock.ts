import { Cliente } from "../models/Cliente";

export const clientesMock: Cliente[] = [
  {
    id: 1,
    nome: "João Silva",
    rg: "123456789",
    cpf: "111.222.333-44",
    endereco: "Rua A, 123",
    profissao: "Engenheiro",
    login: "joao.silva",
    senha: "senha123",
  },
  {
    id: 2,
    nome: "Maria Oliveira",
    rg: "987654321",
    cpf: "222.333.444-55",
    endereco: "Rua B, 456",
    profissao: "Designer",
    login: "maria.oliveira",
    senha: "senha123",
  },
  {
    id: 3,
    nome: "Pedro Santos",
    rg: "654321987",
    cpf: "333.444.555-66",
    endereco: "Rua C, 789",
    profissao: "Professor",
    login: "pedro.santos",
    senha: "senha123",
  },
];
