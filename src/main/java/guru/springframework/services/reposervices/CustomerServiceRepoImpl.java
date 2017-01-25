package guru.springframework.services.reposervices;

import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fudjitsu on 16.01.2017.
 */
@Service
@Profile("springdatajpa")
public class CustomerServiceRepoImpl  implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<?> listAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customer-> customers.add(customer));
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
}