package Tubes_PP_Kelompok8;

import java.util.Scanner;

public class RelationshipManager {
    private HubunganSupplierToko hubungan;

    public RelationshipManager(HubunganSupplierToko hubungan) {
        this.hubungan = hubungan;
    }

    public void tambahHubungan(Node supplier, Node toko, double jarak, double biaya, double waktu) {
        Edge edge = new Edge(supplier, toko, jarak, biaya, waktu);
        hubungan.tambahEdge(edge);
    }

    public void tampilkanRelasi() {
        hubungan.tampilkanRelasi();
    }

    public void cariSupplierTerdekat(Node toko) {
        Node supplier = hubungan.cariSupplierTerdekatSaja(toko);
        if (supplier != null) {
            System.out.println("Supplier terdekat dari " + toko.getNama() + " adalah: " + supplier.getNama());
        } else {
            System.out.println("Tidak ditemukan supplier yang terhubung dengan toko ini.");
        }
    }
}
