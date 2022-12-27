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

class AESTest {

    @Test
    void testAES()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

        String input = "Example text to test encryption";
        SecretKey key = AES.generateKey(128);
        IvParameterSpec ivParameterSpec = AES.generateIv();
        String cipher = AES.encrypt("AES/CBC/PKCS5Padding", input, key, ivParameterSpec);
        String result = AES.decrypt("AES/CBC/PKCS5Padding", cipher, key, ivParameterSpec);
        Assertions.assertEquals(input, result);
    }
}