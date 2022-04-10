import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import Models.Vertex;

public class GraphNavigator {

    private ArrayList<Vertex> graph;

    public void buildGraph(ArrayList<Integer> userArr){
        this.graph = new ArrayList<Vertex>();

        ArrayList<Integer> edges = new ArrayList<>();

        for(int i = 0; i < userArr.size(); i++) {
            edges.clear();
            int value = userArr.get(i);

            int firstEdge = i + value;
            if(firstEdge < userArr.size()){
                System.out.println(firstEdge);
                edges.add(firstEdge);
            }

            int secoundEdge = i - value;
            if(secoundEdge >= 0){
                System.out.println(secoundEdge);
                edges.add(secoundEdge);
            }
            graph.add(new Vertex(value, edges));
        }
    }

    public void visualDFS(Integer v){

        Vertex vertex = graph.get(v);

        vertex.isVisited(true);
        System.out.print(v);

        if (vertex.getValue() == 0){
            System.out.println("Found " + vertex.getValue());
            return;
        }
        for (Integer u : vertex.getEdges()) {
            if(!graph.get(u).isVisited()) {
                visualDFS(u);
            }
        }
    }
}
