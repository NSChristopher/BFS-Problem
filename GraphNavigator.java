import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import Models.Vertex;

public class GraphNavigator {

    private ArrayList<Vertex> graph;
    private Vertex dest;

    private ArrayList<Integer> traversalPath = new ArrayList<>();

    public GraphNavigator() {
    }

    // Ctor for building graph at instantiation.
    public GraphNavigator(ArrayList<Integer> userArr) {
        buildGraph(userArr);
    }

    public void buildGraph(ArrayList<Integer> userArr){
        this.graph = new ArrayList<Vertex>();
        this.dest = new Vertex();

        for(int i = 0; i < userArr.size(); i++) {
            ArrayList<Integer> edges = new ArrayList<>();
            int value = userArr.get(i);

            if(value != 0) {
                int firstEdge = i + value;
                if(firstEdge < userArr.size()){
                    edges.add(firstEdge);
                }
    
                int secoundEdge = i - value;
                if(secoundEdge >= 0){
                    edges.add(secoundEdge);
                }
                graph.add(new Vertex(value, edges));
            }
            else {
                graph.add(dest);
            }

            
        }
    }

    // Used for testing
    public void printGraph() {
        for (int i = 0; i < graph.size(); i++) {
            System.out.println(graph.get(i).getValue());
            for (Integer u : graph.get(i).getEdges()) {
                System.out.print(u);
            }
            System.out.println();
        }
    }

    public void DFS(Integer v) {

        if(!dest.isVisited()) {
            traversalPath.add(v);
            System.out.println(v);
        }

        Vertex vertex = graph.get(v);
        vertex.isVisited(true);

        for (Integer u : vertex.getEdges()) {
            if(!graph.get(u).isVisited()) {
                DFS(u);
            }
        }
    }

    public ArrayList<Integer> getTraversalPath() {
        return traversalPath;
    }

    public Boolean isSolved(){
        return dest.isVisited();
    }
}
