package PP_Tubes;

public class HubunganSupplierToko {

    private static final int MAX_NODE = 100;

    private Node[] nodes = new Node[MAX_NODE];
    private int jumlahNode = 0;

    private Edge[][] adjacencyMatrix = new Edge[MAX_NODE][MAX_NODE];

    // Menambahkan node ke dalam array
    public void tambahNode(Node node) {
        if (node == null) return;

        if (jumlahNode >= MAX_NODE) {
            System.out.println("Maksimal node tercapai.");
            return;
        }

        if (cariIndexNode(node.getId()) != -1) {
            System.out.println("Node dengan ID ini sudah ada.");
            return;
        }

        nodes[jumlahNode++] = node;
    }

    // Menambahkan edge ke adjacency matrix
    public void tambahEdge(String idSource, String idDest, double jarak, double biaya, double waktu) {
        int indexSource = cariIndexNode(idSource);
        int indexDest = cariIndexNode(idDest);

        if (indexSource == -1 || indexDest == -1) {
            System.out.println("Salah satu node tidak ditemukan.");
            return;
        }

        try {
            Edge edge = new Edge(nodes[indexSource], nodes[indexDest], jarak, biaya, waktu);
            adjacencyMatrix[indexSource][indexDest] = edge;
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal menambahkan edge: " + e.getMessage());
        }
    }

    // Menampilkan seluruh relasi dari adjacency matrix
    public void tampilkanRelasi() {
        System.out.println("Daftar Relasi (Edge) Antar Node:");
        for (int i = 0; i < jumlahNode; i++) {
            for (int j = 0; j < jumlahNode; j++) {
                if (adjacencyMatrix[i][j] != null) {
                    System.out.println(adjacencyMatrix[i][j]);
                }
            }
        }
    }

    // Menghapus node dan relasinya dari matriks
    public void hapusNode(String id) {
        int index = cariIndexNode(id);
        if (index == -1) {
            System.out.println("Node tidak ditemukan.");
            return;
        }

        // Hapus dari array nodes
        for (int i = index; i < jumlahNode - 1; i++) {
            nodes[i] = nodes[i + 1];
        }
        nodes[--jumlahNode] = null;

        // Hapus baris dan kolom dari adjacency matrix
        for (int i = 0; i < MAX_NODE; i++) {
            adjacencyMatrix[index][i] = null;
            adjacencyMatrix[i][index] = null;
        }

        System.out.println("Node dan semua relasi terkait telah dihapus.");
    }
    
    public void tampilkanSemuaSupplierTerdekat(String idToko) {
        int indexToko = cariIndexNode(idToko);
        
        if (indexToko == -1 || !nodes[indexToko].getTipe().equalsIgnoreCase("Toko")) {
            System.out.println("Toko tidak ditemukan atau node bukan toko.");
            return;
        }

        // Array untuk menyimpan jarak terdekat dari setiap supplier ke toko
        Object[] supplierTerdekat = new Object[jumlahNode];
        int count = 0;

        // Cari semua supplier dan hitung jaraknya ke toko
        for (int i = 0; i < jumlahNode; i++) {
            if (nodes[i].getTipe().equalsIgnoreCase("Supplier")) {
                double jarak = hitungJarakTerdekat(i, indexToko);
                if (jarak != Double.MAX_VALUE) {
                    supplierTerdekat[count++] = new Object[]{nodes[i], jarak};
                }
            }
        }

        // Urutkan berdasarkan jarak terdekat (bubble sort)
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                double jarak1 = (double) ((Object[])supplierTerdekat[j])[1];
                double jarak2 = (double) ((Object[])supplierTerdekat[j+1])[1];
                if (jarak1 > jarak2) {
                    Object[] temp = (Object[]) supplierTerdekat[j];
                    supplierTerdekat[j] = supplierTerdekat[j+1];
                    supplierTerdekat[j+1] = temp;
                }
            }
        }

        // Tampilkan hasil
        System.out.println("\nDaftar Supplier Terdekat ke " + nodes[indexToko].getNama() + ":");
        if (count == 0) {
            System.out.println("- Tidak ada supplier yang terhubung dengan toko ini");
        } else {
            for (int i = 0; i < count; i++) {
                Node supplier = (Node) ((Object[])supplierTerdekat[i])[0];
                double jarak = (double) ((Object[])supplierTerdekat[i])[1];
                System.out.printf("%d. %s - Jarak: %.2f km\n", 
                               i+1, supplier.getNama(), jarak);
            }
        }
    }

    private double hitungJarakTerdekat(int indexSupplier, int indexToko) {
        double[] jarak = new double[MAX_NODE];
        boolean[] dikunjungi = new boolean[MAX_NODE];
        
        for (int i = 0; i < jumlahNode; i++) {
            jarak[i] = Double.MAX_VALUE;
            dikunjungi[i] = false;
        }
        
        jarak[indexSupplier] = 0;
        
        // Algoritma Dijkstra sederhana
        for (int count = 0; count < jumlahNode - 1; count++) {
            int u = minDistance(jarak, dikunjungi);
            dikunjungi[u] = true;
            
            for (int v = 0; v < jumlahNode; v++) {
                if (!dikunjungi[v] && adjacencyMatrix[u][v] != null && 
                    jarak[u] != Double.MAX_VALUE &&
                    jarak[u] + adjacencyMatrix[u][v].getJarak() < jarak[v]) {
                    
                    jarak[v] = jarak[u] + adjacencyMatrix[u][v].getJarak();
                }
            }
        }
        
        return jarak[indexToko];
    }

    private int minDistance(double[] jarak, boolean[] dikunjungi) {
        double min = Double.MAX_VALUE;
        int minIndex = -1;
        
        for (int v = 0; v < jumlahNode; v++) {
            if (!dikunjungi[v] && jarak[v] <= min) {
                min = jarak[v];
                minIndex = v;
            }
        }
        
        return minIndex;
    }

    // Mencari index node berdasarkan ID
    private int cariIndexNode(String id) {
        for (int i = 0; i < jumlahNode; i++) {
            if (nodes[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public Edge[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getJumlahNode() {
        return jumlahNode;
    }
}
