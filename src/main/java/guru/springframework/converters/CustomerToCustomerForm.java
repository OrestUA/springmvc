package guru.springframework.converters;

import guru.springframework.commands.CustomerForm;
import guru.springframework.domain.Address;
import guru.springframework.domain.Customer;
import guru.springframework.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * Created by YSkakun on 02/02/2017.
 */
@Component
public class CustomerToCustomerForm implements Converter<Customer, CustomerForm> {
    @Override
    public CustomerForm  convert(Customer customer) {
        CustomerForm customerForm = new CustomerForm();

        customerForm.setCustomerId(customer.getId());
        customerForm.setCustomerVersion(customer.getVersion());
        customerForm.setEmail(customer.getEmail());
        customerForm.setFirstName(customer.getFirstName());
        customerForm.setLastName(customer.getLastName());
        customerForm.setPhoneNumber(customer.getPhoneNumber());
        customerForm.setUserId(customer.getUser().getId());
        customerForm.setUserName(customer.getUser().getUsername());
        customerForm.setUserVersion(customer.getUser().getVersion());
        customerForm.setPasswordText(customer.getUser().getPassword());
        customerForm.setPasswordTextConf(customer.getUser().getPassword());

        return customerForm;
    }
}