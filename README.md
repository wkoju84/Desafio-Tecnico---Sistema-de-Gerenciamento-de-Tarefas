
# Sistema de Gerenciamento de Tarefas Abstrato

## Descrição

Este projeto é uma aplicação web desenvolvida como parte de um desafio técnico, cujo objetivo é permitir que os usuários criem e gerenciem listas de tarefas de forma eficiente. A aplicação permite a criação de listas, gerenciamento de itens dentro dessas listas, visualização, filtragem e priorização dos itens.

## Funcionalidades

1. **Criação de Listas**:
    - O usuário pode criar múltiplas listas de tarefas.
    - Cada lista pode conter vários itens associados.

2. **Gerenciamento de Itens**:
    - Adicionar, editar e remover itens dentro das listas.
    - Alterar o estado de um item (ex: realizado).

3. **Visualização e Filtragem**:
    - Visualizar todas as listas e seus itens de forma organizada.
    - Filtrar itens por prioridade ou nome.

4. **Prioridade de Itens**:
    - Destaque de itens dentro das listas para indicar prioridade.
    - Itens destacados são priorizados na visualização.

## Regras de Negócio

1. **Validação de Dados**:
    - Títulos dos itens devem seguir critérios mínimos, como comprimento mínimo.

2. **Estado dos Itens**:
    - Cada item possui um estado que pode ser alterado pelo usuário (ex: pendente, em andamento, concluído).

3. **Ordenação e Destaque**:
    - Itens destacados são exibidos em primeiro lugar na lista.

## Requisitos Não Funcionais

1. **Persistência de Dados**:
    - As listas e itens são armazenados de forma persistente em um banco de dados relacional.

2. **Exposição de API**:
    - A aplicação fornece uma API para operações CRUD (Create, Read, Update, Delete) das listas e itens.

3. **Testes Automatizados**:
    - Foram implementados testes automatizados para as principais funcionalidades da aplicação.

## Tecnologias Utilizadas

- **Framework de Desenvolvimento**: SpringBoot
- **Banco de Dados**: MySQL/H2
- **Ferramentas de Teste**: JUnit/Mockito

## Configuração e Execução

### Pré-requisitos

- Java 17
- MySQL


## Configuração e Execução

### Pré-requisitos

- **Java 17**
- **MySQL**

### Passos para Configuração

1. **Instalação do Java 17:**

   Para instalar o Java 17, siga os passos abaixo:

    - **No Windows:**
        1. Baixe o instalador do Java 17 do [site oficial da Oracle](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
        2. Execute o instalador e siga as instruções na tela.
        3. Após a instalação, verifique se o Java está instalado corretamente executando o comando no terminal:
           ```bash
           java -version
           ```
        - **No Linux (Ubuntu/Debian):**
            1. Abra o terminal e adicione o repositório do Java:
               ```bash
               sudo add-apt-repository ppa:linuxuprising/java
               sudo apt update
               ```
            2. Instale o Java 17:
               ```bash
               sudo apt install oracle-java17-installer
               ```
            3. Verifique a instalação:
               ```bash
               java -version
               ```
        - **No macOS:**
            1. Instale o Homebrew (se não tiver):
               ```bash
               /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
               ```
            2. Instale o Java 17 via Homebrew:
               ```bash
               brew install openjdk@17
               ```
            3. Verifique a instalação:
               ```bash
               java -version
               ```

2. **Instalação do MySQL:**

   Para instalar o MySQL, siga os passos abaixo:

    - **No Windows:**
        1. Baixe o MySQL Installer do [site oficial](https://dev.mysql.com/downloads/installer/).
        2. Execute o instalador, escolha "Server only" e siga as instruções na tela.
        3. Configure o MySQL conforme necessário e verifique a instalação utilizando o MySQL Workbench ou o terminal:
           ```bash
           mysql -u root -p
           ```
        - **No Linux (Ubuntu/Debian):**
            1. Abra o terminal e atualize os pacotes:
               ```bash
               sudo apt update
               ```
            2. Instale o MySQL:
               ```bash
               sudo apt install mysql-server
               ```
            3. Proteja a instalação do MySQL:
               ```bash
               sudo mysql_secure_installation
               ```
            4. Verifique a instalação:
               ```bash
               sudo systemctl status mysql
               ```
        - **No macOS:**
            1. Instale o MySQL via Homebrew:
               ```bash
               brew install mysql
               ```
            2. Inicie o serviço MySQL:
               ```bash
               brew services start mysql
               ```
            3. Verifique a instalação:
               ```bash
               mysql -u root -p
               ```

3. **Clonando o Repositório:**

   Clone o repositório do projeto usando o comando abaixo:

   ```bash
   git clone https://github.com/wkoju84/Desafio-Tecnico---Sistema-de-Gerenciamento-de-Tarefas.git

4. **Executando a Aplicação:**

   Você pode usar a IDE de sua preferência para inicializar a aplicação. Siga os passos abaixo:

    1. Abra sua IDE (ex: IntelliJ IDEA, Eclipse, VS Code).
    2. Importe o projeto como um projeto Maven ou Gradle (dependendo da configuração).
    3. Certifique-se de que todas as dependências sejam baixadas e que o projeto esteja compilando corretamente.
    4. Navegue até a classe principal do projeto (aquela que contém o método `main`).
    5. Execute a aplicação a partir da IDE, utilizando a opção "Run".


Isso iniciará a aplicação, e você poderá acessá-la através de http://localhost:9000/swagger-ui/index.html#/




