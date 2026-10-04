### 🍽️ Aplikasi Pemesanan Restoran Sederhana — Tugas Praktik 1

Mata Kuliah : Pemrograman Berbasis DesktopBahasa : Java (Console Application)

Identitas Keterangan
Nama (isi nama Anda)
NIM (isi NIM Anda)
Program Studi (isi prodi Anda)
Link Video (isi link YouTube / Google Drive)

### 📌 Deskripsi Program

Program ini mensimulasikan sistem pemesanan pada sebuah restoran. Pelanggan dapatmelihat daftar menu (dikelompokkan per kategori makanan & minuman), memesanmaksimal 4 menu dengan format Nama Menu = Jumlah, lalu program menghitung totalbiaya (termasuk pajak, biaya pelayanan, dan promo) serta mencetak struk pesanan.

Sesuai ketentuan tugas, program tidak menggunakan struktur pengulangan(for / while / do-while) sama sekali — semua proses dilakukan secarasekuensial dan pemanggilan method berulang.

### ✨ Fitur

Data Menu — 4 makanan + 4 minuman disimpan dalam array of object (class Menu).
Pemesanan — maksimal 4 menu, format input: Nasi Padang = 2.
Perhitungan Biaya — pajak 10%, biaya pelayanan Rp 20.000, promo diskon & BOGO.
Struk Pesanan — rincian item, subtotal, pajak, biaya layanan, promo, total akhir.

### 🗂️ Struktur File

```
TugasPraktik1/├── Menu.java    → class Menu (atribut: nama, harga, kategori + constructor)└── Main.java    → class utama (menu, pemesanan, perhitungan, struk)
```

### ▶️ Cara Menjalankan

Melalui terminal / command prompt:

javac Menu.java Main.javajava Main
Melalui IDE (NetBeans / IntelliJ / VS Code): buka folder project, lalujalankan (Run) class Main.

### 🔄 Alur Program

```
Mulai  → Tampilkan sambutan & menu pilihan (switch case: 1 = Lihat Menu, 2 = Keluar)  → Tampilkan daftar menu (dikelompokkan per kategori)  → Konfirmasi pemesanan (if-else: y / t)  → Input pesanan 1 s.d. 4 (format: Nama Menu = Jumlah)  → Hitung subtotal  → Hitung pajak 10% + biaya pelayanan Rp 20.000 → Total Keseluruhan  → Tentukan promo (if-else if):       Total > 100.000 → Diskon 10%       Total > 50.000  → Beli 1 Gratis 1 minuman (minuman termurah)  → Cetak struk pesananSelesai
```

### 💰 Aturan Perhitungan

Komponen Rumus Ketentuan
Subtotal Σ (harga × jumlah) —
Pajak 10% × Subtotal selalu dikenakan
Biaya Pelayanan Rp 20.000 selalu dikenakan
Total Keseluruhan Subtotal + Pajak + Pelayanan —
Diskon 10% 10% × Total Keseluruhan jika Total Keseluruhan > Rp 100.000
Beli 1 Gratis 1 potongan = harga minuman termurah yang dipesan jika Total Keseluruhan > Rp 50.000 dan ada minuman
Total Akhir Total Keseluruhan − Diskon − BOGO —

### ⚠️ Keputusan Desain (Asumsi)

Kedua promo tidak berlaku bersamaan (diimplementasikan dengan if-else if),sesuai redaksi soal "restoran ini juga menerapkan diskon atau penawaran khusus".Pelanggan dengan total > Rp 100.000 otomatis mendapat promo yang lebih besar (diskon 10%).
Diskon 10% dihitung dari Total Keseluruhan (setelah pajak + pelayanan),karena soal menyebut "total biaya keseluruhan pesanan".
BOGO memotong harga 1 gelas minuman termurah yang dipesan.

### 🧠 Pemetaan Struktur Keputusan dalam Kode

Struktur Lokasi (Method) Fungsi
if sederhana inputSatuPesanan, cetakStruk lewati input kosong; tampilkan promo hanya jika ada
if-else main konfirmasi "ingin memesan? (y/t)"
if-else inputSatuPesanan validasi format input & menu ditemukan/tidak
if-else if cariMenu pencarian nama menu di daftar makanan & minuman
if-else if hitungPromo penentuan diskon 10% / BOGO / tanpa promo
switch case main menu utama program (1 = lihat menu, 2 = keluar)
nested if cariMenu jika belum ketemu di makanan → cari di minuman (if dalam if)
nested if periksaMinuman cek kategori minuman → lalu bandingkan harga termurah
nested if hitungPromo cabang BOGO hanya aktif jika ada minuman dipesan
Catatan: tidak ada satupun for, while, atau do-while dalam program ini.Penjumlahan subtotal, pencarian menu, dan pencetakan struk dilakukan per-indexsecara langsung, serta beberapa method dipanggil berulang kali secara manual.

### 🧪 Skenario Pengujian

```
Skenario	Input Pesanan	Subtotal	Pajak	Total Keseluruhan	Promo	Total Bayar
A (≤ 50rb)	Soto Ayam=1, Es Teh=1	Rp 23.000	Rp 2.300	Rp 45.300	—	Rp 45.300
B (50–100rb + minuman)	Nasi Goreng=2, Es Teh=3	Rp 55.000	Rp 5.500	Rp 80.500	BOGO −Rp 5.000	Rp 75.500
C (> 100rb)	Nasi Padang=2, Ayam Bakar=1, Kopi Susu=2	Rp 108.000	Rp 10.800	Rp 138.800	Diskon 10% −Rp 13.880	Rp 124.920
🧾 Contoh Output (Skenario C)
Nasi Padang       2 x Rp 25.000   = Rp 50.000Ayam Bakar        1 x Rp 28.000   = Rp 28.000Kopi Susu         2 x Rp 15.000   = Rp 30.000-------------------------------------------------Subtotal              : Rp 108.000Pajak (10%)           : Rp 10.800Biaya Pelayanan       : Rp 20.000Total Keseluruhan     : Rp 138.800Diskon 10%            : -Rp 13.880-------------------------------------------------TOTAL BAYAR           : Rp 124.920
```

### 📋 Keterbatasan Program

Input jumlah harus berupa angka; input non-angka akan menghentikan program.
Menu yang sama dipesan dua kali akan tercetak sebagai dua baris terpisah(tidak digabung otomatis).
Karena tanpa perulangan, daftar menu ditampilkan per-index dan pemesanandibatasi tepat 4 baris input.
