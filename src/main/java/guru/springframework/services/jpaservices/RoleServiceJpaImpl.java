package guru.springframework.services.jpaservices;

import guru.springframework.domain.security.Role;
import guru.springframework.services.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by YSkakun on 11/10/2016.
 */
@Service
@Profile("jpadao")
public class RoleServiceJpaImpl extends AbstractJpaDaoService implements RoleService {

    @Override
    public List<Role> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Role",Role.class).getResultList();
    }

    @Override
    public Role getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Role.class, id);
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Role savedRole = em.merge(domainObject);
        em.getTransaction().commit();
        return savedRole;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Role.class,id));
        em.getTransaction().commit();
    }
}