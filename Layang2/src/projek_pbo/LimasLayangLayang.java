package projek_pbo;

import java.util.InputMismatchException;
import java.util.Scanner;


public class LimasLayangLayang extends LayangLayang {
    private double tinggi;
    private double volume;
    private double luasPermukaan;

    public LimasLayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang, double tinggi) {
        super(diagonal1, diagonal2, sisiPendek, sisiPanjang);
        this.tinggi = tinggi;
        this.hitungVolume();
        this.hitungLuasPermukaan();
    }

    public double hitungLuasPermukaan() {
        double luasAlas = super.luas;
        double a = super.getSisiPendek();
        double b = super.getSisiPanjang();
        double tinggiSegitigaPendek = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(a / 2, 2));
        double tinggiSegitigaPanjang = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(b / 2, 2));
        double luasSisiTegak = 2 * (0.5 * a * tinggiSegitigaPendek) + 2 * (0.5 * b * tinggiSegitigaPanjang);
        luasPermukaan =  luasAlas + luasSisiTegak;
        return luasPermukaan;
    }

    public double hitungVolume() {
        volume =  (1.0 / 3.0) * super.luas * tinggi;
        return volume;
    }

    public double hitungVolume(double diagonal1Baru, double diagonal2Baru, double tinggiBaru) {
        volume = (1.0 / 3.0) * (diagonal1Baru * diagonal2Baru) / 2 * tinggiBaru;
        return volume;
    }

    public double hitungLuasPermukaan(double diagonal1Baru, double diagonal2Baru, double tinggiBaru) {
        double luasAlas = (diagonal1Baru * diagonal2Baru) / 2;
        double a = super.getSisiPendek();
        double b = super.getSisiPanjang();
        double tinggiSegitigaPendek = Math.sqrt(Math.pow(tinggiBaru, 2) + Math.pow(a / 2, 2));
        double tinggiSegitigaPanjang = Math.sqrt(Math.pow(tinggiBaru, 2) + Math.pow(b / 2, 2));
        double luasSisiTegak = 2 * (0.5 * a * tinggiSegitigaPendek) + 2 * (0.5 * b * tinggiSegitigaPanjang);
        luasPermukaan = luasAlas + luasSisiTegak;
        return luasPermukaan;
    }

    public void prosesInputDanValidasi() {
        Scanner inp = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah ingin mengubah nilai diagonal dan tinggi? (Y/N): ");
            String jawab = inp.nextLine();

            if (jawab.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukkan diagonal 1 baru: ");
                        double newDiagonal1 = inp.nextDouble();
                        System.out.print("Masukkan diagonal 2 baru: ");
                        double newDiagonal2 = inp.nextDouble();
                        System.out.print("Masukkan tinggi baru: ");
                        double newTinggi = inp.nextDouble();
                        inp.nextLine();
                        if (newDiagonal1 <= 0 || newDiagonal2 <= 0 || newTinggi <= 0) {
                            System.out.println("Diagonal dan tinggi harus lebih dari nol.\n");
                            continue;
                        }
                        diagonal1 = newDiagonal1;
                        diagonal2 = newDiagonal2;
                        tinggi = newTinggi;
                        volume = hitungVolume(newDiagonal1, newDiagonal2, newTinggi);
                        luasPermukaan = hitungLuasPermukaan(newDiagonal1, newDiagonal2, newTinggi);
                        System.out.printf("\nVolume Limas Baru: %.2f\n", volume);
                        System.out.printf("Luas Permukaan Limas Baru: %.2f\n", luasPermukaan);
                        break; // keluar dari loop input
                    } catch (InputMismatchException e) {
                        System.out.println("Input tidak valid. Silakan masukkan angka yang benar.");
                        inp.nextLine(); // bersihkan input yang salah
                    }
                }
                break;
            } else if (jawab.equalsIgnoreCase("N")) {
                break; // keluar dari loop utama
            } else {
                System.out.println("Pilihan tidak valid. Silakan masukkan Y atau N.");
            }
        }
    }

    public double getTinggi() {
        return tinggi;
    }

    public double getVolume() {
        return volume;
    }

    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    public double getDiagonal1() {
        return diagonal1;
    }

    public double getDiagonal2() {
        return diagonal2;
    }

    
} 