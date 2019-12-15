package io.github.hugoltsp.user.data.repository;

import io.github.hugoltsp.user.data.orm.UserToken;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserTokenRepository extends CrudRepository<UserToken, String> {

    List<UserToken> findByUserId(Long userId);

}
