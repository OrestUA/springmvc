package guru.springframework.services.jpaservices;

import guru.springframework.domain.Order;
import guru.springframework.domain.Product;
import guru.springframework.services.OrderService;
import guru.springframework.services.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by YSkakun on 11/10/2016.
 */
@Service
@Profile("jpadao")
public class OrderServiceJpaDaoImpl extends AbstractJpaDaoService implements OrderService {

    @Override
    public List<Order> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Order",Order.class).getResultList();
    }

    @Override
    public Order getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Order.class, id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Order savedOrder = em.merge(domainObject);//creates new if not exists or updates existing one Order
        em.getTransaction().commit();
        return savedOrder;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Order.class,id));
        em.getTransaction().commit();
    }
}