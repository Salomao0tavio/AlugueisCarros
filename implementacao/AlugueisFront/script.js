document.addEventListener("DOMContentLoaded", function () {
    const clientesList = document.getElementById('clientesList');
    const clienteForm = document.getElementById('clienteForm');

    // Função para carregar clientes da API
    function carregarClientes() {
        fetch('http://localhost:8080/clientes')
            .then(response => response.json())
            .then(clientes => {
                clientesList.innerHTML = ''; // Limpa a lista

                clientes.forEach(cliente => {
                    const clienteDiv = document.createElement('div');
                    clienteDiv.innerHTML = `
                        <p><strong>ID:</strong> ${cliente.id}</p>
                        <p><strong>Nome:</strong> ${cliente.nome}</p>
                        <p><strong>CPF:</strong> ${cliente.cpf}</p>
                        <p><strong>Entidade Empregadora:</strong> ${cliente.entidadeEmpregadora}</p>
                        <p><strong>Rendimento:</strong> R$ ${cliente.rendimento.toFixed(2)}</p>
                        <button onclick="deletarCliente(${cliente.id})">Deletar</button>
                        <hr>
                    `;
                    clientesList.appendChild(clienteDiv);
                });
            })
            .catch(error => {
                console.error('Erro ao carregar clientes:', error);
            });
    }

    // Função para deletar cliente da API
    function deletarCliente(id) {
        fetch(`http://localhost:8080/clientes/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                carregarClientes();
            })
            .catch(error => {
                console.error('Erro ao deletar cliente:', error);
            });
    }

    // Função para adicionar cliente via formulário
    clienteForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const cliente = {
            nome: document.getElementById('nome').value,
            rg: document.getElementById('rg').value,
            cpf: document.getElementById('cpf').value,
            endereco: document.getElementById('endereco').value,
            profissao: document.getElementById('profissao').value,
            login: document.getElementById('login').value,
            senha: document.getElementById('senha').value,
            entidadeEmpregadora: document.getElementById('entidadeEmpregadora').value,
            rendimento: parseFloat(document.getElementById('rendimento').value)
        };

        fetch('http://localhost:8080/clientes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(cliente),
        })
            .then(response => response.json())
            .then(data => {
                console.log('Cliente adicionado com sucesso:', data);
                carregarClientes();
                clienteForm.reset();
            })
            .catch(error => {
                console.error('Erro ao adicionar cliente:', error);
            });
    });

    // Carregar a lista de clientes ao carregar a página
    carregarClientes();
});