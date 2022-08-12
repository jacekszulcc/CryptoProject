package pl.cryptoproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.cryptoproject.dto.CryptoFavoriteDto;
import pl.cryptoproject.entity.CryptoFavorite;
import pl.cryptoproject.entity.CryptoFavoriteLog;
import pl.cryptoproject.service.CryptoFavoriteService;
import pl.cryptoproject.service.CryptoService;
import pl.cryptoproject.service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FavoriteController {

    private final CryptoFavoriteService cryptoFavoriteService;
    private final CryptoService cryptoService;

    private final UserService userService;

    public FavoriteController(CryptoFavoriteService cryptoFavoriteService, CryptoService cryptoService, UserService userService) {
        this.cryptoFavoriteService = cryptoFavoriteService;
        this.cryptoService = cryptoService;
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
        cryptoFavoriteList.stream()
                .forEach((CryptoFavorite cryptoFavorite) -> {
                    try {
                        CryptoFavoriteLog lastCryptoFavoriteLog = cryptoFavoriteService.findFirstCryptoFavoriteLogsByCryptoId(cryptoFavorite);
                        CryptoFavoriteLog lastPriceCryptoFavoriteLog = cryptoFavoriteService.findLastCryptoFavoriteLogsByCryptoId(cryptoFavorite);
                        Double priceChange = 0.0;
                        if (!(lastPriceCryptoFavoriteLog==null) && !(lastCryptoFavoriteLog==null)){
                            Double lastPrice = lastPriceCryptoFavoriteLog.getPrice();
                            Double nowPice = cryptoService.getCryptoBySymbol(cryptoFavorite.getName());
                            if (lastPrice < nowPice) {
                                priceChange = 100 / (lastPrice / (lastPrice - nowPice));
                            } else if (lastPrice > nowPice) {
                                priceChange = 0 - 100 / (lastPrice / (lastPrice - nowPice));
                            }
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                            String date1 = lastCryptoFavoriteLog.getData().format(formatter);
                            String date2 = LocalDateTime.now().format(formatter);
                            if (!date1.equals(date2)) {
                                cryptoFavoriteService.saveCryptoFavoriteLog(new CryptoFavoriteLog(LocalDateTime.now(), cryptoService.getCryptoBySymbol(cryptoFavorite.getName()), priceChange, cryptoFavorite));
                            }
                        }
                        else {
                            cryptoFavoriteService.saveCryptoFavoriteLog(new CryptoFavoriteLog(LocalDateTime.now(), cryptoService.getCryptoBySymbol(cryptoFavorite.getName()), priceChange, cryptoFavorite));
                        }


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

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
