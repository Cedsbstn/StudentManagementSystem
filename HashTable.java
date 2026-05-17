import java.util.*;

// =============================================
// Class HashTable - Chaining dengan LinkedList
// =============================================
class HashTable {
    private LinkedList<BSTNode>[] table; // Array of linkedLists untuk menyimpan
    private int size; // Ukuran Hash Table

    // Konstruktor untuk inisialisasi Hash Table
    @SuppressWarnings("unchecked")
    HashTable(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>(); // Inisialisasi setiap indeks dengan LinkedList kosong
        }
    }

    // Fungsi Hash: Menghitung indeks dari NIM
    private int hashFunction(int nim) {
        return nim % size; // Modulus size untuk mendapatkan indeks
    }

    // Metode Insert: Menambah data mahasiswa ke dalam Hash Table
    void insert(int nim, String nama, double ipk) {
        int index = hashFunction(nim); // Hitung indeks berdasarkan fungsi hash

        // Mengecek duplikat NIM
        for (BSTNode node : table[index]) {
            if (node.nim == nim) {
                System.out.println("NIM " + nim + " sudah terdaftar!");
                return;
            }
        }

        table[index].add(new BSTNode(nim, nama, ipk)); // Tambahkan entry ke Linked List di indeks yang sesuai
        System.out.println("Tambah Mahasiswa: NIM " + nim +
                           ", Nama: " + nama + ", IPK: " + ipk);
    }

    // Metode Search: Mencari mahasiswa berdasarkan NIM
    void search(int nim) {
        int index = hashFunction(nim); // Hitung indeks

        for (BSTNode node : table[index]) { // Telusuri Linked List di indeks yang sesuai
            if (node.nim == nim) {
                System.out.println("Cari NIM Mahasiswa " + nim +
                                   ": Ditemukan " + node.nama +
                                   ", IPK " + node.ipk);
                return;
            }
        }
        System.out.println("Cari Mahasiswa dengan NIM " + nim +
                           ": Data tidak ditemukan.");
    }

    // Metode Delete: Menghapus mahasiswa berdasarkan NIM
    void delete(int nim) {
        int index = hashFunction(nim);
        boolean removed = table[index].removeIf(node -> node.nim == nim);

        if (removed) {
            System.out.println("Hapus Mahasiswa dengan NIM " + nim +
                               ": Data berhasil dihapus.");
        } else {
            System.out.println("Hapus Mahasiswa dengan NIM " + nim +
                               ": Data tidak ditemukan.");
        }
    }

    // UPDATE untuk Memperbarui data mahasiswa
    void update(int nim, String namaBaru, double ipkBaru) {
        int index = hashFunction(nim);

        for (BSTNode node : table[index]) {
            if (node.nim == nim) {
                node.nama = namaBaru;
                node.ipk  = ipkBaru;
                System.out.println("Update Mahasiswa NIM " + nim +
                                   ": Nama -> " + namaBaru +
                                   ", IPK -> " + ipkBaru);
                return;
            }
        }
        System.out.println("Gagal update. NIM " + nim + " data tidak ditemukan.");
    }

    // Metode Display: menampilkan semua elemen dalam Hash Table
    void display() {
        System.out.println("\n===== Daftar Semua Mahasiswa (Hash Table) =====");
        for (int i = 0; i < size; i++) {
            System.out.print("Index " + i + ": ");
            for (BSTNode node : table[i]) {
                System.out.print("(NIM: " + node.nim +
                                 ", Nama: " + node.nama +
                                 ", IPK: " + node.ipk + ") -> ");
            }
            System.out.println("null");
        }
    }

    // GET TOTAL menghitung jumlah mahasiswa terdaftar
    int getTotalMahasiswa() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            count += table[i].size();
        }
        return count;
    }
}