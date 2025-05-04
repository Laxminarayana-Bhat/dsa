package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    //final locks the pointer, not the contents
    private final Map<String, List<String>> adjacencyList = new HashMap<>();

    public boolean addVertex(String vertex) {
        if (adjacencyList.get(vertex) == null) {
            adjacencyList.put(vertex, new ArrayList<>());
            return true;
        }
        return false;
    }

    public void printGraph() {
//        System.out.println("{");
//        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
//            System.out.println(entry);
//        }
//        System.out.println("}");
        System.out.println(adjacencyList);
    }

    public boolean addEdge(String vertex1, String vertex2) {
        if (adjacencyList.get(vertex1) != null && adjacencyList.get(vertex2) != null) {
            adjacencyList.get(vertex1).add(vertex2);
            adjacencyList.get(vertex2).add(vertex1);
            return true;
        }
        return false;
    }

    public boolean removeEdge(String vertex1, String vertex2) {
        if (adjacencyList.get(vertex1) != null && adjacencyList.get(vertex2) != null) {
            adjacencyList.get(vertex1).remove(vertex2);
            adjacencyList.get(vertex2).remove(vertex1);
            return true;
        }
        return false;
    }
}
