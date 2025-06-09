<<<<<<< HEAD
package PP_Tubes;
=======
package Tubes_PP_Kelompok8;
>>>>>>> 15eb2b3c86450442919a45ed556afcc2154f7bcd

public class Edge {
    private Node source;
    private Node destination;
<<<<<<< HEAD
    private double jarak;        // dalam kilometer
    private double biayaKirim;   // dalam rupiah
    private double waktuKirim;   // dalam jam

    public Edge(Node source, Node destination, double jarak, double biayaKirim, double waktuKirim) {
        if (source == null || destination == null) {
            throw new IllegalArgumentException("Node sumber dan tujuan tidak boleh null.");
        }
        if (!source.getTipe().equalsIgnoreCase("Supplier") || !destination.getTipe().equalsIgnoreCase("Toko")) {
            throw new IllegalArgumentException("Edge hanya boleh dibuat dari Supplier ke Toko.");
        }
        if (jarak <= 0 || biayaKirim <= 0 || waktuKirim <= 0) {
            throw new IllegalArgumentException("Jarak, biaya, dan waktu harus lebih besar dari 0.");
        }

=======
    private double jarak; // dalam km
    private double biayaKirim;
    private double waktuKirim; // dalam jam

    public Edge(Node source, Node destination, double jarak, double biayaKirim, double waktuKirim) {
        if (source == null || destination == null) {
            throw new IllegalArgumentException("Source dan destination tidak boleh null");
        }
        if (!source.getTipe().equals("Supplier") || !destination.getTipe().equals("Toko")) {
            throw new IllegalArgumentException("Edge hanya boleh dari Supplier ke Toko");
        }
        if (jarak <= 0 || biayaKirim <= 0 || waktuKirim <= 0) {
            throw new IllegalArgumentException("Jarak, biaya, dan waktu harus lebih besar dari 0");
        }
        
>>>>>>> 15eb2b3c86450442919a45ed556afcc2154f7bcd
        this.source = source;
        this.destination = destination;
        this.jarak = jarak;
        this.biayaKirim = biayaKirim;
        this.waktuKirim = waktuKirim;
    }

    // Getter
    public Node getSource() { return source; }
    public Node getDestination() { return destination; }
    public double getJarak() { return jarak; }
    public double getBiayaKirim() { return biayaKirim; }
    public double getWaktuKirim() { return waktuKirim; }

    @Override
    public String toString() {
<<<<<<< HEAD
        return source.getNama() + " -> " + destination.getNama() +
               " | Jarak: " + jarak + " km, Biaya: Rp" + biayaKirim +
               ", Waktu: " + waktuKirim + " jam";
    }
}
=======
        return source.getNama() + " -> " + destination.getNama() + 
               " | Jarak: " + jarak + " km, Biaya: Rp" + biayaKirim + 
               ", Waktu: " + waktuKirim + " jam";
    }
}
>>>>>>> 15eb2b3c86450442919a45ed556afcc2154f7bcd
