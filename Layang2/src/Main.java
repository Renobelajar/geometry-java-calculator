


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import threading.ThreadExecutor;
import java.util.*;
import projek_pbo.*;

public class Main {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            List<BendaGeometri> shapes = new ArrayList<>();
            Random random = new Random();
            int pilihan;
            boolean lanjutMenu = true;
            do {
                System.out.println("\n=== Program Perhitungan Benda Geometri ===");
            
                System.out.println("1. Layang-Layang ");
                
                System.out.println("2. Limas Layang Layang ");
                System.out.println("3. Prisma Layang Layang ");
                
                System.out.println("4. Polymorphism");
                System.out.println("5. Exit");
                System.out.println("6. Proses Geometri Acak dengan Thread pool");
                System.out.println("7. Proses Geometri dengan Thread Runnable");
                System.out.print("Masukkan pilihan anda : ");

                try {
                    pilihan = input.nextInt();
                    input.nextLine();
                    switch (pilihan) {
                        case 1:
                            LayangLayang layanglayang = new LayangLayang(15, 10, 8, 12);
                            System.out.printf("Luas LayangLayang: %.2f\n", layanglayang.hitungLuas());
                            System.out.printf("Keliling LayangLayang: %.2f\n", layanglayang.hitungKeliling());
                            layanglayang.prosesInputDanValidasi();
                            break;
                        
                        case 2:
                            LimasLayangLayang limasLayangLayang = new LimasLayangLayang(5, 10, 12, 8, 14);
                            System.out.printf("Luas Permukaan Limas LayangLayang: %.2f\n", limasLayangLayang.hitungLuasPermukaan());
                            System.out.printf("Volume Limas LayangLayang: %.2f\n", limasLayangLayang.hitungVolume());
                            limasLayangLayang.prosesInputDanValidasi(); 
                            break;
                        case 3:
                            PrismaLayangLayang prismaLayangLayang = new PrismaLayangLayang(8, 10, 8, 12, 14);
                            System.out.printf("Luas Permukaan Prisma LayangLayang: %.2f\n", prismaLayangLayang.hitungLuasPermukaan());
                            System.out.printf("Volume Prisma LayangLayang: %.2f\n", prismaLayangLayang.hitungVolume());
                            prismaLayangLayang.prosesInputDanValidasi();
                            break;

                        default:
                            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    }
                    if (pilihan != 3) {
                        lanjutMenu = tanyaKembaliKeMenu(input);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka sesuai pilihan.");
                    input.nextLine();
                    pilihan = 0;
                }
            } while (pilihan != 3);
        }
        System.out.println("Program telah berakhir.");
    }
    
     private static boolean tanyaKembaliKeMenu(Scanner input) {
        while (true) {
            System.out.print("Apakah Anda ingin kembali ke menu utama? (y/n): ");
            String jawaban = input.nextLine().trim().toLowerCase();
            if (jawaban.equals("y") || jawaban.equals("ya")) {
                return true;
            } else if (jawaban.equals("n") || jawaban.equals("tidak")) {
                System.out.println("Program akan keluar...");
                System.exit(0);
            } else {
                System.out.println("Input tidak dikenali. Silakan masukkan 'y' atau 'n'.");
            }
        }
    }
    
    private static double r(){
        return 1 + Math.random() * 10;
    }
    
    private static double r(double min, double max){
        return min + Math.random() * (max - min);
    }
    
    private static BendaGeometri generateRandomBendaGeometri(int choice) {
        return switch (choice){
//            case 1 -> new Segitiga(r(), r());
//            case 2 -> new JajarGenjang(r(), r(), r(), r());
//            case 3 -> new BelahKetupat(r(), r(), r());
//            case 4 -> new PersegiPanjang(r(), r());
            case 1 -> new LayangLayang(r(), r(), r(), r());
//            case 6 -> new Persegi(r());
//            case 7 -> new Trapesium(r(), r(), r(), r());
//            case 8 -> new Lingkaran(r());
//            case 9 -> new JuringLingkaran(r(), r(1, 360));
//            case 10 -> new TemberengLingkaran(r(), r(1, 360));
//            case 11 -> new Kubus(r());
//            case 12 -> new LimasPersegi(r(), r());
//            case 13 -> new PrismaPersegi(r(), r());
//            case 14 -> new LimasBelahKetupat(r(), r(), r(), r());
//            case 15 -> new PrismaBelahKetupat(r(), r(), r(), r());
            case 2 -> new LimasLayangLayang(r(), r(), r(), r(), r());
            case 3 -> new PrismaLayangLayang(r(), r(), r(), r(), r());
//            case 18 -> new LimasSegitiga(r(), r(), r());
//            case 19 -> new PrismaSegitiga(r(), r(), r());
//            case 20 -> new LimasJajarGenjang(r(), r(), r(), r(), r());
//            case 21 -> new PrismaJajarGenjang(r(), r(), r(), r(), r());
//            case 22 -> new LimasTrapesium(r(), r(), r(), r(), r());
//            case 23 -> new PrismaTrapesium(r(), r(), r(), r(), r());
//            case 24 -> new Balok(r(), r(), r());
//            case 25 -> new LimasPersegiPanjang(r(), r(), r());
//            case 26 -> new PrismaPersegiPanjang(r(), r(), r());
//            case 27 -> new Bola(r());
//            case 28 -> new CincinBola(r(), r());
//            case 29 -> new JuringBola(r(), r(1, 360));
//            case 30 -> new TemberengBola(r(), r());
//            case 31 -> new Kerucut(r(), r());
//            case 32 -> new KerucutTerpancung(r(), r(), r());
//            case 33 -> new Tabung(r(), r());
            default -> throw new IllegalArgumentException("Pilihan bentuk geometri tidak valid untuk generator acak: " + choice);
        };
    }


}