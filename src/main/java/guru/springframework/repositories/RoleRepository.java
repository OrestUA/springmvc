package guru.springframework.repositories;

import guru.springframework.domain.Product;
import guru.springframework.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Fudjitsu on 15.01.2017.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
