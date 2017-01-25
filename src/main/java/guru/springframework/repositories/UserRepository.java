package guru.springframework.repositories;

import guru.springframework.domain.Product;
import guru.springframework.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Fudjitsu on 15.01.2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

}
