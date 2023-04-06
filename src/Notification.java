public class Notification {
    private String reciever_id;

    public Notification(String reciever_id) {
        this.reciever_id = reciever_id;
    }

    public void SendNotification() {
        System.out.println("Notification sent to " + this.reciever_id);
    }
}
