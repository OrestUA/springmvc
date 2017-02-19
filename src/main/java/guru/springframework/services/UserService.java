package guru.springframework.services;

import guru.springframework.domain.Customer;
import guru.springframework.domain.User;

/**
 * Created by Fudjitsu on 30.10.16.
 */
public interface UserService extends CRUDService<User> {

    User findByUserName(String userName);
}
