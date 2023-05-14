package com.zxcv5595.domain.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Aes256UtilTest {

    @Test
    void encrypt() {
        String encrypt = Aes256Util.encrypt("Hello world");
        assertEquals("Hello world", Aes256Util.decrypt(encrypt));
    }

}