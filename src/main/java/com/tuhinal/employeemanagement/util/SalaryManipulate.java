package com.tuhinal.employeemanagement.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Base64;

public class SalaryManipulate {

    public static String encrypt(String employeeId, LocalDateTime date, String designation,
                                 String data, Double salary) throws Exception {
        // Create encryption key from employee data
        byte[] keyData = (employeeId + designation + data).getBytes(StandardCharsets.UTF_8);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = digest.digest(keyData);

        // Get month number for salt (1-12)
        int monthValue = date.getMonthValue();

        // Apply salt by XORing key with month value
        for (int i = 0; i < keyBytes.length; i++) {
            keyBytes[i] = (byte) (keyBytes[i] ^ monthValue);
        }

        // Create AES secret key
        SecretKey secretKey = new SecretKeySpec(keyBytes, 0, 16, "AES");

        // Initialize cipher
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Get IV
        byte[] iv = cipher.getIV();

        // Convert salary to bytes
        byte[] salaryBytes = ByteBuffer.allocate(Double.BYTES).putDouble(salary).array();

        // Encrypt
        byte[] encryptedSalary = cipher.doFinal(salaryBytes);

        // Combine IV and encrypted data
        ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + encryptedSalary.length);
        byteBuffer.put(iv);
        byteBuffer.put(encryptedSalary);

        // Return as Base64 encoded string
        return Base64.getEncoder().encodeToString(byteBuffer.array());
    }
}
