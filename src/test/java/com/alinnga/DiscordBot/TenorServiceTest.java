package com.alinnga.DiscordBot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TenorServiceTest {

    private final TenorService tenorService;

    @Autowired
    TenorServiceTest(TenorService tenorService) {
        this.tenorService = tenorService;
    }

    @Test
    void getRandomGif() {
        var randomGif = tenorService.getRandomGif("hi");
        Assertions.assertThat(randomGif).matches("https://tenor.com/.*\\.gif");
    }
}