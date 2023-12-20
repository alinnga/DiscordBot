package com.alinnga.DiscordBot;

import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@Component
public abstract class MessageListener {

    private final TenorService tenorService;
    String[] gifs = new String[]{"https://tenor.com/view/%D0%B4%D0%B0%D1%83%D0%BD%D1%8B-gif-26752747",
            "https://tenor.com/view/%D0%BC%D0%B0%D0%BB%D0%BE%D1%80%D0%BE%D1%81%D1%8B-%D0%BE%D0%B1%D1%89%D0%B8%D0%B9-%D1%81%D0%B1%D0%BE%D1%80-gif-6548255871513673096",
            "https://tenor.com/view/obwiy-sbor-borrow-squad-shelby-gang-gif-24749822",
            "https://tenor.com/view/bleach-gif-7975488050930660293",
            "https://tenor.com/view/%D0%B5%D0%B1%D0%BB%D0%B0%D0%BD%D1%8B-%D0%BE%D0%B1%D1%89%D0%B8%D0%B9-%D1%81%D0%B1%D0%BE%D1%80-gif-4477570067328898567",
            "https://tenor.com/view/%D1%81%D0%BC%D1%83%D0%B7%D0%B8-%D0%BA%D0%BE%D0%B7%D1%8B%D1%80%D1%8C%D0%BA%D0%B8-gif-23863955",
            "https://tenor.com/view/%D0%B4%D0%B0%D1%83%D0%BD%D1%8B-%D0%BE%D0%B1%D1%89%D0%B8%D0%B9-%D1%81%D0%B1%D0%BE%D1%80-%D0%BE%D0%B1%D1%89%D0%B8%D0%B9-%D1%81%D0%B1%D0%BE%D1%80-%D1%81%D1%85%D0%BE%D0%B4%D0%BA%D0%B0-monsters-inc-%D0%BA%D0%BE%D1%80%D0%BF%D0%BE%D1%80%D0%B0%D1%86%D0%B8%D1%8F-%D0%BC%D0%BE%D0%BD%D1%81%D1%82%D1%80%D0%BE%D0%B2-gif-4684587280093561682",
            "https://tenor.com/view/cs-go-lets-goo-ignuxaz-gif-25390531"};

    protected MessageListener(TenorService tenorService) {
        this.tenorService = tenorService;
    }

    public Mono<Void> processCommand(Message eventMessage) {
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .flatMap(message -> {
                    String result = "";
                    String content = message.getContent();
                    if(content.equalsIgnoreCase("@спецназ")){
                        int gifId = ThreadLocalRandom.current().nextInt(gifs.length);
                        result = gifs[gifId];
                    }
                    else if(content.matches("random\s*.+")){
                        result = tenorService.getRandomGif(content.replaceFirst("random", ""));
                    }
                    String finalResult = result;
                    if(!finalResult.isBlank()){
                        return message.getChannel().flatMap(channel -> channel.createMessage(finalResult));
                    }
                    return Mono.empty();
                })
                .then();
    }
}
