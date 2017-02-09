package guru.springframework.controllers;

import guru.springframework.commands.CustomerForm;
import guru.springframework.commands.validator.CustomerFormPasswordValidator;
import guru.springframework.converters.CustomerToCustomerForm;
import guru.springframework.domain.Address;
import guru.springframework.domain.Customer;
import guru.springframework.domain.User;
import guru.springframework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Fudjitsu on 31.10.16.
 */
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        customerController.setCustomerFormPasswordValidator(new CustomerFormPasswordValidator());
        customerController.setCustomerToCustomerForm( new CustomerToCustomerForm());
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAll()).thenReturn((List) customers);

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));

        mockMvc.perform(get("/customer/"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testEdit() throws Exception {

        Integer id = 1;

        User user = new User();
        Customer customer = new Customer();
        customer.setUser(user);

        when(customerService.getById(id)).thenReturn(customer);

        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customerForm", instanceOf(CustomerForm.class)));
    }

    @Test
    public void testNewCustomer() throws Exception {
        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customerForm", instanceOf(CustomerForm.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String userName = "Test";
        String firstName = "John";
        String lastName = "Milton";
        String email = "test@test.com";
        String phoneNumber = "055-45-45";
        String zipCode = "21000";
        String address1 = "Str. 1";
        String address2 = "Str. 2";
        String state = "Washington";
        String city = "Seattle";

        Customer returnCustomer = new Customer();
        returnCustomer.setId(id);
        returnCustomer.setUser(new User());
        returnCustomer.setFirstName(firstName);
        returnCustomer.setLastName(lastName);
        returnCustomer.setEmail(email);
        returnCustomer.setPhoneNumber(phoneNumber);
        returnCustomer.setBillingAddress(new Address());
        returnCustomer.getBillingAddress().setZipCode(zipCode);
        returnCustomer.getBillingAddress().setAddressLine1(address1);
        returnCustomer.getBillingAddress().setAddressLine2(address2);
        returnCustomer.getBillingAddress().setState(state);
        returnCustomer.getBillingAddress().setCity(city);

        when(customerService.saveOrUpdate(Mockito.any(CustomerForm.class)))
                .thenReturn( new CustomerToCustomerForm().convert(returnCustomer));

        mockMvc.perform(post("/customer")
                        .param("userId", "1")
                        .param("userName", userName)
                        .param("firstName", firstName)
                        .param("lastName", lastName)
                        .param("email", email)
                        .param("phoneNumber", phoneNumber)
                //TODO: refactor when customer form is ready
//                .param("billingAddress.zipCode", zipCode)
//                .param("billingAddress.addressLine1", address1)
//                .param("billingAddress.addressLine2", address2)
//                .param("billingAddress.state", state)
//                .param("billingAddress.city", city)
        )

                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/show/1"))
                .andExpect(model().attribute("customerForm", instanceOf(CustomerForm.class)))
                .andExpect(model().attribute("customerForm", hasProperty("userId", is(id))))
                .andExpect(model().attribute("customerForm", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("customerForm", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("customerForm", hasProperty("email", is(email))))
                .andExpect(model().attribute("customerForm", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("customerForm", hasProperty("userName", is(userName))))
//                .andExpect(model().attribute("customer", hasProperty("billingAddress", hasProperty("zipCode", is(zipCode)))))
//                .andExpect(model().attribute("customer", hasProperty("billingAddress", hasProperty("addressLine1", is(address1)))))
//                .andExpect(model().attribute("customer", hasProperty("billingAddress", hasProperty("addressLine2", is(address2)))))
//                .andExpect(model().attribute("customer", hasProperty("billingAddress", hasProperty("state", is(state)))))
//                .andExpect(model().attribute("customer", hasProperty("billingAddress", hasProperty("city", is(city)))))
        ;

        //verify properties of bound object
        ArgumentCaptor<CustomerForm> boundCustomer = ArgumentCaptor.forClass(CustomerForm.class);
        verify(customerService).saveOrUpdate(boundCustomer.capture());

        CustomerForm updateCustomer = boundCustomer.getValue();
        assertEquals(id, updateCustomer.getUserId());
        assertEquals(firstName, updateCustomer.getFirstName());
        assertEquals(lastName, updateCustomer.getLastName());
        assertEquals(email, updateCustomer.getEmail());
        assertEquals(phoneNumber, updateCustomer.getPhoneNumber());
//        assertEquals(zipCode, updateCustomer.getBillingAddress().getZipCode());
//        assertEquals(address1, updateCustomer.getBillingAddress().getAddressLine1());
//        assertEquals(address2, updateCustomer.getBillingAddress().getAddressLine2());
//        assertEquals(state, updateCustomer.getBillingAddress().getState());
//        assertEquals(city, updateCustomer.getBillingAddress().getCity());
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));

        verify(customerService, times(1)).delete(id);
    }
}