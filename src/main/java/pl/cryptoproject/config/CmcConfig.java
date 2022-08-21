package pl.cryptoproject.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "cmc")
public class CmcConfig {
    private String apikey;
}
