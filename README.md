<h1 align="center">Projeto Sistema de Financeiro</h1>

<p align="center">O projeto foi desenvolvido como parte de um teste técnico para a empresa TGID, utilizando a linguagem Java. A estrutura do código foi projetada com foco na manutenibilidade, empregando uma classe abstrata de usuário. Essa abordagem permite uma fácil extensão e modificação do código no futuro.

A aplicação é executada via console, onde o cliente interage digitando os dados necessários para realizar operações financeiras. As operações disponíveis incluem depósitos e saques, cada um sujeito a uma taxa específica definida pela empresa.</p>

## Detalhes Técnicos:

- Linguagem de Programação: Java
- Arquitetura: Utilização de uma classe abstrata para representar o usuário, facilitando a implementação de diferentes tipos de usuários no futuro.
- Interface: Console, onde o cliente insere os dados e escolhe as operações a serem realizadas.
## Funcionalidades:
- Depósitos: O cliente pode realizar depósitos na conta, com uma taxa de depósito aplicada.
- Saques: O cliente pode realizar saques da conta, com uma taxa de saque aplicada.
- Manutenibilidade: A utilização de uma classe abstrata permite que o código seja facilmente mantido e expandido, adicionando novos tipos de usuários ou modificando as operações existentes sem grandes refatorações.

## Fluxo de Operação:
- Entrada de Dados: O cliente insere seus dados no console.
- Escolha da Operação: O cliente escolhe entre realizar um depósito ou saque.
- Processamento: A aplicação processa a operação escolhida, aplicando as taxas correspondentes.
- Confirmação: O resultado da operação é exibido no console, confirmando o sucesso ou falha da transação.




