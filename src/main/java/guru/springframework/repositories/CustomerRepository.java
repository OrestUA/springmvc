package guru.springframework.repositories;

import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Fudjitsu on 15.01.2017.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
