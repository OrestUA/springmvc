package guru.springframework.services.jpaservices;

import guru.springframework.commands.CustomerForm;
import guru.springframework.converters.CustomerFormToCustomer;
import guru.springframework.converters.CustomerToCustomerForm;
import guru.springframework.domain.Customer;
import guru.springframework.domain.User;
import guru.springframework.services.CustomerService;
import guru.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by YSkakun on 11/2/2016.
 */
@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl extends AbstractJpaDaoService implements CustomerService {

    private EncryptionService encryptionService;
    private CustomerFormToCustomer customerFormToCustomer;
    private CustomerToCustomerForm customerToCustomerForm;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Autowired
    public void setCustomerToCustomerForm(CustomerToCustomerForm customerToCustomerForm) {
        this.customerToCustomerForm = customerToCustomerForm;
    }

    @Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
    }

    @Override
    public List<Customer> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Customer.class, id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        if (domainObject.getUser() != null && domainObject.getUser().getPassword() != null) {
            domainObject.getUser().setEncryptedPassword(
                    encryptionService.encryptString(domainObject.getUser().getPassword()));
        }

        Customer savedCustomer = em.merge(domainObject);//creates new if not exists or updates existing one Customer
        em.getTransaction().commit();
        return savedCustomer;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Customer.class, id));
        em.getTransaction().commit();
    }

    @Override
    public CustomerForm saveOrUpdate(CustomerForm customerForm) {
        Customer newCustomer = customerFormToCustomer.convert(customerForm);
        if(newCustomer.getUser().getId()!=null){
            Customer existingCustomer = getById(newCustomer.getId());

            newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }
        return customerToCustomerForm.convert(saveOrUpdate(newCustomer));
    }
}