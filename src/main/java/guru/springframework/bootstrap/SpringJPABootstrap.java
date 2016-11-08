package guru.springframework.bootstrap;

import guru.springframework.domain.Address;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by YSkakun on 11/1/2016.
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadCustomers();
    }

    public void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Jon");
        customer1.setLastName("Milko");
        customer1.setEmail("user1@test.com");
        customer1.setPhoneNumber("555-75-75");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddress_line1("California, Str, USA");
        customer1.getBillingAddress().setAddress_line2("Washington, Str, USA");
        customer1.getBillingAddress().setCity("Los Angeles");
        customer1.getBillingAddress().setState("California");
        customer1.getBillingAddress().setZipCode("00001");

        customer1.setShippingAddress(new Address());
        customer1.getShippingAddress().setAddress_line1("California, Str, USA2");
        customer1.getShippingAddress().setAddress_line2("Washington, Str, USA2");
        customer1.getShippingAddress().setCity("Los Angeles2");
        customer1.getShippingAddress().setState("California2");
        customer1.getShippingAddress().setZipCode("00002");
        customerService.saveOrUpdate(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Mike");
        customer2.setLastName("Portnoy");
        customer2.setEmail("user2@test.com");
        customer2.setPhoneNumber("555-723-753");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddress_line1("San Francisco, Str, USA");
        customer2.getBillingAddress().setAddress_line2("Chicago, Str, USA");
        customer2.getBillingAddress().setCity("San Francisco");
        customer2.getBillingAddress().setState("California");
        customer2.getBillingAddress().setZipCode("00001");

        customer2.setShippingAddress(new Address());
        customer2.getShippingAddress().setAddress_line1("San Francisco, Str, USA2");
        customer2.getShippingAddress().setAddress_line2("Chicago, Str, USA2");
        customer2.getShippingAddress().setCity("San Francisco2");
        customer2.getShippingAddress().setState("California2");
        customer2.getShippingAddress().setZipCode("00002");
        customerService.saveOrUpdate(customer2);

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setFirstName("Iren");
        customer3.setLastName("Dido");
        customer3.setEmail("user3@test.com");
        customer3.setPhoneNumber("555-23-53");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddress_line1("New York, Str, USA");
        customer3.getBillingAddress().setAddress_line2("New Jersey, Str, USA");
        customer3.getBillingAddress().setCity("New Jersey");
        customer3.getBillingAddress().setState("New York");
        customer3.getBillingAddress().setZipCode("21000");

        customer3.setShippingAddress(new Address());
        customer3.getShippingAddress().setAddress_line1("New York, Str, USA2");
        customer3.getShippingAddress().setAddress_line2("New Jersey, Str, USA2");
        customer3.getShippingAddress().setCity("New Jersey2");
        customer3.getShippingAddress().setState("New York2");
        customer3.getShippingAddress().setZipCode("21002");
        customerService.saveOrUpdate(customer3);

    }

    public void loadProducts(){
        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://example.com/product1");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://example.com/product2");
        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://example.com/product3");
        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http://example.com/product4");
        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("25.99"));
        product5.setImageUrl("http://example.com/product5");
        productService.saveOrUpdate(product5);

    }
}
