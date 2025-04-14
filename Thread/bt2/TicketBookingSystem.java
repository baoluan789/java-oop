package Thread.bt2;

public class TicketBookingSystem {
    public static void main(String[] args) {
        TicketCounter counter = new TicketCounter(); // Quầy vé dùng chung

        TicketSeller camp1 = new TicketSeller(counter, "Trại 1");
        TicketSeller camp2 = new TicketSeller(counter, "Trại 2");
        TicketSeller camp3 = new TicketSeller(counter, "Trại 3");

        camp1.start();
        camp2.start();
        camp3.start();
    }
}
