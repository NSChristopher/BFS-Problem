import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GraphNavigator gn = new GraphNavigator();

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(3);
        arr.add(6);
        arr.add(4);
        arr.add(1);
        arr.add(3);
        arr.add(4);
        arr.add(2);
        arr.add(5);
        arr.add(3);
        arr.add(0);

        gn.buildGraph(arr);

        gn.visualDFS(0);
    }
}
