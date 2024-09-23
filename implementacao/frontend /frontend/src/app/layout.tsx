import "../styles/globals.css"; // Importa os estilos globais

export const metadata = {
  title: "Sistema de Gestão de Clientes",
  description: "CRUD simples de clientes com Next.js e TailwindCSS",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="pt-br">
      <body className="bg-gray-100 min-h-screen flex flex-col">
        {/* Cabeçalho com navegação */}
        <header className="bg-gray-800 text-white p-4 shadow-md">
          <div className="container mx-auto">
            <h1 className="text-xl font-bold text-center">Locadora</h1>
          </div>
        </header>

        {/* Conteúdo principal (onde o CRUD será renderizado) */}
        <main className="flex-1 container mx-auto py-8">
          <div className="bg-white shadow-lg rounded-lg p-6">
            {children} {/* Aqui vai o conteúdo das páginas */}
          </div>
        </main>

        {/* Rodapé */}
        <footer className="bg-gray-800 text-white p-4">
          <div className="container mx-auto text-center">
            <p>Sistema de Gestão &copy; 2024</p>
          </div>
        </footer>
      </body>
    </html>
  );
}
