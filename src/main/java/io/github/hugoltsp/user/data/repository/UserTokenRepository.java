package io.github.hugoltsp.user.data.repository;

import io.github.hugoltsp.user.data.orm.UserToken;
import org.springframework.data.repository.CrudRepository;

public interface UserTokenRepository extends CrudRepository<UserToken, String> {

}
