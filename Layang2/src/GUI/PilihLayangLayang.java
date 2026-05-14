package GUI;

import javax.swing.*;
import projek_pbo.LayangLayang;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PilihLayangLayang extends javax.swing.JPanel {

    private LayangLayang LayangLayang;

    public PilihLayangLayang() {
        initComponents();
        this.LayangLayang = new LayangLayang(10, 12, 10, 20);
        tambahkanListener();
        btnHitung.setEnabled(false);
    }

    public void setLayangLayang(LayangLayang LayangLayang) {
        this.LayangLayang = LayangLayang;
    }

    private void tambahkanListener() {
        diagonal1Baru.addActionListener(e -> updateButtonState());
        diagonal1Baru.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }

            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }
        });

        btnHitung.addActionListener(e -> updatePerhitungan());
        btnBatal.addActionListener(e -> tutupTab());
    }

    private void updateButtonState() {
        // Aktifkan tombol hanya jika text field tidak kosong
        btnHitung.setEnabled(!diagonal1Baru.getText().trim().isEmpty());
    }

    private void updatePerhitungan() {
        System.out.println("[DEBUG] Status LayangLayang: " + (LayangLayang != null ? "Ada" : "Null"));
        try {
            double diagonal1 = Double.parseDouble(diagonal1Baru.getText());
            double diagonal2 = Double.parseDouble(diagonal2Baru.getText());
            if (diagonal1 <= 0 || diagonal2 <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Setiap nilai harus lebih dari nol",
                        "Input Tidak Valid",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            double luas = LayangLayang.hitungLuas(diagonal1, diagonal2);
            double keliling = LayangLayang.hitungKeliling(diagonal1, diagonal2);
            HasilBaru.setText(String.format("Luas: %.2f\nKeliling: %.2f", luas, keliling));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Input harus:\n- Berupa angka\n- Lebih besar dari 0",
                    "Format Salah",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void tutupTab() {
        javax.swing.JTabbedPane tabbedPane = (javax.swing.JTabbedPane) this.getParent();
        if (tabbedPane != null) {
            tabbedPane.remove(this);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        diagonal1Baru = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        HasilBaru = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnHitung = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        diagonal2Baru = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        SisiPendekBaru = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        SisiPanjangBaru = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("Masukkan Diagonal 1 Baru :");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setText("Hasil :");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Ubah Nilai Belah Ketupat");

        btnHitung.setText("Hitung Nilai Baru");

        btnBatal.setText("Batal");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setText("Masukkan Diagonal 2 Baru :");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setText("Masukkan Sisi Pendek Baru :");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setText("Masukkan Sisi Panjang Baru :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(diagonal2Baru, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .addComponent(diagonal1Baru)
                                    .addComponent(SisiPendekBaru, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .addComponent(SisiPanjangBaru, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jLabel3)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 53, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(36, 36, 36)
                                .addComponent(HasilBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnBatal)
                                .addGap(31, 31, 31)
                                .addComponent(btnHitung)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(diagonal1Baru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diagonal2Baru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SisiPendekBaru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SisiPanjangBaru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(HasilBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatal)
                    .addComponent(btnHitung))
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField HasilBaru;
    public javax.swing.JTextField SisiPanjangBaru;
    public javax.swing.JTextField SisiPendekBaru;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHitung;
    public javax.swing.JTextField diagonal1Baru;
    public javax.swing.JTextField diagonal2Baru;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
