package guru.springframework.repositories;

import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by YSkakun on 1/24/2017.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
