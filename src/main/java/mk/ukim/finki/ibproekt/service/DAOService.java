package mk.ukim.finki.ibproekt.service;

public interface DAOService {

    void update2FAProperties(String userid, String twofacode);

    boolean checkCode(String id, String code);
}
