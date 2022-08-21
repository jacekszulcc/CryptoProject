package pl.cryptoproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.cryptoproject.dto.CryptoPortfolioDto;
import pl.cryptoproject.entity.Crypto;
import pl.cryptoproject.service.CryptoService;
import pl.cryptoproject.service.UserService;

import java.util.List;


@Controller
public class PortfolioController {

    private final UserService userService;
    private final CryptoService cryptoService;

    public PortfolioController(UserService userService, CryptoService cryptoService) {
        this.userService = userService;
        this.cryptoService = cryptoService;
    }

    @GetMapping("/portfolio/add/{cryptoName}/{cmcId}")
    public String portfolioQuestion(@PathVariable String cryptoName,@PathVariable int cmcId, Model model){

        model.addAttribute("cmcId", cmcId);
        model.addAttribute("cryptoName", cryptoName);

        return "portfolio/portfolio_question";
    }

    @PostMapping("/portfolio/add")
    public String portfolioAdd(@RequestParam Double quantity, @RequestParam String cryptoName, @RequestParam int cmcId){
        Long id = userService.findUserId().get().getId();
        Crypto crypto = new Crypto(cryptoName, quantity, id, cmcId);
        cryptoService.saveCryptoInPortfolio(crypto);
        return "redirect:/portfolio";
    }

    @GetMapping("/portfolio")
    public String portfolioAll(Model model) {
        Long id = userService.findUserId().get().getId();
        List<Crypto> cryptoList = cryptoService.findAllCryptoByUserId(id);
        List<CryptoPortfolioDto> cryptoPortfolioDtoList = cryptoService.createCryptoPortfolioDtoList(cryptoList);
        Double sum = cryptoService.sumPortfolio(cryptoPortfolioDtoList);
        model.addAttribute("sum", sum);
        model.addAttribute("cryptoList", cryptoPortfolioDtoList);
        return "portfolio/portfolio";
    }

    @GetMapping("/portfolio/del/{id}")
    public String portfolioDelete(@PathVariable Long id) {
        cryptoService.deleteById(id);
        return "redirect:/portfolio";
    }

    @GetMapping("/portfolio/edit/{id}")
    public String portfolioGetEdit(@PathVariable Long id, Model model) {
        Crypto crypto = cryptoService.findCryptoById(id).orElseThrow(RuntimeException::new);
        model.addAttribute("crypto", crypto);
        return "portfolio/portfolio_edit";
    }

    @PostMapping("/portfolio/edit")
    public String portfolioPostEdit(@ModelAttribute Crypto crypto) {
        cryptoService.updateCrypto(crypto);
        return "redirect:/portfolio";
    }
}
