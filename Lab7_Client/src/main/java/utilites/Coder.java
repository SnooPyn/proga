package utilites;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Coder {

    protected static String  toCode(String password) throws NoSuchAlgorithmException {
        password += "czcesaeq3424124qfw5";
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}