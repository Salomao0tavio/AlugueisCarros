import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";
import { getToken } from "@/services/authService"; // Pega o token de autenticação

// Definindo as rotas públicas
const publicRoutes = ["/auth/login", "/auth/register", "/auth/forgot-password"];

export function middleware(request: NextRequest) {
  const token = getToken(); // Pegando o token (simulação para o mock)

  // Se o usuário está autenticado e tenta acessar login ou register, redireciona para o dashboard
  if (token && publicRoutes.includes(request.nextUrl.pathname)) {
    return NextResponse.redirect(new URL("/dashboard", request.url));
  }

  // Se o usuário não está autenticado e tenta acessar uma rota protegida, redireciona para login
  if (!token && !publicRoutes.includes(request.nextUrl.pathname)) {
    return NextResponse.redirect(new URL("/auth/login", request.url));
  }

  // Se o usuário está autenticado ou está acessando uma rota pública, permite continuar
  return NextResponse.next();
}

// Define as rotas nas quais o middleware será aplicado
export const config = {
  matcher: [
    "/dashboard",
    "/automoveis/:path*",
    "/pedidos/:path*",
    "/clientes/:path*",
  ],
};
