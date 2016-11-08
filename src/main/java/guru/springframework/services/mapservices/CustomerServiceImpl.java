package guru.springframework.services.mapservices;

import guru.springframework.domain.Customer;
import guru.springframework.domain.DomainObject;
import guru.springframework.services.CustomerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Fudjitsu on 30.10.16.
 */
@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer)super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
            return (Customer)super.saveOrUpdate(customer);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }



//    protected void loadDomainObjects() {
//        domainMap = new HashMap<>();
//
//        Customer customer1 = new Customer();
//        customer1.setId(1);
//        customer1.setFirstName("Jon");
//        customer1.setLastName("Milko");
//        customer1.setEmail("user1@test.com");
//        customer1.setPhoneNumber("555-75-75");
//        customer1.setAddress_line1("California, Str, USA");
//        customer1.setAddress_line2("Washington, Str, USA");
//        customer1.setCity("Los Angeles");
//        customer1.setState("California");
//        customer1.setZipCode("00001");
//
//        domainMap.put(1, customer1);
//
//        Customer customer2 = new Customer();
//        customer2.setId(2);
//        customer2.setFirstName("Mike");
//        customer2.setLastName("Portnoy");
//        customer2.setEmail("user2@test.com");
//        customer2.setPhoneNumber("555-723-753");
//        customer2.setAddress_line1("San Francisco, Str, USA");
//        customer2.setAddress_line2("Chicago, Str, USA");
//        customer2.setCity("San Francisco");
//        customer2.setState("California");
//        customer2.setZipCode("00001");
//
//        domainMap.put(2, customer2);
//
//        Customer customer3 = new Customer();
//        customer3.setId(3);
//        customer3.setFirstName("Iren");
//        customer3.setLastName("Dido");
//        customer3.setEmail("user3@test.com");
//        customer3.setPhoneNumber("555-23-53");
//        customer3.setAddress_line1("New York, Str, USA");
//        customer3.setAddress_line2("New Jersey, Str, USA");
//        customer3.setCity("New Jersey");
//        customer3.setState("New York");
//        customer3.setZipCode("21000");
//
//        domainMap.put(3, customer3);
//    }
}
