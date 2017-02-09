package guru.springframework.services.reposervices;

import guru.springframework.commands.CustomerForm;
import guru.springframework.converters.CustomerFormToCustomer;
import guru.springframework.converters.CustomerToCustomerForm;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.UserRepository;
import guru.springframework.services.CustomerService;
import guru.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YSkakun on 1/24/2017.
 */
@Service
@Profile("springdatajpa")
public class CustomerServiceRepoImpl implements CustomerService {

    private EncryptionService encryptionService;
    private CustomerToCustomerForm customerToCustomerForm;
    private CustomerFormToCustomer customerFormToCustomer;
    private CustomerRepository customerRepository;
    private UserRepository userRepository;

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

    @Autowired
    public void setCustomerRepository(CustomerRepository productRepository) {
        this.customerRepository = productRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<?> listAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer getById(Integer id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        if (domainObject.getUser() != null && domainObject.getUser().getPassword() != null) {
            domainObject.getUser().setEncryptedPassword(
                    encryptionService.encryptString(domainObject.getUser().getPassword()));
        }
        return customerRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Customer customer = customerRepository.findOne(id);
        userRepository.delete(customer.getUser());
        customerRepository.delete(customer);
    }

    @Override
    public CustomerForm saveOrUpdate(CustomerForm customerForm) {
        Customer newCustomer = customerFormToCustomer.convert(customerForm);
        if (newCustomer.getUser().getId() != null) {
            Customer existingCustomer = getById(newCustomer.getId());

            newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }
        return customerToCustomerForm.convert(saveOrUpdate(newCustomer));
    }
}