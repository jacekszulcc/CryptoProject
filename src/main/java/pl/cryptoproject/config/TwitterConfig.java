package pl.cryptoproject.config;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "twitter")
public class TwitterConfig {
    String consumerKey;
    String consumerSecret;
    String accessToken;
    String accessTokenSecret;
}
