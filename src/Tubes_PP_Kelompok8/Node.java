package PP_Tubes;

public class Node {
    private String id;
    private String nama;
    private String tipe; // "Supplier" atau "Toko"
    private String lokasi;
    private String[] barangList;
    private int jumlahBarang;

    public Node(String id, String nama, String tipe, String lokasi) {
        this.id = id;
        this.nama = nama;
        this.tipe = tipe;
        this.lokasi = lokasi;
        this.barangList = new String[50]; // Maksimal 50 barang per supplier
        this.jumlahBarang = 0;
    }

    // Getter dan Setter
    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getTipe() { return tipe; }
    public String getLokasi() { return lokasi; }

    public void setId(String id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setTipe(String tipe) { this.tipe = tipe; }
    public void setLokasi(String lokasi) { this.lokasi = lokasi; }

    public void tambahBarang(String barang) {
        if (barang == null || barang.trim().isEmpty()) {
            System.out.println("Nama barang tidak valid.");
            return;
        }
        if (jumlahBarang < barangList.length) {
            barangList[jumlahBarang++] = barang;
        } else {
            System.out.println("Daftar barang penuh.");
        }
    }

    public String[] getBarangList() {
        return barangList;
    }

    public int getJumlahBarang() {
        return jumlahBarang;
    }

    @Override
    public String toString() {
        return nama + " (" + tipe + ") - " + lokasi;
    }
}
