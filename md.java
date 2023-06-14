import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class FileMessageDigest {

    public static void main(String[] args) {
        String filePath = "C:/Users/kervi/OneDrive/Documents/Networking";

        try {
            // Compute the message digest for the file
            String messageDigest = computeMessageDigest(filePath);
            if (messageDigest != null) {
                System.out.println("Message Digest: " + messageDigest);
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String computeMessageDigest(String filePath) throws IOException, NoSuchAlgorithmException {
        byte[] buffer = new byte[8192]; // Adjust the buffer size as needed

        // Create a MessageDigest instance using SHA-256 algorithm
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        try (FileInputStream fis = new FileInputStream(filePath)) {
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                // Update the digest with the data read from the file
                digest.update(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.err.println("Error opening or reading file: " + filePath);
            throw e;
        }

        // Get the computed digest in hexadecimal format
        byte[] hashedBytes = digest.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte hashedByte : hashedBytes) {
            stringBuilder.append(String.format("%02x", hashedByte));
        }
        return stringBuilder.toString();
    }
}
