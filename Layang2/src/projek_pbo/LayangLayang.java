package projek_pbo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LayangLayang extends BangunDatar {

    public double diagonal1;
    public double diagonal2;
    public double sisiPendek;
    public double sisiPanjang;


    public LayangLayang(double diagonal1, double diagonal2, double sisiPendek, double sisiPanjang) {
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
        this.sisiPendek = sisiPendek;
        this.sisiPanjang = sisiPanjang;
        this.hitungKeliling();
        this.hitungLuas();
    }

    @Override
    public double hitungLuas() {
        this.luas = 0.5 * diagonal1 * diagonal2;
        return luas;
    }

    @Override
    public double hitungKeliling() {
        this.keliling =  2 * (sisiPendek + sisiPanjang);
        return keliling;
    }

    public double hitungLuas(double diagonal1Baru, double diagonal2Baru) {
        luas = 0.5 * diagonal1Baru * diagonal2Baru;
        return luas;
    }

    public double hitungKeliling(double sisiPendekBaru, double sisiPanjangBaru) {
        keliling = 2 * (sisiPendekBaru + sisiPanjangBaru);
        return keliling;
    }

    public void prosesInputDanValidasi() {
        Scanner inp = new Scanner(System.in);
        while (true) {
            System.out.print("\nApakah ingin mengubah nilai diagonal? (Y/N): ");
            String jawab = inp.nextLine();

            if (jawab.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.print("Masukan diagonal 1 baru: ");
                        double newDiagonal1 = inp.nextDouble();
                        System.out.print("Masukan diagonal 2 baru: ");
                        double newDiagonal2 = inp.nextDouble();
                        inp.nextLine();
                        if (newDiagonal1 <= 0 || newDiagonal2 <= 0) {
                            System.out.println("Diagonal harus lebih dari nol.\n");
                            continue;
                        }
                        diagonal1 = newDiagonal1;
                        diagonal2 = newDiagonal2;
                        luas = hitungLuas(newDiagonal1, newDiagonal2);
                        keliling = hitungKeliling(sisiPendek, sisiPanjang);
                        System.out.printf("\nLuas Layang-Layang Baru: %.2f\n", luas);
                        System.out.printf("Keliling Layang-Layang Baru: %.2f\n", keliling);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Input tidak valid. Silakan masukkan angka yang benar.");
                        inp.nextLine(); // Clear the invalid input
                    }
                }
                break;
            } else if (jawab.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Pilihan tidak valid. Silakan masukkan Y atau N.");
            }
        }
    }
    

    public double getSisiPendek(){
        return sisiPendek;
    }

    public double getDiagonal1() {
        return diagonal1;
    }

    public double getDiagonal2() {
        return diagonal2;
    }

    public double getSisiPanjang(){
        return sisiPanjang;
    }
}
