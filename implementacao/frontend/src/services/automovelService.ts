import { automoveisMock } from "../mocks/automoveisMock";
import { Automovel } from "../models/Automovel";

export const getAutomoveis = async () => {
  return new Promise<Automovel[]>((resolve) => {
    setTimeout(() => {
      resolve(automoveisMock);
    }, 500);
  });
};

export const getAutomovelById = async (id: number) => {
  return new Promise<Automovel | undefined>((resolve) => {
    setTimeout(() => {
      resolve(automoveisMock.find((automovel) => automovel.id === id));
    }, 500);
  });
};

export const createAutomovel = async (data: Automovel) => {
  return new Promise<Automovel>((resolve) => {
    setTimeout(() => {
      const newAutomovel = { ...data, id: automoveisMock.length + 1 };
      automoveisMock.push(newAutomovel);
      resolve(newAutomovel);
    }, 500);
  });
};

export const updateAutomovel = async (id: number, data: Automovel) => {
  return new Promise<Automovel | undefined>((resolve) => {
    setTimeout(() => {
      const index = automoveisMock.findIndex(
        (automovel) => automovel.id === id
      );
      if (index !== -1) {
        automoveisMock[index] = { ...automoveisMock[index], ...data };
        resolve(automoveisMock[index]);
      } else {
        resolve(undefined);
      }
    }, 500);
  });
};

export const deleteAutomovel = async (id: number) => {
  return new Promise<void>((resolve) => {
    setTimeout(() => {
      const index = automoveisMock.findIndex(
        (automovel) => automovel.id === id
      );
      if (index !== -1) {
        automoveisMock.splice(index, 1);
      }
      resolve();
    }, 500);
  });
};
