package pl.cryptoproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.cryptoproject.dto.CryptoFavoriteDto;
import pl.cryptoproject.entity.CryptoFavorite;
import pl.cryptoproject.service.CryptoFavoriteService;
import pl.cryptoproject.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FavoriteController {

    private final CryptoFavoriteService cryptoFavoriteService;
    private final UserService userService;

    public FavoriteController(CryptoFavoriteService cryptoFavoriteService, UserService userService) {
        this.cryptoFavoriteService = cryptoFavoriteService;
        this.userService = userService;
    }

    @GetMapping("/favorite/add/{cryptoName}/{cmcId}")
    public String favoriteQuestion(@PathVariable String cryptoName, @PathVariable int cmcId, Model model){

        model.addAttribute("cryptoName", cryptoName);
        model.addAttribute("cmcId", cmcId);

        return "favorite/favorite_question";
    }

    @PostMapping("/favorite/add")
    public String favoriteAdd(@RequestParam String cryptoName, @RequestParam int cmcId) throws IOException {
        Long userId = userService.findUserId().get().getId();
        CryptoFavorite cryptoFavorite = new CryptoFavorite(cryptoName, cmcId, userId);
        cryptoFavoriteService.saveCryptoFavorite(cryptoFavorite);
        return "redirect:/favorite";
    }

    @GetMapping("/favorite")
    public String favorite(Model model){
        Long userId = userService.findUserId().get().getId();
        List<CryptoFavorite> cryptoFavoriteList = cryptoFavoriteService.findAllCryptoFavoriteByUserId(userId);
        cryptoFavoriteService.forEachCryptoFavoriteList(cryptoFavoriteList);
        List<CryptoFavoriteDto> cryptoFavoriteDtoList = cryptoFavoriteList.stream()
                .map(cryptoFavorite -> new CryptoFavoriteDto(cryptoFavorite.getId(), cryptoFavorite.getName(), cryptoFavorite.getCmcId(), cryptoFavoriteService.findFirst3CryptoFavoriteLogsByCryptoId(cryptoFavorite))).collect(Collectors.toList());
        model.addAttribute("cryptoList", cryptoFavoriteDtoList);
        return "favorite/favorite";
    }

    @GetMapping("/favorite/del/{id}")
    public String favoriteDelete(@PathVariable Long id) {
        CryptoFavorite cryptoFavorite = cryptoFavoriteService.findCryptoFavoriteById(id);
        cryptoFavoriteService.deleteByCryptoFavorite(cryptoFavorite);
        return "redirect:/favorite";
    }
}
