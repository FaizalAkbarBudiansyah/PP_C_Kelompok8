package Tubes_PP_Kelompok8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JaringanBarang {
    private Map<Node, List<String>> barangBySupplier;

    public JaringanBarang() {
        barangBySupplier = new HashMap<>();
    }

    public void tambahBarang(Node supplier, String barang) {
        if (supplier == null || barang == null || barang.isEmpty()) {
            throw new IllegalArgumentException("Supplier atau barang tidak valid");
        }
        if (!supplier.getTipe().equals("Supplier")) {
            throw new IllegalArgumentException("Hanya supplier yang bisa memiliki barang");
        }
        
        if (!barangBySupplier.containsKey(supplier)) {
            barangBySupplier.put(supplier, new ArrayList<>());
        }
        barangBySupplier.get(supplier).add(barang);
        supplier.tambahBarang(barang);
    }

    public List<String> getBarangBySupplier(Node supplier) {
        return barangBySupplier.getOrDefault(supplier, new ArrayList<>());
    }

    public List<Node> cariSupplierUntukBarang(String barang) {
        List<Node> suppliers = new ArrayList<>();
        for (Map.Entry<Node, List<String>> entry : barangBySupplier.entrySet()) {
            if (entry.getValue().contains(barang)) {
                suppliers.add(entry.getKey());
            }
        }
        return suppliers;
    }

    public void tampilkanBarangSupplier(Node supplier) {
        System.out.println("Barang dari " + supplier.getNama() + ":");
        for (String barang : getBarangBySupplier(supplier)) {
            System.out.println("- " + barang);
        }
    }
    
    public void hapusBarangDariSupplier(Node supplier) {
        barangBySupplier.remove(supplier);
    }
}