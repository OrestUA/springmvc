package guru.springframework.services;

import guru.springframework.domain.Address;
import guru.springframework.domain.Customer;
import guru.springframework.domain.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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

//    User user1 = new User();
//        user1.setUsername("mweston");
//        user1.setPassword("password");
//
//    Customer customer1 = new Customer();
//        customer1.setFirstName("Micheal");
//        customer1.setLastName("Weston");
//        customer1.setBillingAddress(new Address());
//        customer1.getBillingAddress().setAddressLine1("1 Main St");
//        customer1.getBillingAddress().setCity("Miami");
//        customer1.getBillingAddress().setState("Florida");
//        customer1.getBillingAddress().setZipCode("33101");
//        customer1.setEmail("micheal@burnnotice.com");
//        customer1.setPhoneNumber("305.333.0101");
//        user1.setCustomer(customer1);
//        userService.saveOrUpdate(user1);
//
//    User user2 = new User();
//        user2.setUsername("fglenanne");
//        user2.setPassword("password");
//
//    Customer customer2 = new Customer();
//        customer2.setFirstName("Fiona");
//        customer2.setLastName("Glenanne");
//        customer2.setBillingAddress(new Address());
//        customer2.getBillingAddress().setAddressLine1("1 Key Biscane Ave");
//        customer2.getBillingAddress().setCity("Miami");
//        customer2.getBillingAddress().setState("Florida");
//        customer2.getBillingAddress().setZipCode("33101");
//        customer2.setEmail("fiona@burnnotice.com");
//        customer2.setPhoneNumber("305.323.0233");
//        user2.setCustomer(customer2);
//        userService.saveOrUpdate(user2);
//
//    User user3 = new User();
//        user3.setUsername("saxe");
//        user3.setPassword("password");
//    Customer customer3 = new Customer();
//        customer3.setFirstName("Sam");
//        customer3.setLastName("Axe");
//        customer3.setBillingAddress(new Address());
//        customer3.getBillingAddress().setAddressLine1("1 Little Cuba Road");
//        customer3.getBillingAddress().setCity("Miami");
//        customer3.getBillingAddress().setState("Florida");
//        customer3.getBillingAddress().setZipCode("33101");
//        customer3.setEmail("sam@burnnotice.com");
//        customer3.setPhoneNumber("305.426.9832");

    @BeforeClass
    public static void setup() {
        expectedCustomer1 =  createCustomer(1,"Micheal","Weston","micheal@burnnotice.com","305.333.0101","1 Main St",
                null,"Miami","Florida","33101");

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
        customer.setBillingAddress(new Address());
        customer.getBillingAddress().setAddressLine1(address1);
        customer.getBillingAddress().setAddressLine2(address2);
        customer.getBillingAddress().setCity(city);
        customer.getBillingAddress().setState(state);
        customer.getBillingAddress().setZipCode(zip);
        return customer;
    }

    @Test
    public void testSaveWithUser() throws Exception {
        Customer customer = new Customer();
        User user = new User();
        user.setUsername("myUsername");
        user.setPassword("myPassword");
        customer.setUser(user);

        Customer savedCustomer = customerService.saveOrUpdate(customer);
        assertNotNull(savedCustomer);
        customerService.delete(savedCustomer.getId());
        assertNull(customerService.getById(savedCustomer.getId()));

    }

    @Test
    public void testListMethod() throws Exception {
        List<Customer> customers = (List<Customer>) customerService.listAll();

        assertEquals(3, customers.size());

        Customer actualCustomer = customers.get(0);

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
        expectedCustomer.setBillingAddress(new Address());
        expectedCustomer.getBillingAddress().setAddressLine1("Down1, Str, USA");
        expectedCustomer.getBillingAddress().setAddressLine2("Long1, Str, USA");
        expectedCustomer.getBillingAddress().setCity("New York1");
        expectedCustomer.getBillingAddress().setState("New York1");
        expectedCustomer.getBillingAddress().setZipCode("21001");

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
        assertEquals(expected.getBillingAddress().getAddressLine1(), actual.getBillingAddress().getAddressLine1());
        assertEquals(expected.getBillingAddress().getAddressLine2(), actual.getBillingAddress().getAddressLine2());
        assertEquals(expected.getBillingAddress().getCity(), actual.getBillingAddress().getCity());
        assertEquals(expected.getBillingAddress().getState(), actual.getBillingAddress().getState());
        assertEquals(expected.getBillingAddress().getZipCode(), actual.getBillingAddress().getZipCode());
    }
}
