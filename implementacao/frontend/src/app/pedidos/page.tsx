"use client";
import { useState, useEffect } from "react";
import Button from "../../components/Button";
import FormInput from "../../components/FormInput";
import { useRouter, useSearchParams } from "next/navigation";
import { FaEdit, FaTrash } from "react-icons/fa";
import { Pedido } from "../../models/Pedido";
import { Cliente } from "../../models/Cliente";
import { pedidosMock } from "../../mocks/pedidosMock";
import { clientesMock } from "../../mocks/clientesMock"; // Importando o mock de clientes

export default function PedidosPage() {
  const [pedidos, setPedidos] = useState<Pedido[]>(pedidosMock);
  const [clientes, setClientes] = useState<Cliente[]>(clientesMock); // Estado para armazenar clientes
  const [formData, setFormData] = useState<Pedido>({
    clienteId: 0,
    status: "",
    data: "",
  });
  const [isEditMode, setIsEditMode] = useState(false);
  const [editId, setEditId] = useState<number | null>(null);
  const router = useRouter();
  const searchParams = useSearchParams();
  const id = searchParams.get("id");

  useEffect(() => {
    if (id) {
      const pedidoToEdit = pedidos.find((pedido) => pedido.id === Number(id));
      if (pedidoToEdit) {
        setFormData(pedidoToEdit);
        setEditId(pedidoToEdit.id!);
        setIsEditMode(true);
      }
    }
  }, [id]);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();
    if (isEditMode && editId !== null) {
      setPedidos(
        pedidos.map((pedido) =>
          pedido.id === editId
            ? { ...pedido, ...formData, clienteId: Number(formData.clienteId) }
            : pedido
        )
      );
      setIsEditMode(false);
      setEditId(null);
    } else {
      const newId = pedidos.length + 1;
      setPedidos([
        ...pedidos,
        { ...formData, id: newId, clienteId: Number(formData.clienteId) },
      ]);
    }
    setFormData({ clienteId: 0, status: "", data: "" });
  };

  const handleDelete = (id: number) => {
    setPedidos(pedidos.filter((pedido) => pedido.id !== id));
  };

  return (
    <div className="bg-white p-6 rounded-lg shadow-md">
      <h1 className="text-3xl font-bold mb-6 text-gray-800">
        {isEditMode ? "Editar Pedido" : "Adicionar Pedido"}
      </h1>
      <form onSubmit={handleSubmit}>
        {/* Campo de seleção de cliente */}
        <div className="mb-4">
          <label className="block text-gray-700 mb-2">Cliente</label>
          <select
            name="clienteId"
            value={formData.clienteId}
            onChange={handleChange}
            className="border border-gray-300 p-2 rounded-md w-full"
            required
          >
            <option value="">Selecione um cliente</option>
            {clientes.map((cliente) => (
              <option key={cliente.id} value={cliente.id}>
                {cliente.nome}
              </option>
            ))}
          </select>
        </div>
        <FormInput
          label="Status"
          name="status"
          value={formData.status}
          onChange={handleChange}
          required
        />
        <FormInput
          label="Data"
          name="data"
          value={formData.data}
          onChange={handleChange}
          required
        />
        <Button type="submit">
          {isEditMode ? "Salvar Alterações" : "Adicionar Pedido"}
        </Button>
      </form>

      <div className="mt-8">
        <h1 className="text-3xl font-bold mb-6 text-gray-800">
          Lista de Pedidos
        </h1>
        <div className="overflow-x-auto">
          <table className="min-w-full border border-gray-200 bg-white shadow-md rounded-lg">
            <thead className="bg-gray-100">
              <tr>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  ID
                </th>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  Cliente
                </th>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  Status
                </th>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  Data
                </th>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  Ações
                </th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200">
              {pedidos.map((pedido) => {
                const cliente = clientes.find((c) => c.id === pedido.clienteId);
                return (
                  <tr key={pedido.id} className="hover:bg-gray-50">
                    <td className="p-4 text-sm text-gray-700">{pedido.id}</td>
                    <td className="p-4 text-sm text-gray-700">
                      {cliente?.nome || "Cliente não encontrado"}
                    </td>
                    <td className="p-4 text-sm text-gray-700">
                      {pedido.status}
                    </td>
                    <td className="p-4 text-sm text-gray-700">{pedido.data}</td>
                    <td className="p-4 flex space-x-2">
                      <Button
                        onClick={() => {
                          router.push(`/pedidos?id=${pedido.id}`);
                          setIsEditMode(true);
                        }}
                        className="text-blue-500 hover:text-blue-700 flex items-center space-x-2"
                      >
                        <FaEdit />
                        <span>Editar</span>
                      </Button>
                      <Button
                        onClick={() => handleDelete(pedido.id!)}
                        className="text-red-500 hover:text-red-700 flex items-center space-x-2"
                      >
                        <FaTrash />
                        <span>Excluir</span>
                      </Button>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
