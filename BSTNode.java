/**
 * BSTNode - Mewakili satu node dalam Binary Search Tree (BST)
 * Setiap node menyimpan data mahasiswa berupa NIM (sebagai key)
 * dan referensi ke anak kiri (left) dan anak kanan (right)
 */
public class BSTNode {
    int nim;          // NIM digunakan sebagai key untuk pengurutan BST
    String nama;      // Nama mahasiswa
    double ipk;       // IPK mahasiswa

    BSTNode left, right; // Referensi ke anak kiri dan kanan

    // Constructor untuk membuat node baru
    BSTNode(int nim, String nama, double ipk) {
        this.nim = nim;
        this.nama = nama;
        this.ipk = ipk;
        this.left = null;
        this.right = null;
    }
}
