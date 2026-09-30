Sistema Bancário API 🏦

Uma API RESTful robusta desenvolvida em Java para simular operações fundamentais de um sistema bancário.

Este projeto foi construído para solidificar conceitos de programação orientada a objetos (POO), arquitetura de software e desenvolvimento de APIs modernas.

🚀 Funcionalidades

Criação e gerenciamento de contas bancárias.

Realização de operações financeiras básicas:

Depósitos

Saques

Transferências entre contas

Consulta de saldos e extratos.

🛠️ Tecnologias Utilizadas

Java: Linguagem de programação principal.

Spring Boot: Framework para simplificar a configuração e publicação da aplicação (se aplicável).

Maven: Gerenciador de dependências e build do projeto.

⚙️ Como Executar Localmente

Clone o repositório para a sua máquina:

git clone https://github.com/MateusStecca/Sistema-Bancario.git


Acesse a pasta do projeto:

cd Sistema-Bancario


Instale as dependências e rode a aplicação usando o Maven Wrapper embutido:

./mvnw spring-boot:run


(No Windows, você pode usar apenas mvnw spring-boot:run)

📚 Estrutura do Projeto

O projeto segue boas práticas de organização de código, separando responsabilidades como:

Models/Entities: Representação dos dados (Conta, Cliente, etc.).

Controllers: Exposição dos endpoints da API (recebimento de requisições web).

Services: Regras de negócio e validações (lógica bancária).