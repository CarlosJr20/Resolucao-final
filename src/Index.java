

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Index {
    private int[][] Matriz;
    private int quantidade;
    private Scanner input;
    private int VerticesIs;
    private int VerticesCont;


    public Index(Scanner seach) {
        this.input = seach;
        this.lineIndex();
        
    }
    private void lineIndex() {
        this.VerticesCont = Integer.parseInt(this.input.nextLine());
        this.quantidade = Integer.parseInt(this.input.nextLine());
        this.Matriz = new int[this.VerticesCont][this.quantidade];

        this.Matriz = setAdjacencyMatrixValues(this.Matriz, this.input);
        this.VerticesIs = isolatedVerticesCounter();
    }
    private int[][] setAdjacencyMatrixValues(int[][] adjacencyMatriz, Scanner seach) {
        while (seach.hasNextLine()) {
            String line = seach.nextLine();
            String[] array = line.split(" ");
            int originVertice = Integer.parseInt(array[0]);
            int destineVertice = Integer.parseInt(array[1]);
            adjacencyMatriz[originVertice][destineVertice] = 1;
        }
        return adjacencyMatriz;
    }
    private int isolatedVerticesCounter() {
        boolean isIsolatedVertice;
        int conte = 0;
        for (int i = 0; i < this.Matriz.length; i++) {
            isIsolatedVertice = true;
            for (int j = 0; j < this.Matriz.length; j++) {
                if (this.Matriz[i][j] == 1 || this.Matriz[j][i] == 1)
                    isIsolatedVertice = false;
            }
            if (isIsolatedVertice == true)
                conte++;
        }
        return conte;
    }
    public void getEntryDegree() {
        int conte = 0;
        for (int i = 0; i < this.Matriz.length; i++) {
            System.out.print("Entry degree of '" + i + "': ");
            for (int j = 0; j < this.Matriz.length; j++) {
                if (this.Matriz[j][i] != 0)
                    conte++;
            }
            System.out.println(conte);
            conte = 0;
        }
    }
    public int[][] getAdjacencyMatrix() {
        return this.Matriz;
    }
    public int getVerticesQuantity() {
        return VerticesCont;
    }
    public int getIsolatedVertices() {
        return VerticesIs;
    }
    public int getEdgesQuantity() {
        return quantidade;
    }
    public static void main(String[] args) throws Exception {
        Index Index;
        int[][] adjacencyMatrix;
        int edgesQuantity, verticesQuantity, isolatedVertices;
        Index = new Index(readFile());
        adjacencyMatrix = Index.getAdjacencyMatrix();
        edgesQuantity = Index.getEdgesQuantity();
        verticesQuantity = Index.getVerticesQuantity();
        isolatedVertices = Index.getIsolatedVertices();
        System.out.println();
        showAdjacencyMatrix(adjacencyMatrix);
        System.out.println();
        System.out.println("Ordem do gráfico: " + edgesQuantity);
        System.out.println("Tamanho do gráfico: " + verticesQuantity);
        System.out.println("Grau de entrada de todos os vértices: ");
        Index.getEntryDegree();
        System.out.println();
        System.out.println("Número de vértices isolados: " + isolatedVertices);
        System.out.println("Em um grafo direcionado, o número de vértices das extremidades é nulo");
    }
    private static void showAdjacencyMatrix(int[][] adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[0].length; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static Scanner readFile() throws FileNotFoundException {
        String seach = "pequenoG.txt";
        FileReader reader = new FileReader(seach);
        return new Scanner(reader).useDelimiter("\\n");
    }
}