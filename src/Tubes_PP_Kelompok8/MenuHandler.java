package PP_Tubes;

import java.util.Scanner;

public class MenuHandler {
    private HubunganSupplierToko hubungan;
    private JaringanBarang jaringanBarang;
    private Scanner scanner;

    public MenuHandler(HubunganSupplierToko hubungan, JaringanBarang jaringanBarang) {
        this.hubungan = hubungan;
        this.jaringanBarang = jaringanBarang;
        this.scanner = new Scanner(System.in);
    }

    public void tampilkanMenu() {
        int pilihan;
        do {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Tambah (Supplier/Toko)");
            System.out.println("2. Tambah Relasi Supplier ke Toko");
            System.out.println("3. Tampilkan Semua Daftar (Supplier/Toko)");
            System.out.println("4. Tampilkan Relasi Supplier ke Toko");
            System.out.println("5. Hapus (Supplier/Toko)");
            System.out.println("6. Tambah Barang ke Supplier");
            System.out.println("7. Tampilkan Barang dari Supplier");
            System.out.println("8. Cari Supplier untuk Barang");
            System.out.println("9. Cari Rute Terdekat ke Toko");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = Integer.parseInt(scanner.nextLine());

            switch (pilihan) {
                case 1:
                    tambahNode();
                    break;
                case 2:
                    tambahRelasi();
                    break;
                case 3:
                    tampilkanNode();
                    break;
                case 4:
                    hubungan.tampilkanRelasi();
                    break;
                case 5:
                    hapusNode();
                    break;
                case 6:
                    tambahBarangKeSupplier();
                    break;
                case 7:
                    tampilkanBarangSupplier();
                    break;
                case 8:
                    cariSupplierUntukBarang();
                    break;
                case 9:
                	tampilkanSupplierTerdekat();
                    break;
                case 0:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private void tambahNode() {
        System.out.print("Masukkan ID : ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama : ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Tipe (Supplier/Toko): ");
        String tipe = scanner.nextLine();
        System.out.print("Masukkan Lokasi: ");
        String lokasi = scanner.nextLine();

        Node node = new Node(id, nama, tipe, lokasi);
        hubungan.tambahNode(node);
        
        if (tipe.equalsIgnoreCase("Supplier")) {
            jaringanBarang.tambahSupplier(node);
        }
    }

    private void tambahRelasi() {
        System.out.print("Masukkan ID Supplier : ");
        String idSource = scanner.nextLine();
        System.out.print("Masukkan ID Toko : ");
        String idDest = scanner.nextLine();
        System.out.print("Masukkan Jarak (km): ");
        double jarak = Double.parseDouble(scanner.nextLine());
        System.out.print("Masukkan Biaya Kirim: ");
        double biaya = Double.parseDouble(scanner.nextLine());
        System.out.print("Masukkan Waktu Kirim (jam): ");
        double waktu = Double.parseDouble(scanner.nextLine());

        hubungan.tambahEdge(idSource, idDest, jarak, biaya, waktu);
    }

    private void tampilkanNode() {
        Node[] semua = hubungan.getNodes();
        int total = hubungan.getJumlahNode();
        System.out.println("=== Daftar Supplier dan Toko ===");
        for (int i = 0; i < total; i++) {
            if (semua[i] != null) {
                System.out.println("- " + semua[i]);
            }
        }
    }

    private void hapusNode() {
        System.out.print("Masukkan ID yang ingin dihapus: ");
        String id = scanner.nextLine();
        
        Node node = cariNodeById(id);
        if (node != null && node.getTipe().equalsIgnoreCase("Supplier")) {
            jaringanBarang.hapusBarangDariSupplier(node);
        }
        
        hubungan.hapusNode(id);
    }

    private void tambahBarangKeSupplier() {
        System.out.print("Masukkan ID Supplier: ");
        String idSupplier = scanner.nextLine();
        
        Node supplier = cariNodeById(idSupplier);
        if (supplier == null || !supplier.getTipe().equalsIgnoreCase("Supplier")) {
            System.out.println("Supplier tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan Nama Barang: ");
        String barang = scanner.nextLine();
        
        jaringanBarang.tambahBarang(supplier, barang);
        System.out.println("Barang berhasil ditambahkan ke supplier.");
    }

    private void tampilkanBarangSupplier() {
        System.out.print("Masukkan ID Supplier: ");
        String idSupplier = scanner.nextLine();
        
        Node supplier = cariNodeById(idSupplier);
        if (supplier == null || !supplier.getTipe().equalsIgnoreCase("Supplier")) {
            System.out.println("Supplier tidak ditemukan.");
            return;
        }
        
        jaringanBarang.tampilkanBarangSupplier(supplier);
    }

    private void cariSupplierUntukBarang() {
        System.out.print("Masukkan Nama Barang: ");
        String barang = scanner.nextLine();
        
        jaringanBarang.cariSupplierUntukBarang(barang);
    }
    
    private void tampilkanSupplierTerdekat() {
        System.out.print("Masukkan ID Toko: ");
        String idToko = scanner.nextLine();
        
        hubungan.tampilkanSemuaSupplierTerdekat(idToko);
    }

    private Node cariNodeById(String id) {
        Node[] nodes = hubungan.getNodes();
        for (int i = 0; i < hubungan.getJumlahNode(); i++) {
            if (nodes[i] != null && nodes[i].getId().equals(id)) {
                return nodes[i];
            }
        }
        return null;
    }
}