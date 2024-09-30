"use client";
import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { isAuthenticated } from "@/services/authService"; // Verifica autenticação
import Navbar from "@/components/navbar"; // Importa o Navbar
import "../styles/globals.css";

export default function Layout({ children }: { children: React.ReactNode }) {
  const [isLoggedIn, setIsLoggedIn] = useState(true);
  const router = useRouter();

  // Verificação se o usuário está autenticado
  useEffect(() => {
    if (isAuthenticated()) {
      setIsLoggedIn(true); // Define o estado como logado
    } else {
      router.push("/auth"); // Redireciona para a tela de login se não autenticado
    }
  }, [router]);

  return (
    <html>
      <body className="flex">
        {/* O Navbar será sempre exibido após o login */}
        {isLoggedIn && <Navbar />}
        <main className="flex-1 bg-gray-100 p-8 min-h-screen">{children}</main>
      </body>
    </html>
  );
}
