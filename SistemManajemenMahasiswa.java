/*
    Cedric Sebastian - 2902735603
    DAVID MARSEN PURBA - 2802527513
    LULU NUR AINI - 2902722342 
    MUHAMMAD RAFLI - 2902724291
    SHAHIRA ALYA MUJAHIDAH - 2902721945
*/

/*
 * SistemManajemenMahasiswa - Simulasi sistem manajemen mahasiswa menggunakan berbagai struktur data
 * 
 * Fitur:
 * 1. Graph menggunakan Algorithm Dijkstra untuk simulasi jalur terpendek antar node (misalnya, lokasi rumah antar mahasiswa).
 * 2. Hash Table untuk penyimpanan cepat berdasarkan NIM.
 * 3. Binary Search Tree (BST) untuk penyimpanan terurut berdasarkan NIM.
 * Program ini mencakup operasi dasar seperti insert, search, delete, dan update data mahasiswa.
 *  
 * Cara pakai:
 * - Jalankan program, dan data mahasiswa akan otomatis ditambahkan ke Hash Table dan BST.
 * - Lihat output di console untuk hasil operasi yang dilakukan.
 * Catatan: Data mahasiswa yang digunakan dalam program ini adalah contoh dan dapat diubah sesuai kebutuhan.
 */

public class SistemManajemenMahasiswa {
    public static void main(String[] args) {

        // ======= HASH TABLE =======
        HashTable hashTable = new HashTable(10);

        System.out.println("======= TAMBAH DATA MAHASISWA =======");
        hashTable.insert(12344, "Andika",  3.60);
        hashTable.insert(12345, "Andi",    3.75);
        hashTable.insert(12346, "Cedric",  3.97);
        hashTable.insert(12347, "David",   3.90);
        hashTable.insert(12348, "Lulu",    3.95);
        hashTable.insert(12349, "Rafli",   3.97);
        hashTable.insert(12350, "Shahira", 3.98);
        hashTable.insert(12351, "Teresa",  3.65);
        hashTable.insert(12352, "Vioka",   3.70);
        hashTable.insert(12353, "Xavier",  3.50);
        hashTable.insert(12354, "Wagner",  3.85);
        hashTable.insert(12355, "Yvonne",  3.80);
        hashTable.display();

        System.out.println("\n======= SEARCH DATA =======");
        hashTable.search(12345);
        hashTable.search(99999);

        System.out.println("\n======= UPDATE DATA =======");
        hashTable.update(12346, "Budi Santoso", 3.80);

        System.out.println("\n======= DELETE DATA =======");
        hashTable.delete(12350);
        hashTable.delete(99999);

        System.out.println("\nTotal data mahasiswa: " + hashTable.getTotalMahasiswa());

        // ======= BST =======
        BST bst = new BST();

        System.out.println("\n======= INSERT KE BST =======");
        bst.insert(12344, "Andika",  3.60);
        bst.insert(12345, "Andi",    3.75);
        bst.insert(12346, "Cedric",  3.97);
        bst.insert(12347, "David",   3.90);
        bst.insert(12348, "Lulu",    3.95);
        bst.insert(12349, "Rafli",   3.97);
        bst.insert(12350, "Shahira", 3.98);
        bst.insert(12351, "Teresa",  3.65);
        bst.insert(12352, "Vioka",   3.70);
        bst.insert(12353, "Xavier",  3.50);
        bst.insert(12354, "Wagner",  3.85);
        bst.insert(12355, "Yvonne",  3.80);

        System.out.println("\n======= INORDER TRAVERSAL BST =======");
        bst.inorderTraversal(bst.root);

        System.out.println("\n======= SEARCH DI BST =======");
        BSTNode hasil = bst.search(bst.root, 12347);
        if (hasil != null)
            System.out.println("Ditemukan: NIM " + hasil.nim +
                               ", Nama: " + hasil.nama +
                               ", IPK: " + hasil.ipk);

        System.out.println("\n======= DELETE DI BST =======");
        bst.root = bst.delete(bst.root, 12350);
        System.out.println("NIM 12350 dihapus dari BST.");

        System.out.println("\n======= INORDER SETELAH DELETE =======");
        bst.inorderTraversal(bst.root);

        System.out.println("\nTinggi BST  : " + bst.getHeight(bst.root));
        System.out.println("Jumlah Node : " + bst.countNodes(bst.root));

        DijkstraGraph graph = new DijkstraGraph();
        
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 5);
        graph.addEdge("B", "D", 10);
        graph.addEdge("C", "E", 3);
        graph.addEdge("E", "D", 4);
        graph.addEdge("D", "F", 11);
        graph.addEdge("E", "F", 2);
        graph.addEdge("A", "F", 15);
        graph.addEdge("B", "E", 1);
        graph.addEdge("C", "B", 8);
        graph.addEdge("D", "B", 7);

        graph.computeShortestPath("A", "C");
        graph.computeShortestPath("B", "E");
        graph.computeShortestPath("E", "D");
        graph.computeShortestPath("C", "A");
        graph.computeShortestPath("D", "F");
    }
}