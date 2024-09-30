"use client";
import { useState } from "react";
import { login, register, recoverPassword } from "../../services/authService"; // Ajuste para as três funcionalidades
import Button from "../../components/Button";
import FormInput from "../../components/FormInput";
import { useRouter } from "next/navigation";

export default function AuthPage() {
  const [isLogin, setIsLogin] = useState(true); // Define se é login ou registro
  const [isForgotPassword, setIsForgotPassword] = useState(false); // Define se é recuperação de senha
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    email: "",
  });
  const [error, setError] = useState("");
  const [message, setMessage] = useState("");
  const router = useRouter();

  // Reset de mensagens de erro quando o input muda
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    setError(""); // Remove a mensagem de erro ao alterar o input
    setMessage(""); // Limpa mensagem de sucesso
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (isForgotPassword) {
        await recoverPassword(formData.email); // Simula recuperação de senha
        setMessage("Verifique seu email para redefinir a senha.");
        setIsForgotPassword(false); // Volta para login após recuperação
      } else if (isLogin) {
        await login(formData); // Simula login
        router.push("/dashboard");
      } else {
        await register(formData); // Simula registro
        router.push("/dashboard");
      }
    } catch (err) {
      setError(
        isForgotPassword
          ? "Falha na recuperação de senha."
          : isLogin
          ? "Login falhou. Verifique suas credenciais."
          : "Registro falhou. Tente novamente."
      );
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50">
      <div className="bg-white shadow-lg rounded-lg p-8 max-w-md w-full">
        <h1 className="text-2xl font-semibold text-center text-gray-800 mb-6">
          {isForgotPassword
            ? "Recuperar Senha"
            : isLogin
            ? "Login"
            : "Registrar"}
        </h1>

        {/* Exibição de erros e mensagens */}
        {error && <p className="text-red-500 text-center mb-4">{error}</p>}
        {message && (
          <p className="text-green-500 text-center mb-4">{message}</p>
        )}

        {/* Formulário */}
        <form onSubmit={handleSubmit} className="space-y-4">
          {isForgotPassword ? (
            <FormInput
              label="Email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          ) : (
            <>
              <FormInput
                label="Username"
                name="username"
                value={formData.username}
                onChange={handleChange}
                required
              />
              <FormInput
                label="Password"
                name="password"
                type="password"
                value={formData.password}
                onChange={handleChange}
                required
              />
            </>
          )}

          {/* Botão de ação */}
          <Button
            type="submit"
            className="w-full py-2 rounded-md bg-blue-600 text-white hover:bg-blue-700 transition"
          >
            {isForgotPassword
              ? "Recuperar Senha"
              : isLogin
              ? "Entrar"
              : "Registrar"}
          </Button>
        </form>

        {/* Alternativas de login, registro ou recuperação de senha */}
        <div className="mt-2 text-center text-gray-600">
          {isForgotPassword ? (
            <p>
              Lembrou sua senha?{" "}
              <span
                onClick={() => setIsForgotPassword(false)}
                className="text-blue-500 cursor-pointer"
              >
                Fazer login
              </span>
            </p>
          ) : (
            <>
              {isLogin ? (
                <>
                  <p>
                    Não tem uma conta?{" "}
                    <span
                      onClick={() => setIsLogin(false)}
                      className="text-blue-500 cursor-pointer"
                    >
                      Registrar-se
                    </span>
                  </p>
                  <p>
                    Esqueceu sua senha?{" "}
                    <span
                      onClick={() => setIsForgotPassword(true)}
                      className="text-blue-500 cursor-pointer"
                    >
                      Recuperar Senha
                    </span>
                  </p>
                </>
              ) : (
                <p>
                  Já tem uma conta?{" "}
                  <span
                    onClick={() => setIsLogin(true)}
                    className="text-blue-500 cursor-pointer"
                  >
                    Fazer login
                  </span>
                </p>
              )}
            </>
          )}
        </div>
      </div>
    </div>
  );
}
