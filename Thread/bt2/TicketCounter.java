package Thread.bt2;

public class TicketCounter {
    private int ticketNumber = 1; // Số vé bắt đầu
    private final int MAX_TICKETS = 10; // Giới hạn vé bán

    public synchronized void sellTicket(String campName) {
        if (ticketNumber <= MAX_TICKETS) {
            System.out.println(campName + " bán vé số: " + ticketNumber);
            ticketNumber++; // Tăng số vé sau khi bán
        } else {
            System.out.println(campName + " thông báo: Hết vé!");
        }
    }
}


