package Tubes_PP_Kelompok8;

import java.util.*;

public class BarangManager {
    private JaringanBarang jaringanBarang;

    public BarangManager(JaringanBarang jaringanBarang) {
        this.jaringanBarang = jaringanBarang;
    }

    public void tambahBarang(Node supplier, String barang) {
        jaringanBarang.tambahBarang(supplier, barang);
    }

    public void cariSupplierUntukBarang(String barang) {
        List<Node> suppliers = jaringanBarang.cariSupplierUntukBarang(barang);
        if (suppliers.isEmpty()) {
            System.out.println("Tidak ada supplier yang menjual " + barang);
        } else {
            System.out.println("Supplier yang menjual " + barang + ":");
            for (Node supplier : suppliers) {
                System.out.println("- " + supplier.getNama() + " (" + supplier.getLokasi() + ")");
            }
        }
    }

    public void tampilkanBarangSupplier(Node supplier) {
        jaringanBarang.tampilkanBarangSupplier(supplier);
    }
}