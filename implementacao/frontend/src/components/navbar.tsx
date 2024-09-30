import Link from "next/link";
import {
  FaHome,
  FaUsers,
  FaCar,
  FaClipboardList,
  FaRocket,
  FaSignInAlt,
  FaUserPlus,
  FaSignOutAlt,
} from "react-icons/fa";
import { useEffect, useState } from "react";
import { getToken, logout } from "../services/authService";
import { useRouter } from "next/navigation";
import "../styles/globals.css";

export default function Navbar() {
  const [isLoggedIn, setIsLoggedIn] = useState(true);
  const router = useRouter();

  // Verifica se o token está presente para definir o estado de login
  useEffect(() => {
    const token = getToken();
    setIsLoggedIn(!!token);
  }, []);

  // Função para logout
  const handleLogout = () => {
    logout();
    setIsLoggedIn(false);
    router.push("/auth/login");
  };

  return (
    isLoggedIn && (
      <nav className="bg-white text-darkGray h-screen w-64 shadow-md flex flex-col">
        <div className="p-6 flex items-center justify-center border-b">
          <div className="flex items-center space-x-2">
            <div className="bg-primary text-white rounded-full p-2">
              <FaRocket className="text-white text-xl" />
            </div>
            <span className="text-xl font-semibold">iCar</span>
          </div>
        </div>

        <ul className="flex flex-col space-y-4 p-4 flex-grow">
          <li>
            <Link
              href="/dashboard"
              className="flex items-center space-x-2 hover:text-primary"
            >
              <FaHome className="text-primary" />
              <span>Dashboard</span>
            </Link>
          </li>
          <li>
            <Link
              href="/clientes"
              className="flex items-center space-x-2 hover:text-primary"
            >
              <FaUsers className="text-primary" />
              <span>Clientes</span>
            </Link>
          </li>
          <li>
            <Link
              href="/pedidos"
              className="flex items-center space-x-2 hover:text-primary"
            >
              <FaClipboardList className="text-primary" />
              <span>Pedidos</span>
            </Link>
          </li>
          <li>
            <Link
              href="/automoveis"
              className="flex items-center space-x-2 hover:text-primary"
            >
              <FaCar className="text-primary" />
              <span>Automóveis</span>
            </Link>
          </li>
        </ul>

        <div className="p-4 border-t">
          <button
            className="flex items-center space-x-2 text-red-500 hover:text-red-700 w-full"
            onClick={handleLogout}
          >
            <FaSignOutAlt />
            <span>Sair</span>
          </button>
        </div>
      </nav>
    )
  );
}
