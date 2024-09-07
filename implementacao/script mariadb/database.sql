CREATE DATABASE sistema_aluguel;
USE sistema_aluguel;

CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    rg VARCHAR(20),
    cpf VARCHAR(14) NOT NULL,
    endereco VARCHAR(255),
    profissao VARCHAR(100)
);

CREATE TABLE Rendimento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entidadeEmpregadora VARCHAR(255),
    valor DECIMAL(10, 2),
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);

CREATE TABLE Cliente (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Usuario(id)
);






CREATE TABLE Empresa (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Veiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(20),
    ano INT,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    placa VARCHAR(10)
);

CREATE TABLE Contrato (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('CLIENTE_PROPRIETARIO', 'EMPRESA_PROPRIETARIA', 'BANCO_PROPRIETARIO'),
    dataInicio DATE,
    dataFim DATE,
    valor DECIMAL(10, 2),
    agente_id INT,
    FOREIGN KEY (agente_id) REFERENCES Usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE PedidoAluguel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data DATE,
    status ENUM('PENDENTE', 'APROVADO', 'REJEITADO', 'CANCELADO'),
    cliente_id INT,
    veiculo_id INT,
    contrato_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (veiculo_id) REFERENCES Veiculo(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (contrato_id) REFERENCES Contrato(id) ON DELETE CASCADE ON UPDATE CASCADE
);


ALTER TABLE Contrato ADD CONSTRAINT fk_pedido_aluguel FOREIGN KEY (id) REFERENCES PedidoAluguel(id);
SHOW TABLES;
