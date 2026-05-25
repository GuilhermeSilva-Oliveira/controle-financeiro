# 💰 Controle Financeiro API — Sistema de Controle Financeiro Pessoal
## 📖 Visão Geral
O Financial Control API é uma API REST desenvolvida com Spring Boot focada em gerenciamento financeiro pessoal, organização de despesas e controle de pagamentos do cotidiano.
O projeto nasce com o objetivo de resolver problemas reais de uma pessoa comum, permitindo controlar:

mensalidades da faculdade;
contas de internet;
alimentação;
transporte;
assinaturas;
cartão de crédito;
gastos recorrentes;
despesas parceladas;
histórico financeiro.

Mais do que um simples CRUD financeiro, o sistema busca modelar regras reais de negócio relacionadas a:
vencimentos;
pagamentos;
atrasos;
recorrência;
parcelamentos;
auditoria financeira;
fluxo de status.

A arquitetura será preparada desde o início para futuras expansões como:
investimentos;
patrimônio;
metas financeiras;
inteligência financeira;
dashboards analíticos;
previsão de gastos.

# 🎯 Objetivo do Projeto
O sistema deve ajudar o usuário a responder perguntas como:
“Quanto ainda preciso pagar este mês?”
“Quais contas estão atrasadas?”
“Quanto estou gastando com alimentação?”
“Minha faculdade está em dia?”
“Quanto sobra após as contas fixas?”
“Quais categorias mais consomem meu dinheiro?”
“Quanto já paguei de faculdade este ano?”

# 🧠 Conceito Central
O núcleo do sistema é baseado em lançamentos financeiros.
Tudo dentro da aplicação será tratado como um lançamento:
Tipo	Exemplo
Receita	salário
Despesa fixa	faculdade
Despesa variável	Uber
Assinatura	Spotify
Parcelamento	notebook
Recorrência	internet

# 🏗️ Arquitetura de Negócio
O sistema será dividido em módulos financeiros independentes, porém integrados.
Principais Domínios:
User
├── Accounts
├── Categories
├── Transactions
├── RecurringTransactions
├── Notifications
├── Dashboard
├── Audit
└── Investments (futuro)
👤 Usuário

Representa o dono do controle financeiro.
Responsabilidades
autenticação;
gerenciamento de contas;
gerenciamento de despesas;
preferências financeiras;
controle individual dos dados.

# 🏦 Conta Financeira
Representa onde o dinheiro do usuário está armazenado.
Exemplos
carteira;
conta bancária;
Nubank;
PicPay;
cartão de crédito;
dinheiro físico.
Funcionalidades
saldo atual;
saldo previsto;
histórico;
controle de movimentações.

# 🗂️ Categorias
Responsáveis por organizar receitas e despesas.
Exemplos
Faculdade
Alimentação
Transporte
Moradia
Assinaturas
Saúde
Lazer
Investimentos (futuro)
Regras
categorias podem ser:
padrão do sistema;
personalizadas;
editáveis;
reutilizáveis.

# 💸 Transaction — Lançamento Financeiro
Entidade principal do sistema.
Cada movimentação financeira será representada por um lançamento.
Campos Principais
Campo	Descrição
description	descrição da despesa
amount	valor
type	RECEITA ou DESPESA
category	categoria
status	situação atual
dueDate	vencimento
paymentDate	data de pagamento
account	conta financeira
observation	observações
recurringOrigin	origem recorrente
installment	parcela

# 🔁 RecurringTransaction — Despesas Recorrentes
Um dos pilares do projeto.
Essa entidade será responsável por representar despesas ou receitas fixas, evitando que o usuário precise cadastrar manualmente contas mensais repetitivas.
Exemplos
faculdade;
internet;
aluguel;
academia;
salário;
assinaturas.

# 🧩 Como Funciona a Recorrência
A recorrência NÃO representa diretamente uma despesa.
Ela funciona como um:
Gerador automático de lançamentos financeiros

# 📌 Exemplo Real
Usuário cria:
Campo	Valor
descrição	Faculdade
valor	R$850
frequência	mensal
vencimento	dia 10

O sistema automaticamente:
gera a despesa do mês;
gera as próximas mensalmente;
mantém histórico separado;
controla status individualmente.

# 🧱 Estrutura da Recorrência
Campos importantes
Campo	Objetivo
description	nome da recorrência
amount	valor padrão
frequency	frequência
startDate	início
endDate	término opcional
active	recorrência ativa
nextGenerationDate	próxima geração
account	conta associada
category	categoria

# 🔄 Frequências Suportadas
DAILY
WEEKLY
MONTHLY
YEARLY

# 🧠 Modelagem Correta
A arquitetura seguirá o modelo:
RecurringTransaction
|
| 1:N
v
Transaction

# ✅ Benefícios Dessa Estratégia
histórico completo;
auditoria financeira;
controle individual por mês;
suporte a analytics;
flexibilidade futura;
manutenção simplificada.

# 📌 Exemplo de Histórico
Mês	Status
Janeiro	PAGO
Fevereiro	ATRASADO
Março	PENDENTE
Mesmo sendo originadas da mesma recorrência.

# 🚦 Fluxo de Status
O sistema terá forte controle de estados financeiros.
Status Disponíveis
Status	Significado
PENDENTE	aguardando pagamento
PAGO	pagamento concluído
ATRASADO	vencimento expirado
PARCIAL	pagamento parcial
CANCELADO	lançamento cancelado
AGENDADO	pagamento futuro
RENEGOCIADO	dívida renegociada

# 🔄 Cenários de Alteração de Status
Criação da despesa
Status inicial = PENDENTE
Pagamento total
PENDENTE -> PAGO
Regras
registrar data de pagamento;
atualizar saldo;
gerar auditoria.
Conta vencida
PENDENTE -> ATRASADO
Regras
atualização automática via scheduler;
possibilidade futura de juros.
Pagamento parcial

Exemplo:
faculdade = R$1000;
usuário pagou R$500.
PENDENTE -> PARCIAL
Quitação final
PARCIAL -> PAGO
Renegociação
ATRASADO -> RENEGOCIADO
Cancelamento
PENDENTE -> CANCELADO

# 📆 Contas Fixas
O sistema terá suporte nativo para despesas fixas.

Exemplos
faculdade;
internet;
aluguel;
academia;
assinaturas.
Funcionalidades
geração automática mensal;
atualização automática de vencimentos;
histórico individual por competência;
alteração futura sem impactar lançamentos antigos.

# 💳 Parcelamentos
O sistema permitirá controle de compras parceladas.
Exemplo
Notebook em 10x
Regras
cada parcela possui status próprio;
parcelas geradas automaticamente;
cálculo de parcelas restantes;
rastreabilidade completa.

# 📊 Dashboard Financeiro
O sistema deverá fornecer métricas importantes.
Informações
saldo do mês;
receitas;
despesas;
contas atrasadas;
contas próximas do vencimento;
gastos por categoria;
total de contas fixas;
percentual de consumo.

# 🔔 Sistema de Alertas
Alertas importantes
conta vence amanhã;
conta atrasada;
saldo baixo;
parcela próxima do vencimento.
Futuras expansões
email;
push notification;
WhatsApp.

# 🕓 Scheduler Automático
O sistema possuirá tarefas automáticas executadas diariamente.
Responsabilidades
gerar recorrências;
atualizar status para ATRASADO;
verificar vencimentos;
disparar notificações;
registrar auditoria.

# 🧾 Auditoria Financeira
Toda alteração relevante deve ser registrada.
Eventos auditáveis
alteração de valor;
alteração de status;
renegociação;
alteração de vencimento;
exclusão lógica;
pagamentos.

# 🔐 Segurança
O sistema utilizará:
autenticação JWT;
criptografia de senha;
autorização por usuário;
isolamento de dados financeiros.
⚙️ Tecnologias
Tecnologia	Objetivo
Java 21	linguagem
Spring Boot	framework
Spring Security	autenticação
JWT	autorização
Spring Data JPA	persistência
PostgreSQL	banco de dados
Flyway	migrations
Docker	containerização
Swagger/OpenAPI	documentação
Lombok	produtividade
MapStruct	mapeamento

# 📁 Estrutura Recomendada
financial-control-api
├── auth
├── user
├── account
├── category
├── transaction
├── recurring-transaction
├── installment
├── dashboard
├── notification
├── audit
├── scheduler
├── security
├── common
└── config

# 🚀 Evoluções Futuras
O sistema será preparado para crescimento gradual.
Investimentos
renda fixa;
ações;
criptomoedas;
carteira de investimentos;
dividendos.
Inteligência Financeira
previsão de gastos;
análise comportamental;
sugestão de economia;
score financeiro pessoal.

# 📌 MVP Inicial
O MVP terá foco em resolver problemas reais do cotidiano.
Funcionalidades principais
autenticação;
CRUD de despesas e receitas;
contas fixas recorrentes;
controle de vencimentos;
alteração de status;
dashboard financeiro;
categorização;
scheduler automático;
auditoria básica.

# 🎯 Objetivo Técnico
O objetivo deste projeto não é apenas criar um sistema financeiro simples.
A proposta é construir uma aplicação:
orientada a regras de negócio;
preparada para escalabilidade;
baseada em fluxo de estados;
com rastreabilidade financeira;
modular;
preparada para futuras expansões.

O foco principal estará em:
modelagem financeira;
consistência de dados;
automações;
recorrência;
auditoria;
controle de vencimentos;
experiência real de uso financeiro pessoal.