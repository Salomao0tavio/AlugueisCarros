### História de Usuário 1: Introduzir Pedido

**Como Cliente,**  
Quero introduzir um pedido de aluguel de automóvel,  
Para solicitar o aluguel de um automóvel de acordo com minhas necessidades.

**Critérios de Aceitação:**
- O cliente deve estar logado no sistema para introduzir um pedido.
- O pedido deve incluir todas as informações obrigatórias (dados do cliente, automóvel, tipo de contrato).
- Após a introdução, o pedido é enviado automaticamente para avaliação dos agentes (bancos e empresas).
- O sistema deve confirmar a introdução bem-sucedida do pedido ao cliente.
- **Prioridade:** ALTA

---

### História de Usuário 2: Modificar Pedido

**Como Cliente,**  
Quero modificar um pedido de aluguel que introduzi,  
Para corrigir ou atualizar informações antes da avaliação.

**Critérios de Aceitação:**
- O cliente deve estar logado no sistema para modificar um pedido.
- O cliente só pode modificar pedidos que ainda não foram avaliados pelos agentes.
- As modificações devem ser salvas e atualizadas no sistema.
- O sistema deve notificar o cliente sobre a conclusão da modificação.
- **Prioridade:** MÉDIA

---

### História de Usuário 3: Consultar Pedido

**Como Cliente,**  
Quero consultar o status dos meus pedidos de aluguel,  
Para acompanhar o progresso da minha solicitação de aluguel.

**Critérios de Aceitação:**
- O cliente deve estar logado no sistema para consultar pedidos.
- O cliente pode visualizar o status atual de todos os seus pedidos, incluindo detalhes sobre a avaliação e a concessão de crédito, se aplicável.
- O sistema deve exibir mensagens claras sobre o status de cada pedido (pendente, em avaliação, aprovado, rejeitado).
- **Prioridade:** MÉDIA

---

### História de Usuário 4: Cancelar Pedido

**Como Cliente,**  
Quero cancelar um pedido de aluguel,  
Para retirar minha solicitação antes da execução do contrato.

**Critérios de Aceitação:**
- O cliente deve estar logado no sistema para cancelar um pedido.
- O cliente pode cancelar pedidos que não foram executados e que não tenham concessão de crédito associada.
- O sistema deve atualizar o status do pedido para "cancelado" e notificar o cliente.
- **Prioridade:** BAIXA

---

### História de Usuário 5: Avaliar Pedido

**Como Agente (Banco ou Empresa),**  
Quero avaliar os pedidos de aluguel submetidos pelos clientes,  
Para determinar a viabilidade financeira e operacional da solicitação.

**Critérios de Aceitação:**
- O agente deve estar logado no sistema para avaliar pedidos.
- O agente pode visualizar todos os detalhes do pedido para análise.
- O agente pode aprovar ou rejeitar um pedido após avaliação.
- O sistema deve registrar a avaliação e notificar o cliente sobre a decisão.
- **Prioridade:** ALTA

---

### História de Usuário 6: Conceder Crédito

**Como Banco,**  
Quero conceder crédito para pedidos de aluguel aprovados,  
Para facilitar o financiamento dos automóveis alugados pelos clientes.

**Critérios de Aceitação:**
- O banco deve estar logado no sistema para conceder crédito.
- O banco pode conceder crédito apenas a pedidos que foram avaliados positivamente.
- O sistema deve registrar a concessão de crédito e vincular ao pedido correspondente.
- O cliente deve ser notificado sobre a concessão de crédito e os termos associados.
- **Prioridade:** ALTA
