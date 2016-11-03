package guru.springframework.services;

import guru.springframework.domain.Customer;
import guru.springframework.services.customer.CustomerService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by YSkakun on 11/2/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("jpadao")
public class CustomerServiceJpaDaoImplTest {

    @Autowired
    private CustomerService customerService;

    private static Customer expectedCustomer1;

    private static Customer newCustomer;

    @BeforeClass
    public static void setup() {
        expectedCustomer1 =  createCustomer(1,"Jon","Milko","user1@test.com","555-75-75","California, Str, USA",
                "Washington, Str, USA","Los Angeles","California","00001");

         newCustomer = createCustomer(33,"Dan","Newman","new@test.com","555-55-55","Down, Str, USA",
                 "Long, Str, USA","New York","New York","21000");
    }

    private static Customer createCustomer(Integer id, String firstName, String lastName, String email,
                                           String phoneNumber, String address1, String address2, String city,
                                           String state, String zip) {
       Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress1(address1);
        customer.setAddress2(address2);
        customer.setCity(city);
        customer.setState(state);
        customer.setZipCode(zip);
        return customer;
    }

    @Test
    public void testListMethod() throws Exception {
        List<Customer> Customers = (List<Customer>) customerService.listAll();

        assertEquals(3, Customers.size());

        Customer actualCustomer = Customers.get(0);

        assertEquals(expectedCustomer1.getId(), actualCustomer.getId());
        assertCustomer(expectedCustomer1,actualCustomer);
    }

    @Test
    public void testGetByIdMethod() throws Exception {
        Integer id = 1;
        Customer actualCustomer = customerService.getById(id);

        assertEquals(expectedCustomer1.getId(), actualCustomer.getId());

        assertCustomer(expectedCustomer1,actualCustomer);
    }

    @Test
    public void testDeleteMethod() throws Exception {
        Integer id = 2;
        Customer customerToDelete = customerService.getById(id);
        customerService.delete(id);

        List<Customer> customers = (List<Customer>) customerService.listAll();
        Customer returnedCustomer = customerService.getById(id);

        assertNull(returnedCustomer);
        assertEquals(2, customers.size());
        assertFalse(customers.contains(customerToDelete));

        customerService.saveOrUpdate(customerToDelete);// insert Customer back
    }

    @Test
    public void testSaveOrUpdateNewCustomerMethod() throws Exception {
        Customer actualCustomer = customerService.saveOrUpdate(newCustomer);
        assertThat(actualCustomer.getId(), notNullValue());
        assertThat(actualCustomer.getVersion(), is(0));

        assertCustomer(newCustomer,actualCustomer);

        customerService.delete(actualCustomer.getId());//cleanup
    }

    @Test
    public void testSaveOrUpdateExistingCustomerMethod() throws Exception {
        Integer id = 3;

        Customer expectedCustomer = customerService.getById(id);
        expectedCustomer.setFirstName("Dan1");
        expectedCustomer.setLastName("Newman1");
        expectedCustomer.setEmail("new1@test.com");
        expectedCustomer.setPhoneNumber("`55-55-55");
        expectedCustomer.setAddress1("Down1, Str, USA");
        expectedCustomer.setAddress2("Long1, Str, USA");
        expectedCustomer.setCity("New York1");
        expectedCustomer.setState("New York1");
        expectedCustomer.setZipCode("21001");

        Integer versionBefore = expectedCustomer.getVersion();

        Customer actualCustomer = customerService.saveOrUpdate(expectedCustomer);
        assertThat(actualCustomer.getVersion(), is(versionBefore+1));

        assertEquals(expectedCustomer.getId(), actualCustomer.getId());
        assertCustomer(expectedCustomer,actualCustomer);
    }

    private void assertCustomer(Customer expected, Customer actual){
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        assertEquals(expected.getAddress1(), actual.getAddress1());
        assertEquals(expected.getAddress2(), actual.getAddress2());
        assertEquals(expected.getCity(), actual.getCity());
        assertEquals(expected.getState(), actual.getState());
        assertEquals(expected.getZipCode(), actual.getZipCode());
    }
}
