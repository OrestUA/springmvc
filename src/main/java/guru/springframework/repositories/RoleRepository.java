package guru.springframework.repositories;

import guru.springframework.domain.Product;
import guru.springframework.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by YSkakun on 1/24/2017.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
//    Role findByRoleName(String roleName);
}
