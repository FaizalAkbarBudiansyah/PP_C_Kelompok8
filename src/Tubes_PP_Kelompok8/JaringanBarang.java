package Tubes_PP_Kelompok8;

public class JaringanBarang {

    private static final int MAX_SUPPLIER = 100;
    private static final int MAX_BARANG = 50;

    private Node[] suppliers = new Node[MAX_SUPPLIER];
    private String[][] barangSupplier = new String[MAX_SUPPLIER][MAX_BARANG];
    private int jumlahSupplier = 0;

    public void tambahSupplier(Node supplier) {
        if (supplier == null || !supplier.getTipe().equalsIgnoreCase("Supplier")) {
            System.out.println("Node bukan supplier.");
            return;
        }
        suppliers[jumlahSupplier++] = supplier;
    }

    public void tambahBarang(Node supplier, String barang) {
        int index = cariIndexSupplier(supplier);
        if (index == -1) {
            System.out.println("Supplier tidak ditemukan dalam daftar barang.");
            return;
        }

        for (int i = 0; i < MAX_BARANG; i++) {
            if (barangSupplier[index][i] == null) {
                barangSupplier[index][i] = barang;
                supplier.tambahBarang(barang); // Simpan juga di list internal Node
                return;
            }
        }

        System.out.println("Daftar barang supplier penuh.");
    }

    public void tampilkanBarangSupplier(Node supplier) {
        int index = cariIndexSupplier(supplier);
        if (index == -1) {
            System.out.println("Supplier tidak ditemukan.");
            return;
        }

        System.out.println("Barang dari " + supplier.getNama() + ":");
        boolean kosong = true;
        for (int i = 0; i < MAX_BARANG; i++) {
            if (barangSupplier[index][i] != null) {
                System.out.println("- " + barangSupplier[index][i]);
                kosong = false;
            }
        }

        if (kosong) {
            System.out.println("- (tidak ada barang)");
        }
    }

    public void cariSupplierUntukBarang(String barang) {
        boolean ditemukan = false;
        System.out.println("Supplier yang menjual " + barang + ":");
        for (int i = 0; i < jumlahSupplier; i++) {
            for (int j = 0; j < MAX_BARANG; j++) {
                if (barangSupplier[i][j] != null && barangSupplier[i][j].equalsIgnoreCase(barang)) {
                    System.out.println("- " + suppliers[i].getNama() + " (" + suppliers[i].getLokasi() + ")");
                    ditemukan = true;
                    break;
                }
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada supplier yang menjual barang tersebut.");
        }
    }

    public void hapusBarangDariSupplier(Node supplier) {
        int index = cariIndexSupplier(supplier);
        if (index != -1) {
            for (int j = 0; j < MAX_BARANG; j++) {
                barangSupplier[index][j] = null;
            }
        }
    }

    private int cariIndexSupplier(Node supplier) {
        for (int i = 0; i < jumlahSupplier; i++) {
            if (suppliers[i] != null && suppliers[i].getId().equals(supplier.getId())) {
                return i;
            }
        }
        return -1;
    }
}
