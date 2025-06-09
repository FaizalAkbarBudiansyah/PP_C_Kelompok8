package Tubes_PP_Kelompok8;

import java.util.*;

public class NodeManager {
    private Map<String, Node> nodes = new HashMap<>();

    public void tambahNode(String id, String nama, String tipe, String lokasi) {
        Node node = new Node(id, nama, tipe, lokasi);
        nodes.put(id, node);
    }

    public Node getNode(String id) {
        return nodes.get(id);
    }

    public void tampilkanSemuaNode() {
        System.out.println("\nDaftar Semua Supplier dan Toko:");
        for (Node node : nodes.values()) {
            System.out.println(node);
        }
    }

    public boolean isValidSupplier(String id) {
        Node node = nodes.get(id);
        return node != null && node.getTipe().equals("Supplier");
    }

    public boolean isValidToko(String id) {
        Node node = nodes.get(id);
        return node != null && node.getTipe().equals("Toko");
    }

    public void hapusNode(String id) {
        if (nodes.containsKey(id)) {
            nodes.remove(id);
            System.out.println("Node dengan ID " + id + " berhasil dihapus.");
        } else {
            System.out.println("Node tidak ditemukan.");
        }
    }
}