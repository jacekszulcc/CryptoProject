package pl.cryptoproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.cryptoproject.dto.TwitterDto;
import pl.cryptoproject.entity.Hashtag;
import pl.cryptoproject.service.TwitterService;
import pl.cryptoproject.service.UserService;
import twitter4j.TwitterException;
import java.util.List;


@Controller
public class TwitterController {

    private final TwitterService twitterService;

    private final UserService userService;

    public TwitterController(TwitterService twitterService, UserService userService) {
        this.twitterService = twitterService;
        this.userService = userService;
    }

    @GetMapping("/twitter")
    public String indexTwitter(Model model) throws TwitterException {
        Long userId = userService.findUserId().get().getId();
        List<Hashtag> hashtagList = twitterService.findAllHashtagsByUserId(userId);
        Hashtag firsHashtag = twitterService.findFirstHashtagByUserId(userId);
        List<TwitterDto> twitterDtoList = twitterService.getTwitterSearch(firsHashtag.getName());
        model.addAttribute("hashtagList", hashtagList);
        model.addAttribute("twitterList", twitterDtoList);
        return "/twitter/hashtags";
    }

    @GetMapping("/twitter/{query}")
    public String getTwitterQuery(@PathVariable String query, Model model) throws TwitterException {
        Long userId = userService.findUserId().get().getId();
        List<Hashtag> hashtagList = twitterService.findAllHashtagsByUserId(userId);
        List<TwitterDto> twitterDtoList = twitterService.getTwitterSearch(query);
        model.addAttribute("hashtagList", hashtagList);
        model.addAttribute("twitterList", twitterDtoList);
        return "twitter/hashtags";
    }

    @GetMapping("/twitter/add")
    public String getAddHashtag(){
        return "twitter/twitter_question";
    }

    @PostMapping("/twitter/add")
    public String postAddHashtag(@RequestParam String hashtag){
        Long userId = userService.findUserId().get().getId();
        Hashtag newHashtag = new Hashtag(hashtag, userId);
        twitterService.saveHashtag(newHashtag);
        return "redirect:/twitter";
    }

    @GetMapping("/twitter/del/{id}")
    public String portfolioDelete(@PathVariable Long id) {
        twitterService.deleteById(id);
        return "redirect:/twitter";
    }
}
