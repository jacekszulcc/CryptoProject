package pl.cryptoproject.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TwitterDto {
    private Long id;
    private String text;
    private String imageUrl;

    private String screenName;
    private String name;

    private int retweetCount;

    private int favoriteCount;

    private LocalDateTime dateTime;

    public TwitterDto(Long id, String text, String imageUrl, String screenName, String name, int retweetCount, int favoriteCount, LocalDateTime dateTime) {
        this.id = id;
        this.text = text;
        this.imageUrl = imageUrl;
        this.screenName = screenName;
        this.name = name;
        this.retweetCount = retweetCount;
        this.favoriteCount = favoriteCount;
        this.dateTime = dateTime;
    }
}
