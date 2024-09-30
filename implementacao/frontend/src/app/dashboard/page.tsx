"use client";

import { FaUsers, FaClipboardList, FaCar } from "react-icons/fa";

export default function DashboardPage() {
  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-4">Dashboard</h1>
      <div className="grid grid-cols-3 gap-6">
        <div className="bg-white shadow rounded-lg p-6">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-sm font-medium text-gray-600">Clientes</p>
              <p className="text-3xl font-bold text-blue-500">120</p>
            </div>
            <FaUsers className="text-gray-400 text-4xl" />
          </div>
          <div className="mt-4">
            <a
              href="/clientes"
              className="text-sm text-blue-500 hover:underline"
            >
              Ver Clientes
            </a>
          </div>
        </div>

        <div className="bg-white shadow rounded-lg p-6">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-sm font-medium text-gray-600">Pedidos</p>
              <p className="text-3xl font-bold text-blue-500">80</p>
            </div>
            <FaClipboardList className="text-gray-400 text-4xl" />
          </div>
          <div className="mt-4">
            <a
              href="/pedidos"
              className="text-sm text-blue-500 hover:underline"
            >
              Ver Pedidos
            </a>
          </div>
        </div>

        <div className="bg-white shadow rounded-lg p-6">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-sm font-medium text-gray-600">Automóveis</p>
              <p className="text-3xl font-bold text-blue-500">30</p>
            </div>
            <FaCar className="text-gray-400 text-4xl" />
          </div>
          <div className="mt-4">
            <a
              href="/automoveis"
              className="text-sm text-blue-500 hover:underline"
            >
              Ver Automóveis
            </a>
          </div>
        </div>
      </div>
    </div>
  );
}
