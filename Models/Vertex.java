package Models;
import java.util.ArrayList;

public class Vertex {
    private Integer value;
    private Boolean isVisited = false;
    private ArrayList<Integer> edges;

    public Vertex() {
        edges = new ArrayList<Integer>();
    }

    public Vertex(Integer value, ArrayList<Integer> edges) {
        this.value = value;
        this.edges = edges;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ArrayList<Integer> getEdges() {
        return this.edges;
    }

    public void setEdges(ArrayList<Integer> edges) {
        this.edges = edges;
    }

    public void addEdge(Integer edge) {
        edges.add(edge);
    }

    public void isVisited(boolean bool){
        this.isVisited = bool;
    }

    public Boolean isVisited(){
        return isVisited;
    }
}
