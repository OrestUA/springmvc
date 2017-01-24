package guru.springframework.repositories;

import guru.springframework.domain.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by YSkakun on 1/24/2017.
 */
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
