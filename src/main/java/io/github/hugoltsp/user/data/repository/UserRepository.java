package io.github.hugoltsp.user.data.repository;

import io.github.hugoltsp.user.data.orm.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
