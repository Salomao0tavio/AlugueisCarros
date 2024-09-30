"use client";

import { useRouter } from "next/navigation";
import { useEffect } from "react";
import { isAuthenticated } from "@/services/authService";

export default function HomePage() {
  const router = useRouter();

  // Verifica se o usuário está logado ou não e redireciona
  useEffect(() => {
    if (isAuthenticated()) {
      router.push("/dashboard"); // Se o usuário está logado, vai para o dashboard
    } else {
      router.push("/auth"); // Se não está logado, redireciona para a tela de login
    }
  }, [router]);

  return null; // Pode exibir uma tela de carregamento aqui enquanto redireciona
}
