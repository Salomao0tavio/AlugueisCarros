import { clientesMock } from "../mocks/clientesMock";
import { Cliente } from "../models/Cliente";

export const getClients = async () => {
  return new Promise<Cliente[]>((resolve) => {
    setTimeout(() => {
      resolve(clientesMock);
    }, 500);
  });
};

export const getClientById = async (id: number) => {
  return new Promise<Cliente | undefined>((resolve) => {
    setTimeout(() => {
      resolve(clientesMock.find((cliente) => cliente.id === id));
    }, 500);
  });
};

export const createClient = async (data: Cliente) => {
  return new Promise<Cliente>((resolve) => {
    setTimeout(() => {
      const newClient = { ...data, id: clientesMock.length + 1 };
      clientesMock.push(newClient);
      resolve(newClient);
    }, 500);
  });
};

export const updateClient = async (id: number, data: Cliente) => {
  return new Promise<Cliente | undefined>((resolve) => {
    setTimeout(() => {
      const index = clientesMock.findIndex((cliente) => cliente.id === id);
      if (index !== -1) {
        clientesMock[index] = { ...clientesMock[index], ...data };
        resolve(clientesMock[index]);
      } else {
        resolve(undefined);
      }
    }, 500);
  });
};

export const deleteClient = async (id: number) => {
  return new Promise<void>((resolve) => {
    setTimeout(() => {
      const index = clientesMock.findIndex((cliente) => cliente.id === id);
      if (index !== -1) {
        clientesMock.splice(index, 1);
      }
      resolve();
    }, 500);
  });
};
