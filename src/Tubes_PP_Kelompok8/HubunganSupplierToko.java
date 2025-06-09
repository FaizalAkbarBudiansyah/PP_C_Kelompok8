// Tambahan di HubunganSupplierToko.java
package Tubes_PP_Kelompok8;

import java.util.*;

public class HubunganSupplierToko {
    private Map<Node, List<Edge>> graph;
    private JaringanBarang jaringanBarang;

    public HubunganSupplierToko(JaringanBarang jaringanBarang) {
        this.graph = new HashMap<>();
        this.jaringanBarang = jaringanBarang;
    }

    public void tambahNode(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node tidak boleh null");
        }
        graph.putIfAbsent(node, new ArrayList<>());
    }

    public void tambahEdge(Edge edge) {
        if (edge == null) {
            throw new IllegalArgumentException("Edge tidak boleh null");
        }
        graph.get(edge.getSource()).add(edge);
    }

    public List<Edge> getEdgesFromNode(Node node) {
        return graph.getOrDefault(node, new ArrayList<>());
    }

    public void hapusNodeDanRelasinya(Node node) {
        graph.remove(node); // Hapus sebagai sumber
        for (List<Edge> edges : graph.values()) {
            edges.removeIf(edge -> edge.getDestination().equals(node));
        }
    }

    // Versi diperbaiki: cari hanya supplier terdekat (jarak terkecil)
    public Node cariSupplierTerdekatSaja(Node toko) {
        Node supplierTerdekat = null;
        double jarakTerdekat = Double.MAX_VALUE;

        for (Map.Entry<Node, List<Edge>> entry : graph.entrySet()) {
            for (Edge edge : entry.getValue()) {
                if (edge.getDestination().equals(toko) &&
                    edge.getSource().getTipe().equals("Supplier")) {

                    if (edge.getJarak() < jarakTerdekat) {
                        jarakTerdekat = edge.getJarak();
                        supplierTerdekat = edge.getSource();
                    }
                }
            }
        }

        return supplierTerdekat;
    }

    public void tampilkanRelasi() {
        for (Map.Entry<Node, List<Edge>> entry : graph.entrySet()) {
            System.out.println(entry.getKey().getNama() + " terhubung dengan:");
            for (Edge edge : entry.getValue()) {
                System.out.println("  " + edge);
            }
        }
    }

	public Map<Node, Double> hitungSemuaJarakSupplier(Node toko) {
		Map<Node, Double> hasil = new HashMap<>();
        for (Map.Entry<Node, List<Edge>> entry : graph.entrySet()) {
            for (Edge edge : entry.getValue()) {
                if (edge.getDestination().equals(toko) && edge.getSource().getTipe().equals("Supplier")) {
                    hasil.put(edge.getSource(), edge.getJarak());
                }
            }
        }
        return hasil;
	}
}