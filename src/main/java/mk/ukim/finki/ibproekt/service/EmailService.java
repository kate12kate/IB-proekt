package mk.ukim.finki.ibproekt.service;

public interface EmailService {
    public void sendOtpMessage(String to,String subject,String message);
}
