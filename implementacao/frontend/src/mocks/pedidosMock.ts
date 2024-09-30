import { Pedido } from "../models/Pedido";

export const pedidosMock: Pedido[] = [
  { id: 1, clienteId: 1, status: "Em andamento", data: "2024-09-01" },
  { id: 2, clienteId: 2, status: "Concluído", data: "2024-09-05" },
  { id: 3, clienteId: 3, status: "Cancelado", data: "2024-09-10" },
];
