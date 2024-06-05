# Fluxo Basico
1. A classe APP.java é executada
2. A classe CLILogin.java é executada
3. A classe CLIMenu.java é executada
4. O usuario escolhe a opção desejada que pode ser: Fabricante, Produto, Estoque, Funcionario, Representante, Movimentação ou Sair



### Elementos do Diagrama

- **Ator (Usuario)**: Representa o usuário que interage com o sistema.
- **Participantes (Classes)**:
    - `APP`: Classe principal que inicia o programa.
    - `CLILogin`: Classe responsável pelo processo de login.
    - `CLIMenu`: Classe responsável por exibir o menu principal após o login.
    - `CLIFabricante`, `CLIProduto`, `CLIEstoque`, `CLIFuncionario`, `CLIRepresentante`, `CLIMovimentacao`: Classes responsáveis pelas operações CRUD para cada entidade.

### Fluxo de Execução

1. **Início do Programa**
    
    - O usuário inicia o programa executando a classe `APP`.
    - A classe `APP` então chama a classe `CLILogin` para iniciar o processo de login.
2. **Processo de Login**
    
    - A `CLILogin` solicita ao usuário que insira suas credenciais.
    - O usuário fornece suas credenciais (usuário e senha).
    - A `CLILogin` verifica as credenciais do usuário.
    - Se as credenciais são válidas, a `CLILogin` informa à `APP` que o login foi bem-sucedido.
3. **Menu Principal**
    
    - A `APP` chama a classe `CLIMenu` para exibir o menu principal.
    - A `CLIMenu` mostra as opções disponíveis no menu para o usuário.
4. **Interação com o Menu**
    
    - O usuário seleciona uma opção no menu.
    - Dependendo da opção selecionada, a `CLIMenu` chama a classe correspondente:
        - **Fabricante**: Chama a `CLIFabricante`.
        - **Produto**: Chama a `CLIProduto`.
        - **Estoque**: Chama a `CLIEstoque`.
        - **Funcionario**: Chama a `CLIFuncionario`.
        - **Representante**: Chama a `CLIRepresentante`.
        - **Movimentação**: Chama a `CLIMovimentacao`.
        - **Sair**: Finaliza a execução do programa.
5. **Operações CRUD **
    
    - **Operações CRUD**:
        - Para cada entidade (`Fabricante`, `Produto`, `Estoque`, `Funcionario`, `Representante`, `Movimentacao`), a classe de inteface grafica correspondente (`CLIFabricante`, `CLIProduto`, `CLIEstoque`, `CLIFuncionario`, `CLIRepresentante`, `CLIMovimentacao`) interage com o usuário para realizar operações de Create, Read, Update e Delete.
        - O usuário escolhe a operação desejada e fornece as informações necessárias.
        - A classe processa a operação e atualiza o banco de dados conforme necessário.
6. **Encerramento**
    
    - Se o usuário seleciona a opção "Sair", a `CLIMenu` informa à `APP` para finalizar a execução do programa.

### Detalhamento das Interações

#### Login

- **Usuario -> APP**: O usuário inicia o programa executando `APP.java`.
- **APP -> CLILogin**: A `APP` chama `CLILogin` para o processo de login.
- **CLILogin -> Usuario**: A `CLILogin` solicita as credenciais do usuário.
- **Usuario -> CLILogin**: O usuário fornece suas credenciais.
- **CLILogin -> CLILogin**: A `CLILogin` verifica as credenciais.
- **CLILogin -> APP**: A `CLILogin` informa que as credenciais são válidas.

#### Menu Principal e Opções

- **APP -> CLIMenu**: A `APP` chama `CLIMenu` para exibir o menu principal.
- **CLIMenu -> Usuario**: A `CLIMenu` mostra as opções do menu.
- **Usuario -> CLIMenu**: O usuário seleciona uma opção.
- **CLIMenu -> [Classe Correspondente]**: Dependendo da opção, a `CLIMenu` chama a classe correspondente para operações CRUD.

#### Operações CRUD

#### Tipos de Operações CRUD

1. **Cadastrar <Entidade>**:
    
    - Inserir um novo registro no banco de dados.
    - Exemplo: Cadastrar um novo produto com informações como nome, preço e quantidade.
2. **Consultar <Entidade>**:
    
    - Buscar e exibir informações detalhadas de um registro específico.
    - Exemplo: Consultar informações detalhadas de um fabricante pelo seu ID.
3. **Atualizar <Entidade>**:
    
    - Modificar informações de um registro existente.
    - Exemplo: Atualizar o preço de um produto específico.
4. **Excluir <Entidade>**:
    
    - Remover um registro do banco de dados.
    - Exemplo: Excluir um funcionário pelo seu ID.
5. **Listar <Entidade>**:
    
    - Exibir uma lista de todos os registros da entidade.
    - Exemplo: Listar todos os produtos cadastrados no sistema.
6. **Relatório sobre <Entidade>**:
    
    - Gerar um relatório com informações agregadas e análises sobre a entidade.
    - Exemplo: Gerar um relatório de movimentação de estoque, mostrando entradas e saídas de produtos.

#### Etapas CRUD
- **CLIMenu -> [Classe Correspondente]**: Chama a classe correspondente (`CLIFabricante`, `CLIProduto`, etc.).
- **[Classe Correspondente] -> Usuario**: Interage com o usuário para a operação CRUD.
- **Usuario -> [Classe Correspondente]**: Fornece informações para a operação.
- **[Classe Correspondente] -> [Classe Correspondente]**: Processa a operação e atualiza o banco de dados.




 
#### Etapas das Operações CRUD

1. **CLIMenu -> [Classe Correspondente]**:
    
    - O menu principal (`CLIMenu`) redireciona a execução para a classe responsável pela entidade selecionada (`CLIFabricante`, `CLIProduto`, etc.).
2. **[Classe Correspondente] -> Usuario**:
    
    - A classe correspondente interage com o usuário, solicitando as informações necessárias para a operação desejada (Cadastro, Consulta, Atualização, Exclusão, Listagem, Relatório).
3. **Usuario -> [Classe Correspondente]**:
    
    - O usuário fornece as informações solicitadas. Por exemplo, ao cadastrar um novo produto, o usuário insere dados como nome, preço, e quantidade.
4. **[Classe Correspondente] -> [Classe Correspondente]**:
    
    - A classe processa a operação solicitada e realiza a atualização no banco de dados.
    - Exemplo de processamento:
        - **Cadastrar**: Inserir um novo registro na tabela correspondente.
        - **Consultar**: Buscar o registro pelo ID e exibir suas informações.
        - **Atualizar**: Modificar os dados do registro existente com as novas informações fornecidas.
        - **Excluir**: Remover o registro do banco de dados.
        - **Listar**: Recuperar todos os registros e exibi-los em uma lista.
        - **Relatório**: Agregar dados e gerar um relatório com as informações solicitadas.

### Exemplo de Fluxo de Cadastro

1. **Usuário seleciona "Cadastrar Produto" no Menu**:
    - **CLIMenu -> CLIProduto**: O menu principal chama `CLIProduto`.
    - **CLIProduto -> Usuario**: `CLIProduto` solicita as informações do novo produto (nome, preço, quantidade).
    - **Usuario -> CLIProduto**: O usuário fornece as informações.
    - **CLIProduto -> CLIProduto**: `CLIProduto` insere as informações no banco de dados e confirma a operação para o usuário.

### Exemplo de Fluxo de Relatório

1. **Usuário seleciona "Relatório de Movimentação" no Menu**:
    - **CLIMenu -> CLIMovimentacao**: O menu principal chama `CLIMovimentacao`.
    - **CLIMovimentacao -> Usuario**: `CLIMovimentacao` solicita parâmetros para o relatório (por exemplo, período de tempo).
    - **Usuario -> CLIMovimentacao**: O usuário fornece os parâmetros.
    - **CLIMovimentacao -> CLIMovimentacao**: `CLIMovimentacao` processa os dados, gera o relatório e exibe para o usuário.