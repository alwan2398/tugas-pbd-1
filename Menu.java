/**
 * ================================================================
 * TUGAS PRAKTIK 1 - PEMROGRAMAN BERBASIS DESKTOP
 * Class Menu : merepresentasikan satu item menu restoran
 * (makanan / minuman) beserta harganya.
 * ================================================================
 */
public class Menu {

    // ---------- Atribut (variabel milik class) ----------
    String nama; // nama menu, contoh: "Nasi Padang"
    int harga; // harga menu dalam Rupiah
    String kategori; // kategori menu: "Makanan" / "Minuman"

    // ---------- Constructor ----------
    // Dipanggil saat object dibuat dengan keyword "new"
    Menu(String nama, int harga, String kategori) {
        this.nama = nama; // "this" merujuk ke atribut milik object
        this.harga = harga;
        this.kategori = kategori;
    }
}