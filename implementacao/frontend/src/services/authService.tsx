import Cookies from "js-cookie";

// Lista de usuários mock
let mockUsers = [
  {
    username: "usuario_mock",
    password: "senha123",
  },
];

// Função de login
export const login = async (credentials: {
  username: string;
  password: string;
}) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const user = mockUsers.find(
        (user) =>
          user.username === credentials.username &&
          user.password === credentials.password
      );

      if (user) {
        const token = "mocked_token"; // Simulação de token
        // Adiciona o token aos cookies com a flag `secure` para maior segurança
        Cookies.set("token", token, { expires: 1, secure: true });
        resolve({ token });
      } else {
        reject(new Error("Credenciais inválidas"));
      }
    }, 500);
  });
};

// Função de registro (adicionando novos usuários à lista mock)
export const register = async (userData: {
  username: string;
  password: string;
}) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const userExists = mockUsers.some(
        (user) => user.username === userData.username
      );

      if (userExists) {
        reject(new Error("Usuário já existe!"));
      } else {
        mockUsers.push(userData); // Adiciona o novo usuário
        resolve({ message: "Usuário registrado com sucesso!" });
      }
    }, 500);
  });
};

// Função para recuperar senha (mock)
export const recoverPassword = async (email: string) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      // Simulação de envio de email de recuperação de senha
      resolve({ message: "Email de recuperação enviado!" });
    }, 500);
  });
};

// Função para recuperar o token do cookie
export const getToken = () => {
  return Cookies.get("token");
};

// Função de logout
export const logout = () => {
  Cookies.remove("token", { secure: true });
};

// Função para verificar se está autenticado
export const isAuthenticated = () => {
  return !!Cookies.get("token");
};
