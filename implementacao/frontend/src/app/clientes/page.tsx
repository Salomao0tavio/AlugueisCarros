"use client";
import { useState, useEffect } from "react";
import Button from "../../components/Button";
import FormInput from "../../components/FormInput";
import { useRouter, useSearchParams } from "next/navigation";
import { FaEdit, FaTrash } from "react-icons/fa";

// Mock de clientes
const clientesMock = [
  { id: 1, nome: "João Silva", cpf: "123.456.789-00" },
  { id: 2, nome: "Maria Oliveira", cpf: "987.654.321-00" },
  { id: 3, nome: "Pedro Santos", cpf: "456.789.123-00" },
];

export default function ClientesPage() {
  const [clientes, setClientes] = useState(clientesMock);
  const [formData, setFormData] = useState({ nome: "", cpf: "" });
  const [isEditMode, setIsEditMode] = useState(false);
  const [editId, setEditId] = useState<number | null>(null);
  const router = useRouter();
  const searchParams = useSearchParams();
  const id = searchParams.get("id");

  useEffect(() => {
    if (id) {
      const clienteToEdit = clientes.find(
        (cliente) => cliente.id === Number(id)
      );
      if (clienteToEdit) {
        setFormData(clienteToEdit);
        setEditId(clienteToEdit.id);
        setIsEditMode(true);
      }
    }
  }, [id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();
    if (isEditMode && editId !== null) {
      setClientes(
        clientes.map((cliente) =>
          cliente.id === editId ? { ...cliente, ...formData } : cliente
        )
      );
      setIsEditMode(false);
      setEditId(null);
    } else {
      const newId = clientes.length + 1;
      setClientes([...clientes, { ...formData, id: newId }]);
    }
    setFormData({ nome: "", cpf: "" });
  };

  const handleDelete = (id: number) => {
    setClientes(clientes.filter((cliente) => cliente.id !== id));
  };

  return (
    <div className="bg-white p-6 rounded-lg shadow-md">
      <h1 className="text-3xl font-bold mb-6 text-gray-800">
        {isEditMode ? "Editar Cliente" : "Adicionar Cliente"}
      </h1>
      <form onSubmit={handleSubmit}>
        <FormInput
          label="Nome"
          name="nome"
          value={formData.nome}
          onChange={handleChange}
          required
        />
        <FormInput
          label="CPF"
          name="cpf"
          value={formData.cpf}
          onChange={handleChange}
          required
        />
        <Button type="submit">
          {isEditMode ? "Salvar Alterações" : "Adicionar Cliente"}
        </Button>
      </form>

      <div className="mt-8">
        <h1 className="text-3xl font-bold mb-6 text-gray-800">
          Lista de Clientes
        </h1>
        <div className="overflow-x-auto">
          <table className="min-w-full border border-gray-200 bg-white shadow-md rounded-lg">
            <thead className="bg-gray-100">
              <tr>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  ID
                </th>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  Nome
                </th>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  CPF
                </th>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  Ações
                </th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200">
              {clientes.map((cliente) => (
                <tr key={cliente.id} className="hover:bg-gray-50">
                  <td className="p-4 text-sm text-gray-700">{cliente.id}</td>
                  <td className="p-4 text-sm text-gray-700">{cliente.nome}</td>
                  <td className="p-4 text-sm text-gray-700">{cliente.cpf}</td>
                  <td className="p-4 flex space-x-2">
                    <Button
                      onClick={() => {
                        router.push(`/clientes?id=${cliente.id}`);
                        setIsEditMode(true);
                      }}
                      className="text-blue-500 hover:text-blue-700 flex items-center space-x-2"
                    >
                      <FaEdit />
                      <span>Editar</span>
                    </Button>
                    <Button
                      onClick={() => handleDelete(cliente.id)}
                      className="text-red-500 hover:text-red-700 flex items-center space-x-2"
                    >
                      <FaTrash />
                      <span>Excluir</span>
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
