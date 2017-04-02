/*
 * MatGraph é uma classe que implementa a interface Graph <Double, Double>.
  * Não foi concebido para fazer um bom projeto de objeto orientado.
  * Somente para teste.
  *
  * Cria um gráfico de matriz de adjacência a partir de um arquivo com o seguinte formato de exemplo:
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

  A primeira linha deve conter o número "n" de vértices. As outras "n" linhas devem
  Têm "n" elementos. Se não houver nenhuma conexão entre dois vértices, um caractere "*" deve ser digitado.
 */


public class MatGrafo implements Grafo<Double,Double> {
    
    private double[][] cost;
    
    public MatGrafo(String fileName) throws java.io.IOException {
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
