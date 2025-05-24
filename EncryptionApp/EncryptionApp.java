package com.example.EncryptionApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class EncryptionApp extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;
    private JComboBox<String> algorithmComboBox;
    private Connection conn;
    private SecretKey aesKey;
    private KeyPair rsaKeyPair;

    // Interface Encryptable
    interface Encryptable {
        String encrypt(String input) throws Exception;
        String decrypt(String input) throws Exception;
    }

    // Triển khai AES
    class AESEncryptor implements Encryptable {
        private SecretKey key;

        public AESEncryptor(SecretKey key) {
            this.key = key;
        }

        @Override
        public String encrypt(String input) throws Exception {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        }

        @Override
        public String decrypt(String input) throws Exception {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(input);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted);
        }
    }

    // Triển khai RSA
    class RSAEncryptor implements Encryptable {
        private PublicKey publicKey;
        private PrivateKey privateKey;

        public RSAEncryptor(KeyPair keyPair) {
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
        }

        @Override
        public String encrypt(String input) throws Exception {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypted = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        }

        @Override
        public String decrypt(String input) throws Exception {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decoded = Base64.getDecoder().decode(input);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted);
        }
    }

    public EncryptionApp() {
        // Kết nối SQL Server
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=encryption_db;user=sa;password=your_password;encrypt=false");
            initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu SQL Server!");
        }

        // Khởi tạo khóa mã hóa
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            aesKey = keyGen.generateKey();
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            rsaKeyPair = keyPairGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi khởi tạo khóa mã hóa!");
        }

        // Thiết lập giao diện chính
        setTitle("Ứng Dụng Mã Hóa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Xâu cần mã hóa:"));
        inputField = new JTextField();
        inputPanel.add(inputField);

        inputPanel.add(new JLabel("Chọn thuật toán:"));
        algorithmComboBox = new JComboBox<>(new String[]{"AES", "RSA"});
        inputPanel.add(algorithmComboBox);

        JButton encryptButton = new JButton("Mã hóa");
        JButton decryptButton = new JButton("Giải mã");
        JButton saveButton = new JButton("Lưu vào DB");
        inputPanel.add(encryptButton);
        inputPanel.add(decryptButton);
        inputPanel.add(saveButton);

        inputPanel.add(new JLabel("Kết quả:"));
        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);
        inputPanel.add(new JScrollPane(resultArea));

        // Thêm panel vào frame
        add(inputPanel, BorderLayout.CENTER);

        // Xử lý sự kiện nút Mã hóa
        encryptButton.addActionListener(e -> {
            String input = inputField.getText().trim();
            if (!input.isEmpty()) {
                try {
                    Encryptable encryptor = algorithmComboBox.getSelectedItem().equals("AES") ? new AESEncryptor(aesKey) : new RSAEncryptor(rsaKeyPair);
                    String result = encryptor.encrypt(input);
                    resultArea.setText("Mã hóa: " + result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi mã hóa: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập xâu cần mã hóa!");
            }
        });

        // Xử lý sự kiện nút Giải mã
        decryptButton.addActionListener(e -> {
            String input = inputField.getText().trim();
            if (!input.isEmpty()) {
                try {
                    Encryptable encryptor = algorithmComboBox.getSelectedItem().equals("AES") ? new AESEncryptor(aesKey) : new RSAEncryptor(rsaKeyPair);
                    String result = encryptor.decrypt(input);
                    resultArea.setText("Giải mã: " + result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi giải mã: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập xâu cần giải mã!");
            }
        });

        // Xử lý sự kiện nút Lưu vào DB
        saveButton.addActionListener(e -> {
            String input = inputField.getText().trim();
            String algorithm = (String) algorithmComboBox.getSelectedItem();
            String encrypted = resultArea.getText().startsWith("Mã hóa: ") ? resultArea.getText().substring(8) : "";
            if (!input.isEmpty() && !encrypted.isEmpty()) {
                saveToDatabase(input, encrypted, algorithm);
                resultArea.setText(resultArea.getText() + "\nĐã lưu vào cơ sở dữ liệu!");
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng mã hóa xâu trước khi lưu!");
            }
        });
    }

    private void initializeDatabase() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'encrypted_data') " +
                "CREATE TABLE encrypted_data (id INT IDENTITY(1,1) PRIMARY KEY, original NVARCHAR(255), encrypted NVARCHAR(255), algorithm NVARCHAR(50))");
    }

    private void saveToDatabase(String original, String encrypted, String algorithm) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO encrypted_data (original, encrypted, algorithm) VALUES (?, ?, ?)");
            ps.setString(1, original);
            ps.setString(2, encrypted);
            ps.setString(3, algorithm);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu vào cơ sở dữ liệu!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EncryptionApp app = new EncryptionApp();
            app.setVisible(true);
        });
    }
}