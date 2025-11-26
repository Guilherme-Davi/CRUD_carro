# CRUD_carro
Create, read, update and delete para carros(marca, modelo e ano)

Este trabalho tem como objetivo desenvolver uma aplicação desktop em Java que realize operações de CRUD (criar, listar, atualizar e remover) sobre registros de veículos, armazenados em memória. A aplicação foi estruturada de forma semelhante ao padrão MVC, separando a parte de dados (Model), a lógica de negócio (Controller) e a interface gráfica (View).

A classe Carro representa o Model do sistema. Ela contém os atributos privados id, modelo, marca e ano, além de construtor, métodos getters e setters e o método toString(). O toString() foi sobrescrito para retornar uma string formatada com os dados do veículo, permitindo que os objetos sejam exibidos corretamente na lista gráfica(Sem o toString() estava bugando).

A classe CarroControlador funciona como o Controller. Ela mantém uma lista de carros em memória, utilizando ArrayList<Carro>. Nessa classe foram implementados os métodos do CRUD: adicionar(Carro), listar(), atualizar(int id, Carro carroAtualizado) e remover(int id). O ID de cada carro é gerado automaticamente por meio de um contador interno, garantindo identificadores únicos independentemente da posição do objeto na lista. O método listar() devolve uma cópia da lista, evitando modificações externas na estrutura interna.

A classe TelaCarro representa a View, construída com a biblioteca Swing. A interface possui campos de texto para entrada de marca, modelo e ano, botões para adicionar, listar, atualizar e remover, e um componente JList para exibir os veículos cadastrados. Foi utilizado um DefaultListModel<Carro> para atualizar dinamicamente os itens mostrados na lista. A tela não contém lógica de negócio: ao clicar nos botões, ela apenas lê os valores digitados, cria ou atualiza objetos Carro e chama os métodos correspondentes do CarroControlador. Após cada operação, os campos são limpos e são exibidas mensagens de confirmação ou erro usando JOptionPane.

COMO EXECUTAR O PROJETO?
1. Instalar o Java (JDK 17 ou superior)
Certifique-se de que o ambiente possui o JDK configurado no PATH.

2. Clonar ou baixar o repositório
Pelo Git:
git clone https://github.com/seuUsuario/seuRepositorio.git
Ou baixar o ZIP pelo GitHub e extrair.

3. Abrir o projeto em uma IDE compatível com Swing como IntelliJ IDEA (recomendado e usado no projeto)

4. Executar o Main.java
