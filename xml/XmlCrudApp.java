package com.example.xml;
import javax.swing.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XmlCrudApp extends JFrame {
    private List<Element> elements = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> elementList;
    private JTextField tagNameField;
    private JTextArea contentArea;
    private Document document;

    public XmlCrudApp() {
        // Thiết lập giao diện chính
        setTitle("XML CRUD Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Khởi tạo Document XML
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Tag Name:"));
        tagNameField = new JTextField();
        inputPanel.add(tagNameField);
        inputPanel.add(new JLabel("Content:"));
        contentArea = new JTextArea(2, 20);
        inputPanel.add(new JScrollPane(contentArea));

        JButton createButton = new JButton("Create");
        JButton updateButton = new JButton("Update");
        inputPanel.add(createButton);
        inputPanel.add(updateButton);

        // Danh sách thẻ XML
        elementList = new JList<>(listModel);
        elementList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        elementList.addListSelectionListener(e -> {
            int selectedIndex = elementList.getSelectedIndex();
            if (selectedIndex >= 0) {
                Element selectedElement = elements.get(selectedIndex);
                tagNameField.setText(selectedElement.getTagName());
                contentArea.setText(selectedElement.getTextContent());
            }
        });

        // Panel nút chức năng
        JPanel buttonPanel = new JPanel();
        JButton deleteButton = new JButton("Delete");
        JButton saveButton = new JButton("Save to File");
        JButton readButton = new JButton("Read File");
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(readButton);

        // Thêm các thành phần vào frame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(elementList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Xử lý sự kiện nút Create
        createButton.addActionListener(e -> {
            String tagName = tagNameField.getText().trim();
            String content = contentArea.getText().trim();
            if (!tagName.isEmpty()) {
                Element element = document.createElement(tagName);
                element.setTextContent(content);
                elements.add(element);
                listModel.addElement(tagName + ": " + content);
                tagNameField.setText("");
                contentArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Tên thẻ không được để trống!");
            }
        });

        // Xử lý sự kiện nút Update
        updateButton.addActionListener(e -> {
            int selectedIndex = elementList.getSelectedIndex();
            if (selectedIndex >= 0) {
                String tagName = tagNameField.getText().trim();
                String content = contentArea.getText().trim();
                if (!tagName.isEmpty()) {
                    // Tạo phần tử mới thay vì sửa tên thẻ
                    Element newElement = document.createElement(tagName);
                    newElement.setTextContent(content);
                    elements.set(selectedIndex, newElement);
                    listModel.set(selectedIndex, tagName + ": " + content);
                } else {
                    JOptionPane.showMessageDialog(this, "Tên thẻ không được để trống!");
                }
            }
        });

        // Xử lý sự kiện nút Delete
        deleteButton.addActionListener(e -> {
            int selectedIndex = elementList.getSelectedIndex();
            if (selectedIndex >= 0) {
                elements.remove(selectedIndex);
                listModel.remove(selectedIndex);
                tagNameField.setText("");
                contentArea.setText("");
            }
        });

        // Xử lý sự kiện nút Save
        saveButton.addActionListener(e -> {
            JComboBox<String> mergeType = new JComboBox<>(new String[]{"Lồng nhau", "Ngang cấp"});
            int result = JOptionPane.showConfirmDialog(this, mergeType, "Chọn kiểu ghép", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String mergeOption = (String) mergeType.getSelectedItem();
                saveToXmlFile(mergeOption.equals("Lồng nhau"));
            }
        });

        // Xử lý sự kiện nút Read
        readButton.addActionListener(e -> readXmlFile());
    }

    private void saveToXmlFile(boolean nested) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("Root");
            doc.appendChild(root);

            if (nested) {
                Element current = root;
                for (Element element : elements) {
                    Element newElement = doc.createElement(element.getTagName());
                    newElement.setTextContent(element.getTextContent());
                    current.appendChild(newElement);
                    current = newElement;
                }
            } else {
                for (Element element : elements) {
                    Element newElement = doc.createElement(element.getTagName());
                    newElement.setTextContent(element.getTextContent());
                    root.appendChild(newElement);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("output.xml"));
            transformer.transform(source, result);

            JOptionPane.showMessageDialog(this, "Lưu file XML thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu file XML!");
        }
    }

    private void readXmlFile() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("output.xml"));
            doc.getDocumentElement().normalize();

            StringBuilder content = new StringBuilder();
            NodeList nodeList = doc.getElementsByTagName("*");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    content.append("Thẻ: ").append(element.getTagName())
                            .append(", Nội dung: ").append(element.getTextContent())
                            .append("\n");
                }
            }

            JOptionPane.showMessageDialog(this, new JScrollPane(new JTextArea(content.toString())),
                    "Nội dung file XML", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file XML!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            XmlCrudApp app = new XmlCrudApp();
            app.setVisible(true);
        });
    }
}