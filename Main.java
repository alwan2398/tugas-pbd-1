import java.util.Scanner;

/**
 * ================================================================
 * TUGAS PRAKTIK 1 - PEMROGRAMAN BERBASIS DESKTOP
 * Aplikasi Pemesanan Restoran Sederhana
 *
 * Ketentuan yang dipenuhi:
 * 1. Data menu disimpan dalam Array (array of object).
 * 2. Pemesanan maksimal 4 menu, format "Nama Menu = Jumlah".
 * 3. Pajak 10%, biaya pelayanan Rp 20.000.
 * 4. Diskon 10% : total keseluruhan > Rp 100.000.
 * Beli 1 Gratis 1 : total keseluruhan > Rp 50.000 (minuman).
 * 5. Mencetak struk pesanan lengkap.
 * 6. TANPA struktur pengulangan (for / while / do-while).
 * 7. Menggunakan if, if-else, if-else if, switch case, nested if.
 * ================================================================
 */
public class Main {

    static Scanner input = new Scanner(System.in);

    // ---------- Konstanta (keyword: static final) ----------
    static final double PAJAK = 0.10; // pajak 10%
    static final double DISKON = 0.10; // diskon 10%
    static final int BIAYA_PELAYANAN = 20000; // biaya pelayanan Rp 20.000
    static final int MAKS_PESANAN = 4; // maksimal 4 menu per pesanan

    // ---------- Array data menu (array of object) ----------
    static Menu[] menuMakanan = {
            new Menu("Nasi Padang", 25000, "Makanan"),
            new Menu("Nasi Goreng", 20000, "Makanan"),
            new Menu("Ayam Bakar", 28000, "Makanan"),
            new Menu("Soto Ayam", 18000, "Makanan")
    };

    static Menu[] menuMinuman = {
            new Menu("Es Teh", 5000, "Minuman"),
            new Menu("Es Jeruk", 8000, "Minuman"),
            new Menu("Kopi Susu", 15000, "Minuman"),
            new Menu("Jus Alpukat", 18000, "Minuman")
    };

    // ---------- Array data pesanan (array paralel) ----------
    static String[] pesananNama = new String[MAKS_PESANAN];
    static String[] pesananKategori = new String[MAKS_PESANAN];
    static int[] pesananHarga = new int[MAKS_PESANAN];
    static int[] pesananJumlah = new int[MAKS_PESANAN];

    // ---------- Variabel hasil perhitungan ----------
    static int subtotal = 0;
    static double pajak = 0;
    static double totalKeseluruhan = 0;
    static double diskonPromo = 0;
    static double potonganBogo = 0;
    static double totalAkhir = 0;
    static int hargaMinumanTermurah = 0;

    // ================================================================
    // METHOD UTAMA (titik awal program)
    // ================================================================
    public static void main(String[] args) {
        tampilkanSelamatDatang();

        System.out.println("\n1. Tampilkan Daftar Menu");
        System.out.println("2. Keluar");
        System.out.print("Pilih menu (1/2) : ");
        int pilihan = Integer.parseInt(input.nextLine());

        // ===== STRUKTUR KEPUTUSAN: SWITCH CASE =====
        switch (pilihan) {
            case 1: {
                tampilkanDaftarMenu();

                System.out.print("\nApakah Anda ingin memesan? (y/t) : ");
                String jawaban = input.nextLine();

                // ===== STRUKTUR KEPUTUSAN: IF-ELSE =====
                if (jawaban.equalsIgnoreCase("y")) {
                    prosesPemesanan();
                    hitungSubtotal();

                    // ===== IF sederhana: struk hanya dicetak jika ada pesanan =====
                    if (subtotal > 0) {
                        hitungPembayaran();
                        cetakStruk();
                    } else {
                        System.out.println("\nAnda belum memilih menu apa pun. Terima kasih!");
                    }
                } else {
                    System.out.println("\nTerima kasih telah berkunjung. Sampai jumpa!");
                }
                break;
            }
            case 2:
                System.out.println("\nTerima kasih telah berkunjung. Sampai jumpa!");
                break;
            default:
                System.out.println("\nPilihan tidak tersedia. Program selesai.");
        }

        input.close();
    }

    // ================================================================
    // METHOD MENAMPILKAN DATA MENU
    // Karena dilarang menggunakan perulangan, setiap elemen array
    // dicetak satu per satu melalui index-nya.
    // ================================================================
    static void tampilkanSelamatDatang() {
        System.out.println("=================================================");
        System.out.println("    SELAMAT DATANG DI RESTORAN RASA NUSANTARA");
        System.out.println("=================================================");
    }

    static void tampilkanDaftarMenu() {
        System.out.println("\n------------ DAFTAR MENU RESTORAN ------------");
        tampilkanMenuMakanan(); // menu dikelompokkan berdasarkan kategori
        tampilkanMenuMinuman();
        System.out.println("-----------------------------------------------");
    }

    static void tampilkanMenuMakanan() {
        System.out.println("\n[ MAKANAN ]");
        System.out.printf("1. %-12s : %s%n", menuMakanan[0].nama, rupiah(menuMakanan[0].harga));
        System.out.printf("2. %-12s : %s%n", menuMakanan[1].nama, rupiah(menuMakanan[1].harga));
        System.out.printf("3. %-12s : %s%n", menuMakanan[2].nama, rupiah(menuMakanan[2].harga));
        System.out.printf("4. %-12s : %s%n", menuMakanan[3].nama, rupiah(menuMakanan[3].harga));
    }

    static void tampilkanMenuMinuman() {
        System.out.println("\n[ MINUMAN ]");
        System.out.printf("1. %-12s : %s%n", menuMinuman[0].nama, rupiah(menuMinuman[0].harga));
        System.out.printf("2. %-12s : %s%n", menuMinuman[1].nama, rupiah(menuMinuman[1].harga));
        System.out.printf("3. %-12s : %s%n", menuMinuman[2].nama, rupiah(menuMinuman[2].harga));
        System.out.printf("4. %-12s : %s%n", menuMinuman[3].nama, rupiah(menuMinuman[3].harga));
    }

    // ================================================================
    // METHOD MENCARI MENU BERDASARKAN NAMA (mengembalikan object Menu)
    // ================================================================
    static Menu cariMenu(String nama) {
        Menu hasil = null;

        // Cari di daftar makanan (if - else if)
        if (nama.equalsIgnoreCase(menuMakanan[0].nama)) {
            hasil = menuMakanan[0];
        } else if (nama.equalsIgnoreCase(menuMakanan[1].nama)) {
            hasil = menuMakanan[1];
        } else if (nama.equalsIgnoreCase(menuMakanan[2].nama)) {
            hasil = menuMakanan[2];
        } else if (nama.equalsIgnoreCase(menuMakanan[3].nama)) {
            hasil = menuMakanan[3];
        }

        // ===== NESTED IF =====
        // If luar : jika belum ditemukan di makanan,
        // If dalam: cari di daftar minuman (if - else if)
        if (hasil == null) {
            if (nama.equalsIgnoreCase(menuMinuman[0].nama)) {
                hasil = menuMinuman[0];
            } else if (nama.equalsIgnoreCase(menuMinuman[1].nama)) {
                hasil = menuMinuman[1];
            } else if (nama.equalsIgnoreCase(menuMinuman[2].nama)) {
                hasil = menuMinuman[2];
            } else if (nama.equalsIgnoreCase(menuMinuman[3].nama)) {
                hasil = menuMinuman[3];
            }
        }

        return hasil; // null jika tidak ditemukan
    }

    // ================================================================
    // METHOD MENGINPUT SATU BARIS PESANAN
    // Format input: "Nama Menu = Jumlah", contoh: "Nasi Padang = 2"
    // ================================================================
    static void inputSatuPesanan(int nomor) {
        System.out.print("Pesanan ke-" + nomor + " : ");
        String baris = input.nextLine().trim();

        // IF sederhana: lewati jika input kosong
        if (!baris.isEmpty()) {
            // Pecah input menggunakan method String.split()
            String[] bagian = baris.split("=");

            // IF-ELSE: validasi format input harus "nama = jumlah"
            if (bagian.length == 2) {
                String nama = bagian[0].trim();
                int jumlah = Integer.parseInt(bagian[1].trim());
                Menu dipilih = cariMenu(nama);

                // IF-ELSE: cek apakah menu ditemukan
                if (dipilih != null) {
                    // simpan pesanan ke array paralel
                    pesananNama[nomor - 1] = dipilih.nama;
                    pesananKategori[nomor - 1] = dipilih.kategori;
                    pesananHarga[nomor - 1] = dipilih.harga;
                    pesananJumlah[nomor - 1] = jumlah;
                } else {
                    System.out.println("   >> Menu \"" + nama + "\" tidak ditemukan. Pesanan dilewati.");
                }
            } else {
                System.out.println("   >> Format salah! Gunakan: <Nama Menu> = <Jumlah>");
            }
        }
    }

    // ================================================================
    // METHOD MEMPROSES SELURUH PESANAN (maksimal 4 menu)
    // Tanpa perulangan: method inputSatuPesanan dipanggil 4 kali.
    // ================================================================
    static void prosesPemesanan() {
        System.out.println("\n========== FORM PEMESANAN (maks " + MAKS_PESANAN + " menu) ==========");
        System.out.println("Format input : <Nama Menu> = <Jumlah>");
        System.out.println("Contoh       : Nasi Padang = 2");
        System.out.println("(Tekan Enter saja jika tidak ingin menambah)");
        System.out.println("-------------------------------------------------");

        inputSatuPesanan(1);
        inputSatuPesanan(2);
        inputSatuPesanan(3);
        inputSatuPesanan(4);
    }

    // ================================================================
    // METHOD MENGHITUNG SUBTOTAL
    // Penjumlahan ditulis langsung (tanpa perulangan)
    // ================================================================
    static void hitungSubtotal() {
        subtotal = (pesananJumlah[0] * pesananHarga[0])
                + (pesananJumlah[1] * pesananHarga[1])
                + (pesananJumlah[2] * pesananHarga[2])
                + (pesananJumlah[3] * pesananHarga[3]);
    }

    // ================================================================
    // METHOD MENCARI HARGA MINUMAN TERMURAH YANG DIPESAN
    // (dipakai untuk promo beli 1 gratis 1)
    // ================================================================
    static void cariMinumanTermurah() {
        periksaMinuman(0);
        periksaMinuman(1);
        periksaMinuman(2);
        periksaMinuman(3);
    }

    static void periksaMinuman(int index) {
        // ===== NESTED IF =====
        // If luar : slot terisi DAN kategorinya minuman
        // If dalam : harga lebih murah dari yang tersimpan
        if (pesananKategori[index] != null && pesananKategori[index].equalsIgnoreCase("Minuman")) {
            if (hargaMinumanTermurah == 0 || pesananHarga[index] < hargaMinumanTermurah) {
                hargaMinumanTermurah = pesananHarga[index];
            }
        }
    }

    // ================================================================
    // METHOD MENGHITUNG SELURUH BIAYA (pajak, pelayanan, promo)
    // ================================================================
    static void hitungPembayaran() {
        pajak = subtotal * PAJAK;
        totalKeseluruhan = subtotal + pajak + BIAYA_PELAYANAN;

        cariMinumanTermurah();
        hitungPromo();

        totalAkhir = totalKeseluruhan - diskonPromo - potonganBogo;
    }

    // ================================================================
    // METHOD MENENTUKAN PROMO
    // ===== STRUKTUR KEPUTUSAN: IF - ELSE IF =====
    // Kedua promo TIDAK berlaku bersamaan:
    // - > Rp 100.000 -> diskon 10%
    // - > Rp 50.000 -> beli 1 gratis 1 minuman
    // ================================================================
    static void hitungPromo() {
        if (totalKeseluruhan > 100000) {
            // Diskon 10% dari total keseluruhan
            diskonPromo = totalKeseluruhan * DISKON;
        } else if (totalKeseluruhan > 50000) {
            // ===== NESTED IF: promo BOGO hanya jika ada minuman dipesan =====
            if (hargaMinumanTermurah > 0) {
                potonganBogo = hargaMinumanTermurah; // 1 gelas minuman gratis
            }
        }
        // else: total <= Rp 50.000 -> tidak mendapat promo
    }

    // ================================================================
    // METHOD MENCETAK STRUK PESANAN
    // ================================================================
    static void cetakStruk() {
        System.out.println("\n=================================================");
        System.out.println("                 STRUK PEMESANAN");
        System.out.println("             RESTORAN RASA NUSANTARA");
        System.out.println("=================================================");

        // Cetak hanya slot pesanan yang terisi (tanpa perulangan)
        if (pesananNama[0] != null)
            cetakBarisPesanan(0);
        if (pesananNama[1] != null)
            cetakBarisPesanan(1);
        if (pesananNama[2] != null)
            cetakBarisPesanan(2);
        if (pesananNama[3] != null)
            cetakBarisPesanan(3);

        System.out.println("-------------------------------------------------");
        System.out.println("Subtotal              : " + rupiah(subtotal));
        System.out.println("Pajak (10%)           : " + rupiah(pajak));
        System.out.println("Biaya Pelayanan       : " + rupiah(BIAYA_PELAYANAN));
        System.out.println("Total Keseluruhan     : " + rupiah(totalKeseluruhan));

        // IF: tampilkan promo hanya jika ada
        if (diskonPromo > 0) {
            System.out.println("Diskon 10%            : -" + rupiah(diskonPromo));
        }
        if (potonganBogo > 0) {
            System.out.println("Promo Beli 1 Gratis 1 : -" + rupiah(potonganBogo) + " (minuman)");
        }

        System.out.println("-------------------------------------------------");
        System.out.println("TOTAL BAYAR           : " + rupiah(totalAkhir));
        System.out.println("=================================================");
        System.out.println("        Terima kasih telah berkunjung!");
    }

    // Method bantu: mencetak satu baris item pesanan
    static void cetakBarisPesanan(int index) {
        int totalPerItem = pesananJumlah[index] * pesananHarga[index];
        System.out.printf("%-14s %3d x %-10s = %-12s%n",
                pesananNama[index],
                pesananJumlah[index],
                rupiah(pesananHarga[index]),
                rupiah(totalPerItem));
    }

    // ================================================================
    // METHOD BANTU FORMAT MATA UANG (implementasi String dalam method)
    // Contoh: 25000 -> "Rp 25.000"
    // ================================================================
    static String rupiah(int angka) {
        return "Rp " + String.format("%,d", angka).replace(',', '.');
    }

    static String rupiah(double angka) {
        return "Rp " + String.format("%,.0f", angka).replace(',', '.');
    }
}