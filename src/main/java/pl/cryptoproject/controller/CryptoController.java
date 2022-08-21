package pl.cryptoproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.cryptoproject.dto.CryptoDto;
import pl.cryptoproject.entity.Crypto;
import pl.cryptoproject.entity.CryptoFavorite;
import pl.cryptoproject.service.CryptoFavoriteService;
import pl.cryptoproject.service.CryptoService;
import pl.cryptoproject.service.UserService;

import java.io.IOException;
import java.util.List;

@Controller
public class CryptoController {

    private CryptoService cryptoService;
    private CryptoFavoriteService cryptoFavoriteService;

    private UserService userService;

    public CryptoController(CryptoService cryptoService, CryptoFavoriteService cryptoFavoriteService, UserService userService) {
        this.cryptoService = cryptoService;
        this.cryptoFavoriteService = cryptoFavoriteService;
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping("/crypto")
    public List<CryptoDto> getCrypto() throws IOException {
        List<CryptoDto> cryptoDtoList = cryptoService.getCrypto();
        return cryptoDtoList.stream().toList();
    }

    @ResponseBody
    @GetMapping("/crypto/{cryptoName}")
    public Double getCryptoByName(@PathVariable String cryptoName) throws IOException {
        return cryptoService.getCryptoBySymbol(cryptoName);
    }


    @GetMapping({"/", "/top100"})
    public String home(Model model) throws IOException {
        Long userId = userService.findUserId().get().getId();
        List<Crypto> allCryptoPortfolioList = cryptoService.findAllCryptoByUserId(userId);
        List<CryptoFavorite> allCryptoFavoriteList = cryptoFavoriteService.findAllCryptoFavoriteByUserId(userId);
        List<CryptoDto> cryptoDtoList = cryptoService.getCrypto();
        model.addAttribute("cryptoPortfolioList", allCryptoPortfolioList);
        model.addAttribute("cryptoFavoriteList", allCryptoFavoriteList);
        model.addAttribute("cryptoList", cryptoDtoList);
        return "index";
    }

}
