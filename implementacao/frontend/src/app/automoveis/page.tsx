"use client";
import { useState, useEffect } from "react";
import Button from "../../components/Button";
import FormInput from "../../components/FormInput";
import { useRouter, useSearchParams } from "next/navigation";
import { FaEdit, FaTrash } from "react-icons/fa";
import { Automovel } from "../../models/Automovel";
import { automoveisMock } from "../../mocks/automoveisMock";

export default function AutomoveisPage() {
  const [automoveis, setAutomoveis] = useState<Automovel[]>(automoveisMock);
  const [formData, setFormData] = useState<Automovel>({
    marca: "",
    modelo: "",
    ano: 0,
  });
  const [isEditMode, setIsEditMode] = useState(false);
  const [editId, setEditId] = useState<number | null>(null);
  const router = useRouter();
  const searchParams = useSearchParams();
  const id = searchParams.get("id");

  useEffect(() => {
    if (id) {
      const automovelToEdit = automoveis.find(
        (automovel) => automovel.id === Number(id)
      );
      if (automovelToEdit) {
        setFormData(automovelToEdit);
        setEditId(automovelToEdit.id!);
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
      setAutomoveis(
        automoveis.map((automovel) =>
          automovel.id === editId ? { ...automovel, ...formData } : automovel
        )
      );
      setIsEditMode(false);
      setEditId(null);
    } else {
      const newId = automoveis.length + 1;
      setAutomoveis([...automoveis, { ...formData, id: newId }]);
    }
    setFormData({ marca: "", modelo: "", ano: 0 });
  };

  const handleDelete = (id: number) => {
    setAutomoveis(automoveis.filter((automovel) => automovel.id !== id));
  };

  return (
    <div className="bg-white p-6 rounded-lg shadow-md">
      <h1 className="text-3xl font-bold mb-6 text-gray-800">
        {isEditMode ? "Editar Automóvel" : "Adicionar Automóvel"}
      </h1>
      <form onSubmit={handleSubmit}>
        <FormInput
          label="Marca"
          name="marca"
          value={formData.marca}
          onChange={handleChange}
          required
        />
        <FormInput
          label="Modelo"
          name="modelo"
          value={formData.modelo}
          onChange={handleChange}
          required
        />
        <FormInput
          label="Ano"
          name="ano"
          value={formData.ano.toString()} // Ajustando para string no campo input
          onChange={handleChange}
          required
        />
        <Button type="submit">
          {isEditMode ? "Salvar Alterações" : "Adicionar Automóvel"}
        </Button>
      </form>

      <div className="mt-8">
        <h1 className="text-3xl font-bold mb-6 text-gray-800">
          Lista de Automóveis
        </h1>
        <div className="overflow-x-auto">
          <table className="min-w-full border border-gray-200 bg-white shadow-md rounded-lg">
            <thead className="bg-gray-100">
              <tr>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  ID
                </th>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  Marca
                </th>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  Modelo
                </th>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  Ano
                </th>
                <th className="p-4 text-left text-sm font-medium text-gray-500 uppercase">
                  Ações
                </th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200">
              {automoveis.map((automovel) => (
                <tr key={automovel.id} className="hover:bg-gray-50">
                  <td className="p-4 text-sm text-gray-700">{automovel.id}</td>
                  <td className="p-4 text-sm text-gray-700">
                    {automovel.marca}
                  </td>
                  <td className="p-4 text-sm text-gray-700">
                    {automovel.modelo}
                  </td>
                  <td className="p-4 text-sm text-gray-700">{automovel.ano}</td>
                  <td className="p-4 flex space-x-2">
                    <Button
                      onClick={() => {
                        router.push(`/automoveis?id=${automovel.id}`);
                        setIsEditMode(true);
                      }}
                      className="text-blue-500 hover:text-blue-700 flex items-center space-x-2"
                    >
                      <FaEdit />
                      <span>Editar</span>
                    </Button>
                    <Button
                      onClick={() => handleDelete(automovel.id!)}
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
