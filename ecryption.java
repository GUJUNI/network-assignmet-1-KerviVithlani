import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

class EncryptionDecryptionExample {

    private static final String ALGORITHM = "AES";
    private static final String KEY = "mysecretkey12345"; // 128-bit key

    public static void main(String[] args) {
        String plaintext = "Hello, World!";

        try {
            // Encrypt the plaintext
            String encryptedText = encrypt(plaintext);
            System.out.println("Encrypted Text: " + encryptedText);

            // Decrypt the encrypted text
            String decryptedText = decrypt(encryptedText);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String plaintext) throws Exception {
        // Create a Cipher object with AES algorithm
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);

        // Initialize the Cipher object with the encryption mode and key
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        // Encrypt the plaintext
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        // Encode the encrypted bytes using Base64 encoding
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText) throws Exception {
        // Create a Cipher object with AES algorithm
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);

        // Initialize the Cipher object with the decryption mode and key
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        // Decode the Base64 encoded encrypted text
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

        // Decrypt the encrypted bytes
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Return the decrypted plaintext
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}

// Output:
// Encrypted Text: kwhVHyUe08laf2MC+K3rcw==
// Decrypted Text: Hello, World!
