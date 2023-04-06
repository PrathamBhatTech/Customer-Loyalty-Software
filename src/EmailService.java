public class EmailService {
    public String email;

    public EmailService(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void sendEmail() {
        System.out.println(this.email);
    }
}
