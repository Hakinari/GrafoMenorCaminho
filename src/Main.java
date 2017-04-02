

//ESSE ALGORITMO ELE DA O menor VALOR PARA CHEGaR EM TODOS VERTICES


public class Main {
    public static void main(String[] args) throws java.io.IOException { 
	
        //Cria OBJT DE BUSCA NO GRAFO
		AlgoritmoPesquisaGrafo ga = new AlgoritmoPesquisaGrafo();

        //CRIA O GRAFO A APRTIR DO TXT 
        Graph<Double,Double> mg = new MatGrafo("src//grafo.txt");

        //Cria um NOVO objeto de busca  de tipo SearchInfo(parece ser da biblioteca do java)
		//usando o objto AlgoritmoPesquisaGrafo criado iniciando a busca pelo grafo (aqlgoritmo, inicio, grafo)
        AlgoritmoPesquisaGrafo.PesquisaInfo so = ga.pequisa(PesquisaTipoEnum.DIJKSTRA, 4, mg);   
        
        System.out.println("A partir do vertice 4: ");
        System.out.println("Menor Caminho para o ");
        for(int i = 0; i < 8; i++) {
            System.out.println("Vertice " + i + " " + so.getPathFrom(i));
        }
        
        System.out.println("\n\nMenor Distancia para o ");
        for(int i = 0; i < 8; i++) {
            System.out.println("Vertice " + i + " " + so.getDistanciaDe(i));
        }
    }
}
