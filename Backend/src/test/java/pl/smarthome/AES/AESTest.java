package pl.smarthome.AES;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

class AESTest {

    @Test
    void testAES()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, InvalidKeySpecException {

        String input = "Example text to test encryption";
        SecretKey key = AES.generateKey(128);
        SecretKey key2 = AES.getKeyFromPassword("A","A");

        System.out.println(Arrays.toString(key.getEncoded()));
        IvParameterSpec ivParameterSpec = AES.generateIv();
        String cipher = AES.encrypt("AES/CBC/PKCS5Padding", input, key, ivParameterSpec);
        String result = AES.decrypt("AES/CBC/PKCS5Padding", cipher, key, ivParameterSpec);
        Assertions.assertEquals(input, result);
    }

    @Test
    void givenPassword_whenEncrypt_thenSuccess()
            throws InvalidKeySpecException, NoSuchAlgorithmException,
            IllegalBlockSizeException, InvalidKeyException, BadPaddingException,
            InvalidAlgorithmParameterException, NoSuchPaddingException {

        String plainText = "www.baeldung.com";
        IvParameterSpec ivParameterSpec = AES.generateIv();
        SecretKey key = AES.getKeyFromPassword();
        String cipherText = AES.encryptPasswordBased(plainText, key, ivParameterSpec);
        String decryptedCipherText = AES.decryptPasswordBased(cipherText, key, ivParameterSpec);
        Assertions.assertEquals(plainText, decryptedCipherText);

    }
}