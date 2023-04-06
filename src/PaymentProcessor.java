public class PaymentProcessor {
    private PointSystem pointSystem;
    private MessageingService messageingService;
    private EmailService emailService;

    public PaymentProcessor(PointSystem pointSystem, MessageingService messageingService, EmailService emailService) {
        this.pointSystem = pointSystem;
        this.messageingService = messageingService;
        this.emailService = emailService;
    }

    public void processPayment(int amount) {
        this.pointSystem.addPoints(amount);
        this.messageingService.setMessage("You have " + this.pointSystem.getPoints() + " points.");
        this.messageingService.printMessage();
        this.emailService.setEmail("You have " + this.pointSystem.getPoints() + " points.");
        this.emailService.printEmail();
    }
}
