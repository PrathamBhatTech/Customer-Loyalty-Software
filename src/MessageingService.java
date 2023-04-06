public class MessageingService {
    public String message;

    public MessageingService(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendMessage() {
        System.out.println(this.message);
    }
}
