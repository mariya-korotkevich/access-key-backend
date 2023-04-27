package com.example.accesskeybackend.ipv6.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class Ipv6ServiceTest {

    private static Ipv6Service service;

    @BeforeAll
    static void init(){
        service = new Ipv6ServiceImpl();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "https://ya.ru/",
            "http://ya.ru/",
            "https://yandex.ru/pogoda/",
            "http://yandex.ru/pogoda/",
            "ya.ru",
            "https://www.google.com/",
            "http://www.google.com/",
            "https://www.google.ru/imghp?hl=ru&ogbl",
            "http://www.google.ru/imghp?hl=ru&ogbl",
            "google.com"
    })
    void checkIpv6Support_ShouldReturnTrueWhenSiteSupportIpv6(String input) throws UnknownHostException {
        boolean result = service.checkIpv6Support(input);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "https://habr.com/",
            "http://habr.com/",
            "https://habr.com/ru/all/",
            "http://habr.com/ru/all/",
            "habr.com"
    })
    void checkIpv6Support_ShouldReturnFalseWhenSiteNotSupportIpv6(String input) throws UnknownHostException {
        boolean result = service.checkIpv6Support(input);
        assertFalse(result);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "someString",
            "..."
    })
    void checkIpv6Support_ShouldThrowExceptionWhenSiteNotExists(String input) {
        assertThrows(UnknownHostException.class,
                () -> service.checkIpv6Support(input));
    }
}