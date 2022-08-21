package pl.cryptoproject.service;


import org.springframework.stereotype.Service;
import pl.cryptoproject.config.TwitterConfig;
import pl.cryptoproject.dto.TwitterDto;
import pl.cryptoproject.entity.Hashtag;
import pl.cryptoproject.repository.TwitterRepository;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TwitterService {

    private final TwitterRepository twitterRepository;
    private final TwitterConfig twitterConfig;

    public TwitterService(TwitterRepository twitterRepository, TwitterConfig twitterConfig) {
        this.twitterRepository = twitterRepository;
        this.twitterConfig = twitterConfig;
    }

    public Twitter getTwitterInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(twitterConfig.getConsumerKey())
                .setOAuthConsumerSecret(twitterConfig.getConsumerSecret())
                .setOAuthAccessToken(twitterConfig.getAccessToken())
                .setOAuthAccessTokenSecret(twitterConfig.getAccessTokenSecret());

        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

    public List<TwitterDto> getTwitterSearch(String querySearch) throws TwitterException{
            Twitter twitter = getTwitterInstance();
            Query query = new Query(querySearch);
            QueryResult result = twitter.search(query);
            List<Status> listTweets = result.getTweets();

            List<TwitterDto> twitterDtoList = listTweets.stream().map(status ->
                    new TwitterDto(status.getId(), status.getText(), status.getUser().getProfileImageURL(),
                            status.getUser().getScreenName(), status.getUser().getName(), status.getRetweetCount(), status.getFavoriteCount(), convertToLocalDateViaInstant(status.getCreatedAt()))).collect(Collectors.toList());
            return twitterDtoList;
    }

    public LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public void saveHashtag(Hashtag hashtag){
        twitterRepository.save(hashtag);
    }

    public List<Hashtag> findAllHashtagsByUserId(Long userId){
        return twitterRepository.findHashtagByUserIdOrderByIdDesc(userId);
    }

    public Hashtag findFirstHashtagByUserId(Long userId){
        return twitterRepository.findFirstByUserIdOrderByIdDesc(userId);
    }

    public void deleteById(Long id){
        twitterRepository.deleteById(id);
    }

}
