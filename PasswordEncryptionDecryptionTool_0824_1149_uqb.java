// 代码生成时间: 2025-08-24 11:49:09
import org.springframework.stereotype.Component;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Password Encryption and Decryption Tool using Spring Cloud framework.
 * This tool uses symmetric encryption (AES) with a key generated at runtime.
 * The key is stored in a secure way and should be managed carefully in production environments.
 */
@Component
public class PasswordEncryptionDecryptionTool {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String CHARSET = "UTF-8";
    private static SecretKey secretKey;

    /**
     * Initialize the encryption and decryption tool by generating a secret key.
     */
    public void init() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256, SecureRandom.getInstanceStrong());
        secretKey = keyGenerator.generateKey();
    }

    /**
     * Encrypts a given plain text password.
     *
     * @param plainText the plain text password to encrypt
     * @return the encrypted password in Base64 encoded string
     * @throws Exception if encryption fails
     */
    public String encrypt(String plainText) throws Exception {
        if (secretKey == null) {
            throw new IllegalStateException("Encryption key is not initialized.");
        }

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(CHARSET));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts a given encrypted password.
     *
     * @param encryptedText the encrypted password in Base64 encoded string
     * @return the plain text password
     * @throws Exception if decryption fails
     */
    public String decrypt(String encryptedText) throws Exception {
        if (secretKey == null) {
            throw new IllegalStateException("Encryption key is not initialized.");
        }

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] originalBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(originalBytes, CHARSET);
    }
}
