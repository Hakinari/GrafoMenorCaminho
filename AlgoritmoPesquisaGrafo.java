/*
 * AlgoritmoPesquisaGrafic
 * É uma classe que contém métodos de pesquisa para gráficos. Até agora, só contém 
 * um único método que usa o algoritmo dijkstra.
 
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class AlgoritmoPesquisaGrafo {    
    
    /**
      * Calcula a distância e caminhos de um vértice "v" para outro
      * Vértices de um gráfico com dupla representação dos custos. Retorna um
      * Objeto SearchInfo (classe pública interna) contendo informações sobre caminhos
      * E distâncias.
      *
      * É possível fazer dois tipos de consultas em um objeto SearchInfo. O primeiro
      * É perguntar sobre um caminho mais baixo do vértice "v" para outro do gráfico. E isso é
      * Possível perguntar sobre a menor distância entre o vértice "v" para outro vértice.
      *
      * O tipo de pesquisa depende do TypeSearchEnum.
      *
      * @param tipo PesquisaTipoEnum é apenas Dijkstra
      * @param v o vértice inicial da pesquisa
      * @param custa a representação de custos entre cada vértice.
      *
      * @retorna o objeto PesquisaInfo contendo dados sobre os resultados da pesquisa.
     */
    
    public PesquisaInfo pequisa(PesquisaTipoEnum type, int v, Grafo<Double,Double> cost) {
    	if(type == PesquisaTipoEnum.DIJKSTRA) {
    		return this.dijkstra(v, cost);
    	}
    	
    	return null;
    }
    
    /**
      * Calcula a menor distância e caminhos de um vértice "v" para outro
      * Vértices de um gráfico com dupla representação dos custos. Retorna um
      * Objeto PesquisaInfo (classe pública interna) contendo informações sobre caminhos
      * E distâncias.
      *
      * É possível fazer dois tipos de consultas em um objeto SearchInfo. O primeiro
      * É perguntar sobre um caminho mais baixo do vértice "v" para outro do gráfico. E isso é
      * Possível perguntar sobre a menor distância entre o vértice "v" para outro vértice.
      *
      * Usa o algoritmo dijkstra.
      *
      * @param v o vértice inicial da pesquisa
      * @param custa a representação de custos entre cada vértice.
      *
          * @retorna o objeto PesquisaInfo contendo dados sobre os resultados da pesquisa.
     */
    private PesquisaInfo dijkstra(int v, Grafo<Double,Double> cost) {        
        boolean[] visited = new boolean[cost.size()]; //CRIA UM VETOR PARA DETERMINAR TODOS VÈRTICES QUE JÀ FORAM VISITADOS
        int[] previous = new int[cost.size()]; //VETOR PARA DEFINIR O HISTORICO DE VÈRTICES VISITADOS
        double[] DIST = new double[cost.size()];
        double min = Float.MAX_VALUE; 
        
        int i;        
        
        for ( i = 0; i < visited.length; i++ ) {          
            DIST[i] = cost.get(v, i); 
        }
        
        previous[v] = -1;
        visited[v] = true;
        DIST[v] = 0;
        
        int u = 0;
        for ( i = 0; i < DIST.length; i++ ) {
            if ( min > DIST[i] && !visited[i]) {
                min = DIST[i];
                u = i;
            }
        }
        
        previous[u] = v;
        int num = 2;
        
        while ( num < visited.length ) {
            min = Double.POSITIVE_INFINITY; //infinito
            
            for ( i = 0; i < DIST.length; i++ ) {
                if ( min > DIST[i] && !visited[i]) {
                    min = DIST[i];
                    u = i;
                }
            }
            
            visited[u] = true;
        
            num++;
            
            for ( i = 0; i < visited.length; i++ ) {                
                if ( !visited[i] ) {                                  
                    if ( DIST[i] > DIST[u] + cost.get(u, i) ) {  
                        DIST[i] = DIST[u] + cost.get(u, i);                         
                        previous[i] = u;
                    }
                }
            }            
        }
        
        PesquisaInfo si = new PesquisaInfo(DIST);
        si.buildPaths(previous);
        
        return si;
    } 
    
   
    public class PesquisaInfo {

        private ArrayList<LinkedList<Integer>> paths;

        private double[] dist;

        private PesquisaInfo(double[] dist) {
            paths = new ArrayList<LinkedList<Integer>>(dist.length);

            for ( int i  = 0; i < dist.length; i++ ) {
                paths.add(new LinkedList<Integer>());
            }

            this.dist = dist;
        }

        private void buildPaths(int[] before) {
            int i = 0;
            int j;
            while ( i < before.length ) {
                j = before[i];
                if ( j == -1 ) {
                    this.paths.get(i).addFirst(i);
                    i++;
                    continue;
                }

                this.paths.get(i).addFirst(i);
                this.paths.get(i).addFirst(j);

                while ( j != -1) {
                    if ( before[j] == -1) {
                        break;
                    }

                    this.paths.get(i).addFirst(before[j]);

                    j = before[j];
                }

                i++;                
            }
        }

		/**
		 * Retorna o caminho mais baixo do vértice "v" (da classe up) para o vértice "vértice";
                 *
                 * @param vertex o número do vértice que será retornado o caminho mais baixo
                 *
                 * @returns Uma lista vinculada de inteiros que contém a ordem do vértice no caminho mais baixo
		 */
        public LinkedList<Integer> getPathFrom(int vertex) {
            return this.paths.get(vertex);
        }

		
		/**
		* Retorna a menor distância do vértice "v" (da classe up) para o vértice "vértice";
                *
                * @param vertex o número do vértice que será retornado a menor distância
                *
                * @returns Um duplo contendo a menor distância
		*/
        public double getDistanciaDe(int vertex) {
            return this.dist[vertex];
        }
    }
}