package com.alinnga.DiscordBot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GifObject {
//    private float created;
//    private boolean hasaudio;
//    private String id;
//    private String[] tags;
//    private String title;
    @JsonProperty
    private String itemurl;
    @JsonProperty
    private String url;
}
