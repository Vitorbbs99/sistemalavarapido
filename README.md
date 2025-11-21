# Sistema de Lava-Rápido (Java + PostgreSQL)

Projeto para gerenciamento de veículos para um lava-rápido. O sistema funciona via JavaFX.

![Painel do Sistema](./capa.png)

## 📌 Funcionalidades

* Login de usuário (com validação no PostgreSQL)
* Cadastro de veículos
* Listagem de veículos cadastrados
* Edição de veículos existentes
* Exclusão de veículos
* Cadastro de clientes

## 🧱 Estrutura do Projeto

O sistema segue uma arquitetura simples usando separação por camadas:

* **model/** → Contém as classes de modelo (ex.: `Veiculo`, `Usuario`)
* **dao/** → Contém as classes responsáveis pelo acesso ao banco (DAO)
* **view/** → Telas e menus do sistema (cadastro, listagem, edição etc.)
* **database/** → Classe de conexão com o PostgreSQL (`ConexaoBanco`)

## 🛠️ Tecnologias Utilizadas

* **Java 21+**
* **JavaFX 21**
* **Java Dotenv 5.2**
* **Kotlin stdlib 1.9**
* **Jackson Databind 5.16**
* **PostgreSQL**
* **Driver JDBC PostgreSQL**

## ▶️ Como Rodar o Projeto

1. Instale o PostgreSQL e configure um banco.
2. Ajuste a classe `ConexaoBanco` com suas credenciais.
3. Certifique-se de ter o driver JDBC do PostgreSQL no classpath.
4. Crie uma pasta "libs" e insira os JAR.

## 📌 Observações

* O projeto está em desenvolvimento.
