package TubesSupplier;

import java.util.*;

public class Node {
    private String id;
    private String nama;
    private String tipe; // "Supplier" atau "Toko"
    private String lokasi;
    private List<String> barangList;

    public Node(String id, String nama, String tipe, String lokasi) {
        this.id = id;
        this.nama = nama;
        this.tipe = tipe;
        this.lokasi = lokasi;
        this.barangList = new ArrayList<>();
    }

    // Getter dan Setter
    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getTipe() { return tipe; }
    public String getLokasi() { return lokasi; }
    public List<String> getBarangList() { return barangList; }

    public void tambahBarang(String barang) {
        barangList.add(barang);
    }

    @Override
    public String toString() {
        return nama + " (" + tipe + ") - " + lokasi;
    }
}