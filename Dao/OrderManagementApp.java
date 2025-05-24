package com.example.Dao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderManagementApp extends JFrame {
    private JTextField customerNameField, productIdField, quantityField;
    private JTextArea orderDetailsArea;
    private JComboBox<String> customerComboBox;
    private DefaultListModel<String> productListModel;
    private List<ProductItem> orderItems = new ArrayList<>();
    private Connection conn;

    static class ProductItem {
        int productId;
        int quantity;

        ProductItem(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }

    public OrderManagementApp() {
        // Kết nối cơ sở dữ liệu SQL Server
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=order_management;user=sa;password=your_password;encrypt=false");
            initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu SQL Server!");
        }

        // Thiết lập giao diện chính
        setTitle("Ứng Dụng Quản Lý Đơn Hàng");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.add(new JLabel("Tên khách hàng:"));
        customerNameField = new JTextField();
        inputPanel.add(customerNameField);
        inputPanel.add(new JLabel("Chọn khách hàng:"));
        customerComboBox = new JComboBox<>();
        loadCustomers();
        inputPanel.add(customerComboBox);
        inputPanel.add(new JLabel("ID sản phẩm:"));
        productIdField = new JTextField();
        inputPanel.add(productIdField);
        inputPanel.add(new JLabel("Số lượng:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);
        JButton addProductButton = new JButton("Thêm sản phẩm");
        JButton createOrderButton = new JButton("Tạo đơn hàng");
        inputPanel.add(addProductButton);
        inputPanel.add(createOrderButton);

        // Danh sách sản phẩm trong đơn hàng
        productListModel = new DefaultListModel<>();
        JList<String> productList = new JList<>(productListModel);
        JScrollPane productScrollPane = new JScrollPane(productList);

        // Khu vực hiển thị chi tiết đơn hàng
        orderDetailsArea = new JTextArea(10, 40);
        orderDetailsArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(orderDetailsArea);

        // Panel nút chức năng
        JPanel buttonPanel = new JPanel();
        JButton viewHistoryButton = new JButton("Xem lịch sử đơn hàng");
        buttonPanel.add(viewHistoryButton);

        // Thêm các thành phần vào frame
        add(inputPanel, BorderLayout.NORTH);
        add(productScrollPane, BorderLayout.CENTER);
        add(detailsScrollPane, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);

        // Xử lý sự kiện nút Thêm sản phẩm
        addProductButton.addActionListener(e -> {
            try {
                int productId = Integer.parseInt(productIdField.getText().trim());
                int quantity = Integer.parseInt(quantityField.getText().trim());
                ProductDAO productDAO = new ProductDAO();
                Product product = productDAO.getProduct(productId);
                if (product != null && quantity > 0) {
                    orderItems.add(new ProductItem(productId, quantity));
                    productListModel.addElement("Sản phẩm: " + product.name + ", Số lượng: " + quantity);
                    productIdField.setText("");
                    quantityField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "ID sản phẩm không hợp lệ hoặc số lượng phải lớn hơn 0!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ID sản phẩm và số lượng hợp lệ!");
            }
        });

        // Xử lý sự kiện nút Tạo đơn hàng
        createOrderButton.addActionListener(e -> {
            String customerName = customerNameField.getText().trim();
            String selectedCustomer = (String) customerComboBox.getSelectedItem();
            int customerId = -1;

            if (!customerName.isEmpty()) {
                CustomerDAO customerDAO = new CustomerDAO();
                customerId = customerDAO.addCustomer(customerName);
            } else if (selectedCustomer != null) {
                customerId = Integer.parseInt(selectedCustomer.split(":")[0]);
            }

            if (customerId != -1 && !orderItems.isEmpty()) {
                OrderDAO orderDAO = new OrderDAO();
                int orderId = orderDAO.addOrder(customerId);
                for (ProductItem item : orderItems) {
                    orderDAO.addOrderItem(orderId, item.productId, item.quantity);
                }
                double total = calculateOrderTotal(orderId);
                orderDetailsArea.setText("Đơn hàng #" + orderId + " được tạo!\nTổng tiền: " + total + " VNĐ");
                orderItems.clear();
                productListModel.clear();
                loadCustomers();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin khách hàng và ít nhất một sản phẩm!");
            }
        });

        // Xử lý sự kiện nút Xem lịch sử
        viewHistoryButton.addActionListener(e -> {
            String selectedCustomer = (String) customerComboBox.getSelectedItem();
            if (selectedCustomer != null) {
                int customerId = Integer.parseInt(selectedCustomer.split(":")[0]);
                OrderDAO orderDAO = new OrderDAO();
                String history = orderDAO.getOrderHistory(customerId);
                orderDetailsArea.setText(history);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng!");
            }
        });
    }

    private void initializeDatabase() throws SQLException {
        Statement stmt = conn.createStatement();
        // Tạo bảng customers
        stmt.execute("IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'customers') " +
                "CREATE TABLE customers (id INT IDENTITY(1,1) PRIMARY KEY, name NVARCHAR(255) NOT NULL)");
        // Tạo bảng products
        stmt.execute("IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'products') " +
                "CREATE TABLE products (id INT PRIMARY KEY, name NVARCHAR(255) NOT NULL, price FLOAT NOT NULL)");
        // Tạo bảng orders
        stmt.execute("IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'orders') " +
                "CREATE TABLE orders (id INT IDENTITY(1,1) PRIMARY KEY, customer_id INT, FOREIGN KEY(customer_id) REFERENCES customers(id))");
        // Tạo bảng order_items
        stmt.execute("IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'order_items') " +
                "CREATE TABLE order_items (order_id INT, product_id INT, quantity INT, FOREIGN KEY(order_id) REFERENCES orders(id), FOREIGN KEY(product_id) REFERENCES products(id))");

        // Thêm dữ liệu mẫu cho products nếu chưa có
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM products");
        if (rs.next() && rs.getInt(1) == 0) {
            stmt.execute("INSERT INTO products (id, name, price) VALUES (1, N'Sản phẩm A', 100000)");
            stmt.execute("INSERT INTO products (id, name, price) VALUES (2, N'Sản phẩm B', 200000)");
            stmt.execute("INSERT INTO products (id, name, price) VALUES (3, N'Sản phẩm C', 150000)");
        }
    }

    private void loadCustomers() {
        customerComboBox.removeAllItems();
        CustomerDAO customerDAO = new CustomerDAO();
        List<String> customers = customerDAO.getAllCustomers();
        for (String customer : customers) {
            customerComboBox.addItem(customer);
        }
    }

    private double calculateOrderTotal(int orderId) {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.calculateOrderTotal(orderId);
    }

    class CustomerDAO {
        public int addCustomer(String name) {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO customers (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return -1;
        }

        public List<String> getAllCustomers() {
            List<String> customers = new ArrayList<>();
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT id, name FROM customers");
                while (rs.next()) {
                    customers.add(rs.getInt("id") + ": " + rs.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return customers;
        }
    }

    class ProductDAO {
        public Product getProduct(int id) {
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT id, name, price FROM products WHERE id = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class OrderDAO {
        public int addOrder(int customerId) {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO orders (customer_id) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, customerId);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return -1;
        }

        public void addOrderItem(int orderId, int productId, int quantity) {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)");
                ps.setInt(1, orderId);
                ps.setInt(2, productId);
                ps.setInt(3, quantity);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public String getOrderHistory(int customerId) {
            StringBuilder history = new StringBuilder();
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT o.id, p.name, p.price, oi.quantity FROM orders o " +
                                "JOIN order_items oi ON o.id = oi.order_id " +
                                "JOIN products p ON oi.product_id = p.id " +
                                "WHERE o.customer_id = ?"
                );
                ps.setInt(1, customerId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    history.append("Đơn hàng #").append(rs.getInt("id"))
                            .append(": Sản phẩm: ").append(rs.getString("name"))
                            .append(", Số lượng: ").append(rs.getInt("quantity"))
                            .append(", Giá: ").append(rs.getDouble("price")).append(" VNĐ\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return history.toString();
        }

        public double calculateOrderTotal(int orderId) {
            double total = 0;
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT p.price, oi.quantity FROM order_items oi " +
                                "JOIN products p ON oi.product_id = p.id WHERE oi.order_id = ?"
                );
                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    total += rs.getDouble("price") * rs.getInt("quantity");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return total;
        }
    }

    static class Product {
        int id;
        String name;
        double price;

        Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrderManagementApp app = new OrderManagementApp();
            app.setVisible(true);
        });
    }
}