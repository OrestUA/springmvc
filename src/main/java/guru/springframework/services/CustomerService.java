package guru.springframework.services;

import guru.springframework.commands.CustomerForm;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.services.CRUDService;

import java.util.List;

/**
 * Created by Fudjitsu on 30.10.16.
 */
public interface CustomerService extends CRUDService<Customer> {

    CustomerForm saveOrUpdate(CustomerForm customerForm);

}
