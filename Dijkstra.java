package pkg1;

import java.util.*;

public class Dijkstra {

    List<List<Integer>> graph;
    boolean[] visited;

    public Dijkstra(List<List<Integer>> g) {
        this.graph = g;
        this.visited = new boolean[graph.size()];
    }

    // Helper class to store (node, distance) pairs for priority queue
    static class Node implements Comparable<Node> {
        int index;
        int distance;

        Node(int i, int d) {
            index = i;
            distance = d;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public List<Integer> perform_dijk(int source) {
        int n = graph.size();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            res.add(Integer.MAX_VALUE);
        }

        res.set(source, 0);
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(source, 0));

        while (!q.isEmpty()) {
            Node current = q.poll();
            int u = current.index;

            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                int weight = graph.get(u).get(v);
                if (weight > 0 && !visited[v]) {
                    int newDist = res.get(u) + weight;
                    if (newDist < res.get(v)) {
                        res.set(v, newDist);
                        q.add(new Node(v, newDist));
                    }
                }
            }
        }

        return res;
    }
}