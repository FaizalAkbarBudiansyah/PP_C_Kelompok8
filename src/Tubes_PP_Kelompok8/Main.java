package PP_Tubes;

public class Main {
    public static void main(String[] args) {
        // Inisialisasi objek utama
        HubunganSupplierToko hubungan = new HubunganSupplierToko();
        JaringanBarang jaringanBarang = new JaringanBarang();
        
        // Inisialisasi menu handler
        MenuHandler menu = new MenuHandler(hubungan, jaringanBarang);
        
        // Menjalankan menu utama
        menu.tampilkanMenu();
    }
}