/**
 * BinarySearchTree - Implementasi BST untuk Sistem Manajemen Data Mahasiswa
 *
 * Aturan BST:
 *   - Node kiri memiliki NIM lebih kecil dari root
 *   - Node kanan memiliki NIM lebih besar dari root
 *
 * Cara pakai (gabungkan dengan Hash Table teman):
 *   BST bst = new BST();
 *   bst.insert(12345, "Andi", 3.75);
 *   bst.insert(23456, "Budi", 3.50);
 *   bst.inorderTraversal(bst.root); // tampil data terurut by NIM
 */
public class BST {

    BSTNode root;

    BST() {
        root = null;
    }

    // =============================================
    // INSERT - Tambah mahasiswa ke BST
    // =============================================
    void insert(int nim, String nama, double ipk) {
        root = insertRec(root, nim, nama, ipk);
    }

    private BSTNode insertRec(BSTNode root, int nim, String nama, double ipk) {
        if (root == null)
            return new BSTNode(nim, nama, ipk);
        if (nim < root.nim)
            root.left = insertRec(root.left, nim, nama, ipk);
        else if (nim > root.nim)
            root.right = insertRec(root.right, nim, nama, ipk);
        else
            System.out.println("  [BST] NIM " + nim + " sudah ada.");
        return root;
    }

    // =============================================
    // SEARCH - Cari mahasiswa berdasarkan NIM
    // =============================================
    BSTNode search(BSTNode root, int nim) {
        if (root == null || root.nim == nim)
            return root;
        if (nim < root.nim)
            return search(root.left, nim);
        return search(root.right, nim);
    }

    // =============================================
    // DELETE - Hapus mahasiswa berdasarkan NIM
    // =============================================
    BSTNode delete(BSTNode root, int nim) {
        if (root == null) return null;
        if (nim < root.nim)
            root.left = delete(root.left, nim);
        else if (nim > root.nim)
            root.right = delete(root.right, nim);
        else {
            // Node dengan 0 atau 1 anak
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // Node dengan 2 anak: ganti dengan inorder successor
            BSTNode successor = minValueNode(root.right);
            root.nim  = successor.nim;
            root.nama = successor.nama;
            root.ipk  = successor.ipk;
            root.right = delete(root.right, successor.nim);
        }
        return root;
    }

    private BSTNode minValueNode(BSTNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // =============================================
    // TRAVERSAL
    // =============================================

    // Inorder (Left -> Root -> Right) -> data terurut ascending by NIM
    void inorderTraversal(BSTNode node) {
        if (node == null) return;
        inorderTraversal(node.left);
        System.out.printf("  NIM: %-8d | Nama: %-20s | IPK: %.2f%n",
                node.nim, node.nama, node.ipk);
        inorderTraversal(node.right);
    }

    // Preorder (Root -> Left -> Right) -> struktur tree dari atas
    void preorderTraversal(BSTNode node) {
        if (node == null) return;
        System.out.printf("  NIM: %-8d | Nama: %-20s | IPK: %.2f%n",
                node.nim, node.nama, node.ipk);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    // Postorder (Left -> Right -> Root) -> untuk penghapusan rekursif
    void postorderTraversal(BSTNode node) {
        if (node == null) return;
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.printf("  NIM: %-8d | Nama: %-20s | IPK: %.2f%n",
                node.nim, node.nama, node.ipk);
    }

    // =============================================
    // UTILITAS
    // =============================================

    // Hitung tinggi BST
    int getHeight(BSTNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    // Hitung jumlah node
    int countNodes(BSTNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // Cari mahasiswa dengan IPK tertinggi
    BSTNode findMaxIPK(BSTNode node, BSTNode maxNode) {
        if (node == null) return maxNode;
        if (maxNode == null || node.ipk > maxNode.ipk) maxNode = node;
        maxNode = findMaxIPK(node.left, maxNode);
        maxNode = findMaxIPK(node.right, maxNode);
        return maxNode;
    }

    // Cari mahasiswa dengan IPK terendah
    BSTNode findMinIPK(BSTNode node, BSTNode minNode) {
        if (node == null) return minNode;
        if (minNode == null || node.ipk < minNode.ipk) minNode = node;
        minNode = findMinIPK(node.left, minNode);
        minNode = findMinIPK(node.right, minNode);
        return minNode;
    }
}
