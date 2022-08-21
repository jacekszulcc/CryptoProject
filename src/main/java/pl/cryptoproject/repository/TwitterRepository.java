package pl.cryptoproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.cryptoproject.entity.Hashtag;

import java.util.List;

public interface TwitterRepository extends CrudRepository<Hashtag, Long> {

    List<Hashtag> findHashtagByUserIdOrderByIdDesc(Long id);

    Hashtag findFirstByUserIdOrderByIdDesc(Long userId);

}
