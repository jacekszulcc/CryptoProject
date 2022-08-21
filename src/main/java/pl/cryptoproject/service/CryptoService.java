package pl.cryptoproject.service;

import org.springframework.stereotype.Service;
import pl.cryptoproject.config.CmcConfig;
import pl.cryptoproject.dto.CryptoDto;
import pl.cryptoproject.dto.CryptoPortfolioDto;
import pl.cryptoproject.entity.Crypto;
import pl.cryptoproject.service.model.crypto.CryptoList;
import pl.cryptoproject.service.model.crypto.Datum;

import java.io.IOException;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import pl.cryptoproject.repository.CryptoRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;
    private final CmcConfig cmcConfig;

    public CryptoService(CryptoRepository cryptoRepository, CmcConfig cmcConfig) {
        this.cryptoRepository = cryptoRepository;
        this.cmcConfig = cmcConfig;
    }

    public List<CryptoDto> getCrypto() throws IOException {

        List<CryptoDto> cryptoDtoList = new ArrayList<>();

        int startList = 1;
        int endList = 100;

        URL url = new URL("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?start=" + startList + "&limit=" + endList + "&CMC_PRO_API_KEY=" + cmcConfig.getApikey());
        InputStreamReader reader = new InputStreamReader(url.openStream());
        CryptoList crypto = new Gson().fromJson(reader, CryptoList.class);
        for (Datum datum : crypto.getData()){
            CryptoDto cryptoDto = new CryptoDto();
            cryptoDto.setId(datum.getId());
            cryptoDto.setName(datum.getName());
            cryptoDto.setSymbol(datum.getSymbol());
            cryptoDto.setCirculatingSupply(datum.getCirculatingSupply());
            cryptoDto.setPrice(datum.getQuote().getUsd().getPrice());
            cryptoDto.setVolume24h(datum.getQuote().getUsd().getVolume24h());
            cryptoDto.setPercentChange1h(datum.getQuote().getUsd().getPercentChange1h());
            cryptoDto.setPercentChange24h(datum.getQuote().getUsd().getPercentChange24h());
            cryptoDto.setPercentChange7d(datum.getQuote().getUsd().getPercentChange7d());
            cryptoDto.setMarketCap(datum.getQuote().getUsd().getMarketCap());
            cryptoDtoList.add(cryptoDto);
        }

        return cryptoDtoList;
    }


    public Double getCryptoBySymbol(String symbol) throws IOException {
        List<CryptoDto> cryptoDtoList = getCrypto();
        Double price = 0.0;
        for (CryptoDto crypto : cryptoDtoList){
            if (crypto.getSymbol().equals(symbol)) {
                price = crypto.getPrice();
            }
        }
        return price;
    }

    public void saveCryptoInPortfolio(Crypto crypto){
        cryptoRepository.save(crypto);
    }

    public List<Crypto> findAllCryptoByUserId(Long id) {
        return cryptoRepository.findCryptoByUserId(id);
    }

    public void deleteById(Long id){
        cryptoRepository.deleteById(id);
    }

    public Optional<Crypto> findCryptoById(Long id){
        return cryptoRepository.findById(id);
    }

    public void updateCrypto(Crypto crypto){
        cryptoRepository.save(crypto);
    }

    public List<CryptoPortfolioDto> createCryptoPortfolioDtoList(List<Crypto> list){
        return list.stream().map(crypto -> {
            try {
                return new CryptoPortfolioDto(crypto.getId(), crypto.getName(), crypto.getQuantity(), getCryptoBySymbol(crypto.getName()), crypto.getCmcId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public double sumPortfolio(List<CryptoPortfolioDto> list){
        Double sum = 0.0;
        return list.stream()
                .mapToDouble(i -> Double.valueOf(i.getQuantity())*i.getPrice()).sum();
    }
}
