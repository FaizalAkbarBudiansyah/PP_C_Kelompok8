package Tubes_PP_Kelompok8;

import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private NodeManager nodeManager;
    private BarangManager barangManager;
    private RelationshipManager relationshipManager;

    public MenuHandler(NodeManager nodeManager, BarangManager barangManager, RelationshipManager relationshipManager) {
        this.scanner = new Scanner(System.in);
        this.nodeManager = nodeManager;
        this.barangManager = barangManager;
        this.relationshipManager = relationshipManager;
    }

    public void tampilkanMenu() {
        System.out.println("\nMenu Utama:");
        System.out.println("1. Tambah Supplier/Toko");
        System.out.println("2. Tambah Barang ke Supplier");
        System.out.println("3. Tambah Hubungan Supplier-Toko");
        System.out.println("4. Tampilkan Semua Supplier/Toko");
        System.out.println("5. Tampilkan Semua Hubungan");
        System.out.println("6. Cari Supplier untuk Barang Tertentu");
        System.out.println("7. Cari Supplier Terdekat dari Toko");
        System.out.println("8. Tampilkan Barang dari Supplier");
        System.out.println("0. Keluar");
        System.out.print("Pilihan Anda: ");
    }

    public void handleTambahNode() {
        System.out.println("\nTambah Node Baru:");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Tipe (Supplier/Toko): ");
        String tipe = scanner.nextLine();
        System.out.print("Lokasi: ");
        String lokasi = scanner.nextLine();

        nodeManager.tambahNode(id, nama, tipe, lokasi);
        System.out.println("Berhasil menambahkan " + tipe + " baru!");
    }

    public void handleTambahBarang() {
        System.out.println("\nTambah Barang ke Supplier:");
        System.out.print("ID Supplier: ");
        String idSupplier = scanner.nextLine();

        if (!nodeManager.isValidSupplier(idSupplier)) {
            System.out.println("Supplier tidak ditemukan!");
            return;
        }

        System.out.print("Nama Barang: ");
        String barang = scanner.nextLine();

        barangManager.tambahBarang(nodeManager.getNode(idSupplier), barang);
        System.out.println("Berhasil menambahkan barang " + barang + " ke " + nodeManager.getNode(idSupplier).getNama());
    }

    public void handleTambahHubungan() {
        System.out.println("\nTambah Hubungan Supplier-Toko:");
        System.out.print("ID Supplier: ");
        String idSupplier = scanner.nextLine();
        System.out.print("ID Toko: ");
        String idToko = scanner.nextLine();

        if (!nodeManager.isValidSupplier(idSupplier)) {
            System.out.println("Supplier tidak valid!");
            return;
        }
        if (!nodeManager.isValidToko(idToko)) {
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

        relationshipManager.tambahHubungan(
                nodeManager.getNode(idSupplier),
                nodeManager.getNode(idToko),
                jarak, biaya, waktu
        );
        System.out.println("Berhasil menambahkan hubungan antara " +
                nodeManager.getNode(idSupplier).getNama() + " dan " +
                nodeManager.getNode(idToko).getNama());

    }

}