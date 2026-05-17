/**
 * DijkstraGraph - Simulasi jalur terpendek menggunakan Algorithm Dijkstra
 */
import java.util.*;

/**
 * Merepresentasikan sebuah edge berbobot antara dua node.
 */
class Edge {
    String destination;
    int weight;

    public Edge(String destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

/**
 * Merepresentasikan sebuah node dalam queue prioritas untuk algoritma Dijkstra.
 */
class Node implements Comparable<Node> {
    String name;
    int distance;

    public Node(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.distance, other.distance);
    }
}

/**
 * Implementasi graph menggunakan adjacency list untuk menghitung jalur terpendek.
 * Graph ini mendukung penambahan edge dan perhitungan jalur terpendek dari satu node ke node lainnya menggunakan algoritma Dijkstra.
 */
public class DijkstraGraph {
    private final Map<String, List<Edge>> adjList;

    /**
     * Konstruktor untuk inisialisasi graph dengan adjacency list kosong.
     */
    public DijkstraGraph() {
        this.adjList = new HashMap<>();
    }

    /**
     * Menambahkan edge berbobot ke dalam graph. Graph ini bersifat tidak berarah, sehingga edge akan ditambahkan ke kedua node.
     *
     * @param src    Node asal.
     * @param dest   Node tujuan.
     * @param weight Jarak perjalanan.
     */
    public void addEdge(String src, String dest, int weight) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(new Edge(dest, weight));
        adjList.get(dest).add(new Edge(src, weight)); // Undirected graph
    }

    /**
     * Menghitung dan mencetak jalur terpendek dari sumber ke tujuan menggunakan algoritma Dijkstra.
     *
     * @param start Node asal.
     * @param end   Node tujuan.
     */
    public void computeShortestPath(String start, String end) {
        if (!adjList.containsKey(start) || !adjList.containsKey(end)) {
            System.out.println("Node tidak ditemukan.");
            return;
        }

        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (String vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }

        distances.put(start, 0);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            String u = current.name;

            if (u.equals(end)) {
                break;
            }

            if (current.distance > distances.get(u)) {
                continue;
            }

            for (Edge edge : adjList.get(u)) {
                String v = edge.destination;
                int newDist = distances.get(u) + edge.weight;

                if (newDist < distances.get(v)) {
                    distances.put(v, newDist);
                    previous.put(v, u);
                    pq.add(new Node(v, newDist));
                }
            }
        }

        printPath(start, end, distances, previous);
    }

    private void printPath(String start, String end, Map<String, Integer> distances, Map<String, String> previous) {
        if (distances.get(end) == Integer.MAX_VALUE) {
            System.out.println("Tidak ada jalur dari " + start + " ke " + end);
            return;
        }

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        System.out.println("Masukkan titik awal: " + start);
        System.out.println("Masukkan titik tujuan: " + end);
        System.out.print("Jalur terpendek ditemukan: " + path.get(0));
        for (int i = 1; i < path.size(); i++) {
            System.out.print(" -> " + path.get(i));
        }
        System.out.println("\nJarak total: " + distances.get(end) + " km\n");
    }
}