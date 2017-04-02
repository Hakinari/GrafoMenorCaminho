/**
 * SearchGraphAlgorithms 1.0
 *
 * Is a class containg search methods for graphs. Until now, only contains
 * a single method that uses dijkstra algorithm.
 *
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class SearchGraphAlgorithms {    
    
    /**
     * Calculates the distance and paths from a vertex "v" to other
     * vertices of a graph with double representation of the costs. Returns a
     * SearchInfo object (inner public class) containing information about paths
     * and distances. 
     *
     * It is possible to make two types of consults in a SearchInfo object. The first 
     * is ask about a lowest path from vertex "v" to another one of the graph. And it is
     * possible to ask about the lowest distance between vertex "v" to another vertex.
     * 
     * The kind of the search depends of the TypeSearchEnum.
     *
     * @param type TypeSearchEnum is only Dijkstra
     * @param v the initial vertex of the search
     * @param cost the costs representation between each vertex.
     *  
     * @returns SearchInfo object containing data about the search results.      
     */
    
    public SearchInfo search(TypeSearchEnum type, int v, Graph<Double,Double> cost) {
    	if(type == TypeSearchEnum.DIJKSTRA) {
    		return this.dijkstra(v, cost);
    	}
    	
    	return null;
    }
    
    /**
     * Calculates the lowest distance and paths from a vertex "v" to other
     * vertices of a graph with double representation of the costs. Returns a
     * SearchInfo object (inner public class) containing information about paths
     * and distances. 
     *
     * It is possible to make two types of consults in a SearchInfo object. The first 
     * is ask about a lowest path from vertex "v" to another one of the graph. And it is
     * possible to ask about the lowest distance between vertex "v" to another vertex.
     * 
     * Uses dijkstra algorithm.
     *
     * @param v the initial vertex of the search
     * @param cost the costs representation between each vertex.
     *  
     * @returns SearchInfo object containing data about the search results.      
     */
    private SearchInfo dijkstra(int v, Graph<Double,Double> cost) {        
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
            min = Double.POSITIVE_INFINITY; //Infinity
            
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
        
        SearchInfo si = new SearchInfo(DIST);
        si.buildPaths(previous);
        
        return si;
    } 
    
    /**
     *
     * @author Caio Bomfim Martins
     * @version 1.0
     */
    public class SearchInfo {

        private ArrayList<LinkedList<Integer>> paths;

        private double[] dist;

        private SearchInfo(double[] dist) {
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
		 * Returns the lowest path from vertex "v" (from up class) to vertex "vertex";
		 *
		 * @param vertex the number of the vertex that will be returned the lowest path
		 * 
		 * @returns A linkedList of Integers containing the vertex order in the  lowest path
		 */
        public LinkedList<Integer> getPathFrom(int vertex) {
            return this.paths.get(vertex);
        }

		
		/**
		 * Returns the lowest distance from vertex "v" (from up class) to vertex "vertex";
		 *
		 * @param vertex the number of the vertex that will be returned the lowest distance
		 * 
		 * @returns A double containg the lowest distance
		 */
        public double getDistFrom(int vertex) {
            return this.dist[vertex];
        }
    }
}