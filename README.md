# GrafoMenorCaminho
Algoritmo usado para Matéria de Teoria dos grafos.
UNIP - Alphaville

Usamos como base para o grafo, o caso de uma rede com N's roteadores dentro dela, o algoritmo irá calcular a partir que o pacote passou chegou no primeiro roteador em rede, até o ultimo antes do detino. Não é levado em consideração o tempo do Host até o primeiro roteador.
No fim vamos dar um ponto de Inicio, e mostraremos o menor caminho e a distacia para todos os roteadores ou vétices,como um pacote distribuido em broadcast.


Decidimos que poderiamos criar um projeto que poderia ser reutilizado mias pra frente.
Fizemos a implementação de uma classe "TypeSearchEnum" podemos listar todos algoritmos buscam um determinado caminho em um grafo.
Essa classe é usada apenas pela classe main(ou classe principal). Onde é iniciado um objeto da classe "SearchGraphAlgorithms" (Algoritmo de pesquisa no grafo)

Para criar o grafo fizemos uma interface "Graph", para projetos futuros implementar novos tipos de grafos a partir dela.
Criamos a classe "MatGraph" apartir da interface "Graph".Essa classe cria a matriz, lendo e identificando um arquivo txt, como um "desenho da matriz" com o seguinte conteúdo:

8
0 * * * * * * *
30 0 * * * * * *
100 80 0 * * * * *
* * 120 0 * * * *
* * * 150 0 25 * * 
* * * 100 * 0 90 140
* * * * * * 0 100
170 * * * * * * 0


Só pode ser uma matriz quadrada.
Onde a primeira linha que dizer ao seu indíce, 8x8
e ná ordem da matriz onde os as vértices não tem conexão é usado "*".


