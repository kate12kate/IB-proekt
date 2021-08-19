package mk.ukim.finki.ibproekt.service;

public interface OTPService {
    public int generateOTP(String key);
    public int getOtp(String key);
    public void clearOtp(String key);
}
