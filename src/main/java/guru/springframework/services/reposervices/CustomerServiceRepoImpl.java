package guru.springframework.services.reposervices;

import guru.springframework.commands.CustomerForm;
import guru.springframework.converters.CustomerFormToCustomer;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YSkakun on 1/24/2017.
 */
@Service
@Profile("springdatajpa")
public class CustomerServiceRepoImpl implements CustomerService {

    @Autowired
    private CustomerFormToCustomer customerFormToCustomer;

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository productRepository) {
        this.customerRepository = productRepository;
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
        return customerRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        customerRepository.delete(id);
    }

    @Override
    public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
        Customer newCustomer = customerFormToCustomer.convert(customerForm);
        if(newCustomer.getUser().getId()!=null){
            Customer existingCustomer = getById(newCustomer.getId());

            newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }
        return saveOrUpdate(newCustomer);
    }
}
