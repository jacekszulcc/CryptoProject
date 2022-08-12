package pl.cryptoproject.service;

import org.springframework.stereotype.Service;
import pl.cryptoproject.dto.CryptoDto;
import pl.cryptoproject.entity.Crypto;
import pl.cryptoproject.model.crypto.CryptoList;
import pl.cryptoproject.model.crypto.Datum;
import pl.cryptoproject.entity.User;

import java.io.IOException;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import pl.cryptoproject.repository.CryptoRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;

    public CryptoService(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }


    public List<CryptoDto> getCrypto() throws IOException {

        List<CryptoDto> cryptoDtoList = new ArrayList<>();

        URL url = new URL("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?start=1&limit=100&CMC_PRO_API_KEY=8d4bdca0-468e-4979-9e71-a9caa8ab0cdd");

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
}
