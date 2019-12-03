package mandalahayu.keamanan;

import mandalahayu.model.petugas;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hashing {

    private String password;

    public void setHash(String sandi) {
        byte[] pass;
        StringBuilder builder = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            pass = messageDigest.digest(sandi.getBytes());
            for (byte b : pass) {
                builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        password = builder.toString();
    }

    public String getHash(){
        return password;
    }
}
