

//ESSE ALGORITMO ELE DA O menor VALOR PARA CHEGaR EM TODOS VERTICES


public class Main {
    public static void main(String[] args) throws java.io.IOException { 
	
        //Cria OBJT DE BUSCA NO GRAFO
		SearchGraphAlgorithms ga = new SearchGraphAlgorithms();

        //CRIA O GRAFO A APRTIR DO TXT MoTHER FUCKER DAORA
        Graph<Double,Double> mg = new MatGraph("graph.txt");

        //Cria um NOVO objeto de busca  de tipo SearchInfo(parece ser da biblioteca do java)
		//usando o objto SearchGraphAlgorithms criado iniciando a busca pelo grafo (aqlgoritmo, inicio, grafo)
        SearchGraphAlgorithms.SearchInfo so = ga.search(TypeSearchEnum.DIJKSTRA, 4, mg);   
        
        System.out.println("A partir do vertice 4: ");
        System.out.println("Menor Caminho para o ");
        for(int i = 0; i < 8; i++) {
            System.out.println("Vertice " + i + " " + so.getPathFrom(i));
        }
        
        System.out.println("\n\nMenor Distancia para o ");
        for(int i = 0; i < 8; i++) {
            System.out.println("Vertice " + i + " " + so.getDistFrom(i));
        }
    }
}
