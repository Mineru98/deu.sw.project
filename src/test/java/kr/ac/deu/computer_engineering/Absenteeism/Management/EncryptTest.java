package kr.ac.deu.computer_engineering.Absenteeism.Management;

import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.Encrypt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EncryptTest {
    @Test
    public void passwordEncoding() {
        String passwordInput = "user1234!";
        String expectedOutput = "daa7f4d0512d94d07b875e8d9842b818079cd71a13c82b5b36a287bc7230a3e249807a7ec75d06a2bcdb175d926d5dcc5cefa67e5534285ceb10c307c84931e1";
        String encodedOutput = Encrypt.encode(passwordInput);
        Assertions.assertEquals(expectedOutput, encodedOutput);
    }
}
