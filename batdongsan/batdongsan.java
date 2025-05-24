package com.example.batdongsan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class batdongsan {
    private static final String BASE_URL = "https://batdongsan.com.vn/nha-dat-ban?page=";
    private static final int MAX_PAGES = 5;
    private static final String CSV_FILE = "C:\\Users\\ADMIN\\IdeaProjects\\btjava\\src\\main\\java\\com\\example\\luu.csv";
    private static final ReentrantLock lock = new ReentrantLock();
    private static final AtomicInteger propertyCount = new AtomicInteger(0);

    static class Property {
        String title;
        String price;
        String address;
        String description;

        public Property(String title, String price, String address, String description) {
            this.title = title;
            this.price = price;
            this.address = address;
            this.description = description;
        }

        String toCsv() {
            return String.format("\"%s\",\"%s\",\"%s\",\"%s\"\n",
                    escapeCsv(title), escapeCsv(price), escapeCsv(address), escapeCsv(description));
        }

        private String escapeCsv(String data) {
            if (data == null) return "";
            return data.replace("\"", "\"\"").replace("\n", " ");
        }
    }

    static class CrawlerTask implements Runnable {
        private final int pageNumber;

        CrawlerTask(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        @Override
        public void run() {
            try {
                String url = BASE_URL + pageNumber;
                Document doc = Jsoup.connect(url).get();
                Elements listings = doc.select("div.js__card");

                List<Property> properties = new ArrayList<>();
                for (Element listing : listings) {
                    String title = listing.selectFirst("h3.js__card-title") != null ?
                            listing.selectFirst("h3.js__card-title").text() : "";
                    String price = listing.selectFirst("span.js__card-price") != null ?
                            listing.selectFirst("span.js__card-price").text() : "";
                    String address = listing.selectFirst("div.js__card-address") != null ?
                            listing.selectFirst("div.js__card-address").text() : "";
                    String description = listing.selectFirst("div.js__card-description") != null ?
                            listing.selectFirst("div.js__card-description").text() : "";

                    if (!title.isEmpty()) {
                        properties.add(new Property(title, price, address, description));
                        propertyCount.incrementAndGet();
                    }
                }

                writeToCsv(properties);

            } catch (IOException e) {
                System.err.println("Lỗi khi crawl trang " + pageNumber + ": " + e.getMessage());
            }
        }

        private void writeToCsv(List<Property> properties) {
            lock.lock();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE, true))) {
                for (Property property : properties) {
                    writer.write(property.toCsv());
                }
            } catch (IOException e) {
                System.err.println("Lỗi khi ghi file CSV: " + e.getMessage());
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            writer.write("\"Tiêu đề\",\"Giá\",\"Địa chỉ\",\"Mô tả\"\n");
        } catch (IOException e) {
            System.err.println("Lỗi khi tạo file CSV: " + e.getMessage());
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 1; i <= MAX_PAGES; i++) {
            executor.submit(new CrawlerTask(i));
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("Tổng số bất động sản đã crawl: " + propertyCount.get());
    }
}