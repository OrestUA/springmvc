package guru.springframework.services.jpaservices;

import guru.springframework.domain.User;
import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import guru.springframework.services.UserService;
import guru.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by YSkakun on 11/3/2016.
 */
@Service
@Profile("jpadao")
public class UserServiceJpaDaoImpl extends AbstractJpaDaoService implements UserService {

    @Autowired
    private EncryptionService encryptionService;

    @Override
    public List<User> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        if (domainObject.getPassword() != null) {
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }
        User savedUser = em.merge(domainObject);//creates new if not exists or updates existing one User
        em.getTransaction().commit();
        return savedUser;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
    }

    @Override
    public User findByUserName(String userName) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from User where username = :userName", User.class).getSingleResult();
    }
}