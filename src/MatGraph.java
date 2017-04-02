/*
 * MatGraph is a class that implements the interface Graph<Double,Double>.
 * It was not intended to make a good oriented-object design.
 * Only for test. 
 *
 * Creates an adjacency matrix graph from a file with the follow example format:
 *
 *  
 	8
	0 * * * * * * *
	30 0 * * * * * *
	100 80 0 * * * * *
	* * 120 0 * * * *
	* * * 150 0 25 * * 
	* * * 100 * 0 90 140
	* * * * * * 0 100
	170 * * * * * * 0
	
	The first line must contains the number "n" of vertices. The other "n" lines must
	have "n" elements. If there is no conexion between two vertices a single char "*" must be typed.
 */

/**
 *
 * @author Caio Bomfim Martins
 * @version 1.0
 */
public class MatGraph implements Graph<Double,Double> {
    
    private double[][] cost;
    
    public MatGraph(String fileName) throws java.io.IOException {
        java.io.BufferedReader br = new java.io.BufferedReader(
        new java.io.FileReader(new java.io.File(fileName)));
        
        int length = Integer.parseInt(br.readLine());
        
        cost = new double[length][length];
        
        String[] line;
        
        for ( int i = 0; i < cost.length; i++ ) {
            line = br.readLine().split(" ");
            
            for ( int j = 0; j < cost.length; j++ ) {
                if (line[j].compareTo("*") == 0) {
                    cost[i][j] = Float.MAX_VALUE;
                    
                } else {
                    cost[i][j] = Float.parseFloat(line[j]);
                  }               
            }
        }
        
        br.close();
    }
    
    public Double get(int i, int j) {
        return Double.valueOf(cost[i][j]);
    }
    
    public int size() {
        return cost.length;
    }
}
