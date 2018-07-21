package jinbro.security.domain;

import jinbro.security.dto.SocialProviders;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByUserId(String userId);

    Optional<Account> findBySocialId(Long socialId);

    Optional<Account> findBySocialIdAndProvider(Long socialId, SocialProviders provider);
}
