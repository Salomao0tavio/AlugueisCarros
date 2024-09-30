import { pedidosMock } from "../mocks/pedidosMock";
import { Pedido } from "../models/Pedido";

export const getPedidos = async () => {
  return new Promise<Pedido[]>((resolve) => {
    setTimeout(() => {
      resolve(pedidosMock);
    }, 500);
  });
};

export const getPedidoById = async (id: number) => {
  return new Promise<Pedido | undefined>((resolve) => {
    setTimeout(() => {
      resolve(pedidosMock.find((pedido) => pedido.id === id));
    }, 500);
  });
};

export const createPedido = async (data: Pedido) => {
  return new Promise<Pedido>((resolve) => {
    setTimeout(() => {
      const newPedido = { ...data, id: pedidosMock.length + 1 };
      pedidosMock.push(newPedido);
      resolve(newPedido);
    }, 500);
  });
};

export const updatePedido = async (id: number, data: Pedido) => {
  return new Promise<Pedido | undefined>((resolve) => {
    setTimeout(() => {
      const index = pedidosMock.findIndex((pedido) => pedido.id === id);
      if (index !== -1) {
        pedidosMock[index] = { ...pedidosMock[index], ...data };
        resolve(pedidosMock[index]);
      } else {
        resolve(undefined);
      }
    }, 500);
  });
};

export const deletePedido = async (id: number) => {
  return new Promise<void>((resolve) => {
    setTimeout(() => {
      const index = pedidosMock.findIndex((pedido) => pedido.id === id);
      if (index !== -1) {
        pedidosMock.splice(index, 1);
      }
      resolve();
    }, 500);
  });
};
