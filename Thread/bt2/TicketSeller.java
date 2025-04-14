package Thread.bt2;

public class TicketSeller extends Thread{
    private TicketCounter counter;
    private String campName;

    public TicketSeller(TicketCounter counter, String campName) {
        this.counter = counter;
        this.campName = campName;
    }

    public void run() {
        while (true) {
            synchronized (counter) {
                if (counter != null) {
                    counter.sellTicket(campName);
                }
            }
            try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}

