package pkg1;

import java.util.*;

public class TopoSort {
    ArrayList<ArrayList<Integer>> graph;
    List<String> nodeNames;
    Map<String, Integer> nameToIndex;
    boolean[] visited;

    public TopoSort(List<String> nodeNames) {
        this.nodeNames = nodeNames;
        this.nameToIndex = new HashMap<>();
        this.graph = new ArrayList<>();

        // Initialize graph and mapping
        for (int i = 0; i < nodeNames.size(); i++) {
            nameToIndex.put(nodeNames.get(i), i);
            graph.add(new ArrayList<>(Collections.nCopies(nodeNames.size(), 0)));
        }

        this.visited = new boolean[nodeNames.size()];
    }

    public void addEdge(String from, String to) {
        int fromIndex = nameToIndex.get(from);
        int toIndex = nameToIndex.get(to);
        graph.get(fromIndex).set(toIndex, 1);
    }

    public LinkedList<String> sort() {
        LinkedList<String> sortedList = new LinkedList<>();
        Arrays.fill(visited, false);

        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                dfs(i, sortedList);
            }
        }

        return sortedList;
    }

    private void dfs(int node, LinkedList<String> sortedList) {
        visited[node] = true;

        for (int i = 0; i < graph.get(node).size(); i++) {
            if (graph.get(node).get(i) == 1 && !visited[i]) {
                dfs(i, sortedList);
            }
        }

        sortedList.addFirst(nodeNames.get(node));
    }
}