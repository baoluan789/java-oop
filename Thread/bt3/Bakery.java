package Thread.bt3;

public class Bakery {
    private int breadCount = 0; // Số lượng bánh hiện có
    private final int MAX_BREAD = 10; // Giới hạn số bánh

    // Sản xuất bánh mì
    public synchronized void produce() {
        while (breadCount >= MAX_BREAD) { // Nếu đủ 10 bánh thì chờ
            try {
                System.out.println("Lò bánh mì: Đủ bánh, ngừng sản xuất!");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        breadCount++;
        System.out.println("🛠️ Lò bánh mì sản xuất: " + breadCount + " cái");
        notify();
    }
    public synchronized void consume() {
        if (breadCount == 0) { // Không có bánh, khách không đợi
            System.out.println("🚶‍♂️ Khách hàng đến nhưng không có bánh → Rời đi!");
            return;
        }
        breadCount--;
        System.out.println("🍞 Khách hàng mua 1 cái, còn lại: " + breadCount);
        notify(); // Báo cho lò bánh biết có thể sản xuất tiếp
    }
}
