

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import projek_pbo.*;

public class GeometriGUI extends JFrame {
    private JComboBox<String> shapeComboBox;
    private JTextArea outputArea;
    private JButton calculateButton;
    private JButton threadButton;
    private JPanel parameterPanel;
    private List<JTextField> parameterFields = new ArrayList<>();
    private List<BendaGeometri> shapes = new ArrayList<>();

    public GeometriGUI() {
        setTitle("Program Perhitungan Benda Geometri");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        JLabel titleLabel = new JLabel("Program Perhitungan Benda Geometri");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Control panels
        JPanel controlPanel = createControlPanel();
        parameterPanel = new JPanel();
        parameterPanel.setLayout(new BoxLayout(parameterPanel, BoxLayout.Y_AXIS));
        parameterPanel.setBorder(BorderFactory.createTitledBorder("Parameter Bentuk"));
        parameterPanel.setBackground(Color.WHITE);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        outputArea.setBackground(new Color(250, 250, 250));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Hasil Perhitungan"));
        scrollPane.setPreferredSize(new Dimension(200, 300));

        // Add components to main panel
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(controlPanel, BorderLayout.NORTH);
        topPanel.add(parameterPanel, BorderLayout.CENTER);
        topPanel.setBackground(new Color(240, 240, 240));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // Initialize
        updateOutput("Selamat datang di Program Perhitungan Benda Geometri\n");
        updateParameterFields();
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 15, 5));
        panel.setBackground(new Color(240, 240, 240));

        // Shape selection panel
        JPanel shapePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        shapePanel.setBackground(new Color(240, 240, 240));
        shapePanel.add(new JLabel("Pilih Bentuk Geometri:"));

        String[] shapeOptions = { 
            "Layang-Layang","Limas Layang Layang", "Prisma Layang Layang", 
        };

        shapeComboBox = new JComboBox<>(shapeOptions);
        shapeComboBox.setPreferredSize(new Dimension(250, 30));
        shapeComboBox.addActionListener(e -> updateParameterFields());
        shapePanel.add(shapeComboBox);

        calculateButton = new JButton("Hitung");
        calculateButton.setPreferredSize(new Dimension(120, 30));
        calculateButton.setBackground(Color.WHITE);
        calculateButton.setForeground(Color.BLACK);
        calculateButton.addActionListener(this::hitungBentuk);
        shapePanel.add(calculateButton);

        threadButton = new JButton("Hitung dengan Thread Pool");
        threadButton.setPreferredSize(new Dimension(220, 30));
        threadButton.setBackground(Color.WHITE);
        threadButton.setForeground(Color.BLACK);
        threadButton.addActionListener(e -> prosesDenganThread());
        shapePanel.add(threadButton);

        panel.add(shapePanel);

        return panel;
    }

    private void updateParameterFields() {
        parameterPanel.removeAll();
        parameterFields = new ArrayList<>();

        String selectedShape = (String) shapeComboBox.getSelectedItem();
        List<String> labels = getParameterLabels(selectedShape);

        for (String label : labels) {
            JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            fieldPanel.setBackground(Color.WHITE);
            fieldPanel.add(new JLabel(label + ": "));
            JTextField field = new JTextField("1.0", 10);
            field.setPreferredSize(new Dimension(100, 30));
            parameterFields.add(field);
            fieldPanel.add(field);
            parameterPanel.add(fieldPanel);
        }

        parameterPanel.revalidate();
        parameterPanel.repaint();
    }

    private List<String> getParameterLabels(String shape) {
        switch (shape) {
            case "Layang-Layang": return List.of("Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang");
            case "Limas Layang Layang": return List.of("Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang", "Tinggi Limas");
            case "Prisma Layang Layang": return List.of("Diagonal 1", "Diagonal 2", "Sisi Pendek", "Sisi Panjang", "Tinggi Prisma");
            default: return List.of();
        }
    }

    private void hitungBentuk(ActionEvent e) {
        bersihkanOutput();
        try {
            BendaGeometri shape = buatBentukDariInput();
            if (shape != null) {
                updateOutputDenganHasil(shape, (String) shapeComboBox.getSelectedItem());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private BendaGeometri buatBentukDariInput() {
        String selected = (String) shapeComboBox.getSelectedItem();
        List<Double> values = new ArrayList<>();
        for (JTextField field : parameterFields) {
            values.add(Double.parseDouble(field.getText()));
        }

        switch (selected) {
            case "Layang-Layang": return new LayangLayang(values.get(0), values.get(1), values.get(2), values.get(3));
            case "Limas Layang Layang": return new LimasLayangLayang(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
            case "Prisma Layang Layang": return new PrismaLayangLayang(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
            default: return null;
        }
    }

    private void updateOutputDenganHasil(BendaGeometri shape, String nama) {
        StringBuilder result = new StringBuilder();
        result.append("\n==============================\n");
        result.append("Hasil Perhitungan: ").append(nama).append("\n");
        result.append("==============================\n");

        try {
            for (Field f : shape.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                result.append(String.format("%-15s = %10.2f\n", f.getName(), f.get(shape)));
            }
        } catch (Exception ignored) {}

        if (shape instanceof BangunDatar) {
            BangunDatar bd = (BangunDatar) shape;
            result.append(String.format("%-15s = %10.2f\n", "Luas", bd.hitungLuas()));
            result.append(String.format("%-15s = %10.2f\n", "Keliling", bd.hitungKeliling()));
        }

        try {
            var volumeMethod = shape.getClass().getMethod("hitungVolume");
            var luasPermukaanMethod = shape.getClass().getMethod("hitungLuasPermukaan");

            double volume = (double) volumeMethod.invoke(shape);
            double luasPermukaan = (double) luasPermukaanMethod.invoke(shape);

            result.append(String.format("%-15s = %10.2f\n", "Volume", volume));
            result.append(String.format("%-15s = %10.2f\n", "Luas Permukaan", luasPermukaan));
        } catch (Exception ignored) {}

        result.append("==============================\n\n");
        updateOutput(result.toString());
    }

    private void prosesDenganThread() {
        bersihkanOutput();
        
        // Generate 3 bentuk acak (1 dari setiap jenis)
        shapes.clear();
        for (int i = 1; i < 3; i++) {
            shapes.add(generateRandomBendaGeometri(i));
        }
        
        updateOutput("Memulai pemrosesan 3 bentuk geometri dengan Thread Pool:\n");
        for (BendaGeometri shape : shapes) {
            updateOutput("- " + shape.getClass().getSimpleName() + "\n");
        }
        updateOutput("\n");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (BendaGeometri shape : shapes) {
            executor.submit(() -> {
                String hasil = prosesBentuk(shape);
                SwingUtilities.invokeLater(() -> {
                    updateOutput(hasil);
                });
            });
        }

        executor.shutdown();
    }

    private String prosesBentuk(BendaGeometri shape) {
        StringBuilder hasil = new StringBuilder();
        String namaThread = Thread.currentThread().getName();
        String namaBentuk = shape.getClass().getSimpleName();

        try {
            hasil.append("Thread: ").append(namaThread).append(" - ").append(namaBentuk).append("\n");

            // Proses bangun datar
            if (shape instanceof BangunDatar) {
                BangunDatar bd = (BangunDatar) shape;
                double keliling = bd.hitungKeliling();
                double luas = bd.hitungLuas();
                hasil.append(String.format("2D -> Keliling: %.2f, Luas: %.2f%n", keliling, luas));
            }

            // Proses bangun ruang
            try {
                var methodVolume = shape.getClass().getMethod("hitungVolume");
                var methodLuasPermukaan = shape.getClass().getMethod("hitungLuasPermukaan");

                double volume = (double) methodVolume.invoke(shape);
                double luasPermukaan = (double) methodLuasPermukaan.invoke(shape);

                hasil.append(String.format("3D -> Volume: %.2f, Luas Permukaan: %.2f%n", 
                    volume, luasPermukaan));
            } catch (NoSuchMethodException ignored) {
                // Lewati jika tidak ada method 3D
            }

            hasil.append("---------------------------\n");
        } catch (Exception e) {
            hasil.append("Error memproses ").append(namaBentuk).append(": ")
                 .append(e.getMessage()).append("\n");
        }

        return hasil.toString();
    }

        private final Random random = new Random();

    private BendaGeometri generateRandomBendaGeometri(int pilihan) {
        switch (pilihan) {
            case 1: 
                return new LayangLayang(
                    random.nextDouble() * 10 + 1,
                    random.nextDouble() * 10 + 1,
                    random.nextDouble() * 10 + 1,
                    random.nextDouble() * 10 + 1
                );
            case 2: 
                return new LimasLayangLayang(
                    random.nextDouble() * 10 + 1,
                    random.nextDouble() * 10 + 1,
                    random.nextDouble() * 10 + 1,
                    random.nextDouble() * 10 + 1,
                    random.nextDouble() * 10 + 1
                );
            case 3: 
                return new PrismaLayangLayang(
                    random.nextDouble() * 10 + 1,
                    random.nextDouble() * 10 + 1,
                    random.nextDouble() * 10 + 1,
                    random.nextDouble() * 10 + 1,
                    random.nextDouble() * 10 + 1
                );
            default: 
            throw new IllegalArgumentException("Pilihan tidak valid: " + pilihan);
    }
}

    private void bersihkanOutput() {
        outputArea.setText("");
    }

    private void updateOutput(String text) {
        outputArea.append(text);
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            GeometriGUI app = new GeometriGUI();
            app.setVisible(true);
            app.setLocationRelativeTo(null);
        });
    }
}