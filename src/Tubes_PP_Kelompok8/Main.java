package Tubes_PP_Kelompok8;

import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static JaringanBarang jaringanBarang = new JaringanBarang();
    private static HubunganSupplierToko hubungan = new HubunganSupplierToko(jaringanBarang);
    private static Map<String, Node> nodes = new java.util.HashMap<>();

    public static void main(String[] args) {
        System.out.println("=== SISTEM MANAJEMEN SUPPLIER DAN TOKO ===");

        boolean running = true;
        while (running) {
            tampilkanMenu();
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    tambahNode();
                    break;
                case 2:
                    tambahBarang();
                    break;
                case 3:
                    tambahEdge();
                    break;
                case 4:
                    tampilkanSemuaNode();
                    break;
                case 5:
                    tampilkanRelasi();
                    break;
                case 6:
                    cariSupplierUntukBarang();
                    break;
                case 7:
                    cariSupplierTerdekat();
                    break;
                case 8:
                    tampilkanBarangSupplier();
                    break;
                case 9:
                    hapusNode();
                    break;
                case 0:
                    running = false;
                    System.out.println("Program selesai. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void tampilkanMenu() {
        System.out.println("\nMenu Utama:");
        System.out.println("1. Tambah Supplier/Toko");
        System.out.println("2. Tambah Barang ke Supplier");
        System.out.println("3. Tambah Hubungan Supplier-Toko");
        System.out.println("4. Tampilkan Semua Supplier/Toko");
        System.out.println("5. Tampilkan Semua Hubungan");
        System.out.println("6. Cari Supplier untuk Barang Tertentu");
        System.out.println("7. Cari Supplier Terdekat dari Toko");
        System.out.println("8. Tampilkan Barang dari Supplier");
        System.out.println("9. Hapus Supplier/Toko");
        System.out.println("0. Keluar");
        System.out.print("Pilihan Anda: ");
    }

    private static void tambahNode() {
        System.out.println("\nTambah Node Baru:");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Tipe (Supplier/Toko): ");
        String tipe = scanner.nextLine();
        System.out.print("Lokasi: ");
        String lokasi = scanner.nextLine();

        Node node = new Node(id, nama, tipe, lokasi);
        hubungan.tambahNode(node);
        nodes.put(id, node);
        System.out.println("Berhasil menambahkan " + tipe + " baru!");
    }

    private static void tambahBarang() {
        System.out.println("\nTambah Barang ke Supplier:");
        System.out.print("ID Supplier: ");
        String idSupplier = scanner.nextLine();
        
        Node supplier = nodes.get(idSupplier);
        if (supplier == null || !supplier.getTipe().equals("Supplier")) {
            System.out.println("Supplier tidak ditemukan!");
            return;
        }

        System.out.print("Nama Barang: ");
        String barang = scanner.nextLine();
        
        jaringanBarang.tambahBarang(supplier, barang);
        System.out.println("Berhasil menambahkan barang " + barang + " ke " + supplier.getNama());
    }

    private static void tambahEdge() {
        System.out.println("\nTambah Hubungan Supplier-Toko:");
        System.out.print("ID Supplier: ");
        String idSupplier = scanner.nextLine();
        System.out.print("ID Toko: ");
        String idToko = scanner.nextLine();

        Node supplier = nodes.get(idSupplier);
        Node toko = nodes.get(idToko);

        if (supplier == null || !supplier.getTipe().equals("Supplier")) {
            System.out.println("Supplier tidak valid!");
            return;
        }
        if (toko == null || !toko.getTipe().equals("Toko")) {
            System.out.println("Toko tidak valid!");
            return;
        }

        System.out.print("Jarak (km): ");
        double jarak = scanner.nextDouble();
        System.out.print("Biaya Kirim: ");
        double biaya = scanner.nextDouble();
        System.out.print("Waktu Kirim (jam): ");
        double waktu = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Edge edge = new Edge(supplier, toko, jarak, biaya, waktu);
        hubungan.tambahEdge(edge);
        System.out.println("Berhasil menambahkan hubungan antara " + supplier.getNama() + " dan " + toko.getNama());
    }

    private static void tampilkanSemuaNode() {
        System.out.println("\nDaftar Semua Supplier dan Toko:");
        for (Node node : nodes.values()) {
            System.out.println(node);
        }
    }

    private static void tampilkanRelasi() {
        System.out.println("\nDaftar Hubungan Supplier-Toko:");
        hubungan.tampilkanRelasi();
    }

    private static void cariSupplierUntukBarang() {
        System.out.print("\nMasukkan nama barang yang dicari: ");
        String barang = scanner.nextLine();
        
        var suppliers = jaringanBarang.cariSupplierUntukBarang(barang);
        if (suppliers.isEmpty()) {
            System.out.println("Tidak ada supplier yang menjual " + barang);
        } else {
            System.out.println("Supplier yang menjual " + barang + ":");
            for (Node supplier : suppliers) {
                System.out.println("- " + supplier.getNama() + " (" + supplier.getLokasi() + ")");
            }
        }
    }

    private static void cariSupplierTerdekat() {
        System.out.print("\nMasukkan ID Toko: ");
        String idToko = scanner.nextLine();

        Node toko = nodes.get(idToko);
        if (toko == null || !toko.getTipe().equals("Toko")) {
            System.out.println("Toko tidak valid!");
            return;
        }

        Map<Node, Double> semuaJarak = hubungan.hitungSemuaJarakSupplier(toko);

        if (semuaJarak.isEmpty()) {
            System.out.println("Tidak ada supplier yang terhubung dengan toko ini.");
        } else {
            System.out.println("\nDaftar Jarak dari Supplier ke " + toko.getNama() + ":");
            for (Map.Entry<Node, Double> entry : semuaJarak.entrySet()) {
                System.out.printf("- %s: %.2f km\n", entry.getKey().getNama(), entry.getValue());
            }
        }
    }


    private static void tampilkanBarangSupplier() {
        System.out.print("\nMasukkan ID Supplier: ");
        String idSupplier = scanner.nextLine();
        
        Node supplier = nodes.get(idSupplier);
        if (supplier == null || !supplier.getTipe().equals("Supplier")) {
            System.out.println("Supplier tidak valid!");
            return;
        }

        jaringanBarang.tampilkanBarangSupplier(supplier);
    }
    
    private static void hapusNode() {
        System.out.print("\nMasukkan ID Supplier/Toko yang ingin dihapus: ");
        String id = scanner.nextLine();
        Node node = nodes.get(id);

        if (node == null) {
            System.out.println("ID tidak ditemukan!");
            return;
        }

        hubungan.hapusNodeDanRelasinya(node);
        jaringanBarang.hapusBarangDariSupplier(node);
        nodes.remove(id);

        System.out.println("Berhasil menghapus node dan semua relasinya.");
    }
}