package guru.springframework.repositories;

import guru.springframework.domain.Product;
import guru.springframework.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by YSkakun on 1/24/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
