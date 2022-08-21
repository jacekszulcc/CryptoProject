package pl.cryptoproject.service;

import org.springframework.stereotype.Service;
import pl.cryptoproject.entity.CryptoFavorite;
import pl.cryptoproject.entity.CryptoFavoriteLog;
import pl.cryptoproject.repository.CryptoFavoriteLogRepository;
import pl.cryptoproject.repository.CryptoFavoriteRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class CryptoFavoriteService {

    private final CryptoFavoriteRepository cryptoFavoriteRepository;
    private final CryptoFavoriteLogRepository cryptoFavoriteLogRepository;
    private final CryptoService cryptoService;

    public CryptoFavoriteService(CryptoFavoriteRepository cryptoFavoriteRepository, CryptoFavoriteLogRepository cryptoFavoriteLogRepository, CryptoService cryptoService) {
        this.cryptoFavoriteRepository = cryptoFavoriteRepository;
        this.cryptoFavoriteLogRepository = cryptoFavoriteLogRepository;
        this.cryptoService = cryptoService;
    }

    public void saveCryptoFavorite(CryptoFavorite cryptoFavorite){
        cryptoFavoriteRepository.save(cryptoFavorite);
    }

    public void saveCryptoFavoriteLog(CryptoFavoriteLog cryptoFavoriteLog){
        cryptoFavoriteLogRepository.save(cryptoFavoriteLog);
    }

    public List<CryptoFavorite> findAllCryptoFavoriteByUserId(Long id){
        return cryptoFavoriteRepository.findAllCryptoFavoriteByUserId(id);
    }

    public List<CryptoFavoriteLog> findFirst3CryptoFavoriteLogsByCryptoId(CryptoFavorite cryptoFavorite){
        return cryptoFavoriteLogRepository.findFirst3CryptoFavoriteLogByCryptoFavoriteOrderByIdDesc(cryptoFavorite);
    }

    public void deleteByCryptoFavorite(CryptoFavorite cryptoFavorite){
          cryptoFavoriteRepository.delete(cryptoFavorite);
    }

    public CryptoFavorite findCryptoFavoriteById(Long id){
        return cryptoFavoriteRepository.findById(id).get();
    }

    public CryptoFavoriteLog findFirstCryptoFavoriteLogsByCryptoId(CryptoFavorite cryptoFavorite){
        return  cryptoFavoriteLogRepository.findFirstCryptoFavoriteLogByCryptoFavoriteOrderByIdDesc(cryptoFavorite);
    }

    public CryptoFavoriteLog findLastCryptoFavoriteLogsByCryptoId(CryptoFavorite cryptoFavorite){
        return  cryptoFavoriteLogRepository.findFirstCryptoFavoriteLogByCryptoFavoriteOrderByIdAsc(cryptoFavorite);
    }

    public void forEachCryptoFavoriteList(List<CryptoFavorite> list) {
        list.stream()
                .forEach((CryptoFavorite cryptoFavorite) -> {
                    try {
                        CryptoFavoriteLog lastCryptoFavoriteLog = findFirstCryptoFavoriteLogsByCryptoId(cryptoFavorite);
                        CryptoFavoriteLog lastPriceCryptoFavoriteLog = findLastCryptoFavoriteLogsByCryptoId(cryptoFavorite);
                        Double priceChange = 0.0;
                        if (!(lastPriceCryptoFavoriteLog == null) && !(lastCryptoFavoriteLog == null)) {
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
                                saveCryptoFavoriteLog(new CryptoFavoriteLog(LocalDateTime.now(), cryptoService.getCryptoBySymbol(cryptoFavorite.getName()), priceChange, cryptoFavorite));
                            }
                        } else {
                            saveCryptoFavoriteLog(new CryptoFavoriteLog(LocalDateTime.now(), cryptoService.getCryptoBySymbol(cryptoFavorite.getName()), priceChange, cryptoFavorite));
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}
