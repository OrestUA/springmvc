package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.domain.security.Role;
import guru.springframework.enums.OrderStatus;
import guru.springframework.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by YSkakun on 11/1/2016.
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoleService roleService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        loadProducts1();
//        loadCustomers1();
//        loadUsers1();
//        loadCarts1();
        //loadOrders1();

        loadProducts();
        loadUsersAndCustomers();
        loadCarts();
        loadOrderHistory();
        loadRoles();
        assignUsersToDefaultRole();
    }

    private void loadOrders1() {
        User user1 = userService.getById(1);
        CartDetail detail1 = new CartDetail();
        detail1.setProduct(productService.getById(1));
        detail1.setQuantity(1);

//        CartDetail detail2 = new CartDetail();
//        detail2.setProduct(productService.getById(2));
//        detail2.setQuantity(2);

        user1.getCart().addCartDetail(detail1);
        //user1.getCart().addCartDetail(detail2);

        userService.saveOrUpdate(user1);

//        User user2 = userService.getById(2);
//        detail1 = new CartDetail();
//        detail1.setProduct(productService.getById(3));
//        detail1.setQuantity(3);
//
//        detail2 = new CartDetail();
//        detail2.setProduct(productService.getById(4));
//        detail2.setQuantity(12);
//
//        user2.getCart().addCartDetail(detail1);
//        user2.getCart().addCartDetail(detail2);
//
//        userService.saveOrUpdate(user2);
//
//        User user3 = userService.getById(2);
//        detail1 = new CartDetail();
//        detail1.setProduct(productService.getById(4));
//        detail1.setQuantity(21);
//
//        detail2 = new CartDetail();
//        detail2.setProduct(productService.getById(5));
//        detail2.setQuantity(7);
//
//        user3.getCart().addCartDetail(detail1);
//        user3.getCart().addCartDetail(detail2);
//
//        userService.saveOrUpdate(user3);

        Order order1 = new Order();
        order1.setCustomer(user1.getCustomer());
        order1.setOrderStatus(OrderStatus.NEW);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProduct(productService.getById(1));
        orderDetail1.setQuantity(2);

//        OrderDetail orderDetail2 = new OrderDetail();
//        orderDetail2.setProduct(productService.getById(2));
//        orderDetail2.setQuantity(4);

        order1.addOrderDetail(orderDetail1);
        //order1.addOrderDetail(orderDetail2);

//        customerService.saveOrUpdate(user1.getCustomer());
        orderService.saveOrUpdate(order1);
//
//        Order order2 = new Order();
//        order2.setCustomer(user2.getCustomer());
//        order2.setOrderStatus(Order.OrderStatus.NEW);
//
//        orderDetail1 = new OrderDetail();
//        orderDetail1.setProduct(productService.getById(3));
//        orderDetail1.setQuantity(11);
//
//        orderDetail2 = new OrderDetail();
//        orderDetail2.setProduct(productService.getById(4));
//        orderDetail2.setQuantity(45);
//
//        order2.addOrderDetail(orderDetail1);
//        order2.addOrderDetail(orderDetail2);
//
//        orderService.saveOrUpdate(order2);
//
//        Order order3 = new Order();
//        order3.setCustomer(user3.getCustomer());
//        order3.setOrderStatus(Order.OrderStatus.NEW);
//
//        orderDetail1 = new OrderDetail();
//        orderDetail1.setProduct(productService.getById(5));
//        orderDetail1.setQuantity(10);
//
//        order3.addOrderDetail(orderDetail1);
//
//        orderService.saveOrUpdate(order3);

    }

    private void loadCarts1() {
        User user1 = userService.getById(1);
        Cart cart1 = new Cart();
        cart1.setUser(user1);
        user1.setCart(cart1);
        userService.saveOrUpdate(user1);

        User user2 = userService.getById(2);
        Cart cart2 = new Cart();
        cart2.setUser(user2);
        user2.setCart(cart2);
        userService.saveOrUpdate(user2);

        User user3 = userService.getById(3);
        Cart cart3 = new Cart();
        cart3.setUser(user3);
        user3.setCart(cart3);
        userService.saveOrUpdate(user3);

    }

    private void loadUsers1() {
        Customer customer1 = customerService.getById(1);
        User user1 = new User();
        user1.setPassword("pas1");
        user1.setUsername("user1");
        user1.setCustomer(customer1);
        userService.saveOrUpdate(user1);

        Customer customer2 = customerService.getById(2);
        User user2 = new User();
        user2.setPassword("pas2");
        user2.setUsername("user2");
        user2.setCustomer(customer2);
        userService.saveOrUpdate(user2);

        Customer customer3 = customerService.getById(3);
        User user3 = new User();
        user3.setPassword("pas3");
        user3.setUsername("user3");
        user3.setCustomer(customer3);
        userService.saveOrUpdate(user3);
    }

    public void loadCustomers1() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Jon");
        customer1.setLastName("Milko");
        customer1.setEmail("user1@test.com");
        customer1.setPhoneNumber("555-75-75");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("California, Str, USA");
        customer1.getBillingAddress().setAddressLine2("Washington, Str, USA");
        customer1.getBillingAddress().setCity("Los Angeles");
        customer1.getBillingAddress().setState("California");
        customer1.getBillingAddress().setZipCode("00001");

        customer1.setShippingAddress(new Address());
        customer1.getShippingAddress().setAddressLine1("California, Str, USA2");
        customer1.getShippingAddress().setAddressLine2("Washington, Str, USA2");
        customer1.getShippingAddress().setCity("Los Angeles2");
        customer1.getShippingAddress().setState("California2");
        customer1.getShippingAddress().setZipCode("00002");

//        User user1 = new User();
//        user1.setPassword("pas1");
//        user1.setUsername("user1");
//        user1.setCustomer(customer1);
//        customer1.setUser(user1);

        customerService.saveOrUpdate(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Mike");
        customer2.setLastName("Portnoy");
        customer2.setEmail("user2@test.com");
        customer2.setPhoneNumber("555-723-753");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLine1("San Francisco, Str, USA");
        customer2.getBillingAddress().setAddressLine2("Chicago, Str, USA");
        customer2.getBillingAddress().setCity("San Francisco");
        customer2.getBillingAddress().setState("California");
        customer2.getBillingAddress().setZipCode("00001");

        customer2.setShippingAddress(new Address());
        customer2.getShippingAddress().setAddressLine1("San Francisco, Str, USA2");
        customer2.getShippingAddress().setAddressLine2("Chicago, Str, USA2");
        customer2.getShippingAddress().setCity("San Francisco2");
        customer2.getShippingAddress().setState("California2");
        customer2.getShippingAddress().setZipCode("00002");

//        User user2 = new User();
//        user2.setPassword("pas2");
//        user2.setUsername("user2");
//        user2.setCustomer(customer2);
//        customer1.setUser(user2);

        customerService.saveOrUpdate(customer2);

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setFirstName("Iren");
        customer3.setLastName("Dido");
        customer3.setEmail("user3@test.com");
        customer3.setPhoneNumber("555-23-53");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLine1("New York, Str, USA");
        customer3.getBillingAddress().setAddressLine2("New Jersey, Str, USA");
        customer3.getBillingAddress().setCity("New Jersey");
        customer3.getBillingAddress().setState("New York");
        customer3.getBillingAddress().setZipCode("21000");

        customer3.setShippingAddress(new Address());
        customer3.getShippingAddress().setAddressLine1("New York, Str, USA2");
        customer3.getShippingAddress().setAddressLine2("New Jersey, Str, USA2");
        customer3.getShippingAddress().setCity("New Jersey2");
        customer3.getShippingAddress().setState("New York2");
        customer3.getShippingAddress().setZipCode("21002");

//        User user3= new User();
//        user3.setPassword("pas3");
//        user3.setUsername("user3");
//        user3.setCustomer(customer3);
//        customer3.setUser(user3);

        customerService.saveOrUpdate(customer3);

    }

    public void loadProducts1() {
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

    ////////////////////////////
    private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("CUSTOMER")) {
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
        });
    }

    private void loadRoles(){
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.saveOrUpdate(role);
    }

    private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setOrderStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addOrderDetail(orderDetail);
                //orderService.saveOrUpdate(order);

            });
            //orderService.saveOrUpdate(order);
            //userService.saveOrUpdate(user);
        });
    }

    private void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.saveOrUpdate(user);
        });
    }

    public void loadUsersAndCustomers() {
        User user1 = new User();
        user1.setUsername("mweston");
        user1.setPassword("password");

        Customer customer1 = new Customer();
        customer1.setFirstName("Micheal");
        customer1.setLastName("Weston");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("1 Main St");
        customer1.getBillingAddress().setCity("Miami");
        customer1.getBillingAddress().setState("Florida");
        customer1.getBillingAddress().setZipCode("33101");
        customer1.setEmail("micheal@burnnotice.com");
        customer1.setPhoneNumber("305.333.0101");
        user1.setCustomer(customer1);
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("fglenanne");
        user2.setPassword("password");

        Customer customer2 = new Customer();
        customer2.setFirstName("Fiona");
        customer2.setLastName("Glenanne");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLine1("1 Key Biscane Ave");
        customer2.getBillingAddress().setCity("Miami");
        customer2.getBillingAddress().setState("Florida");
        customer2.getBillingAddress().setZipCode("33101");
        customer2.setEmail("fiona@burnnotice.com");
        customer2.setPhoneNumber("305.323.0233");
        user2.setCustomer(customer2);
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("saxe");
        user3.setPassword("password");
        Customer customer3 = new Customer();
        customer3.setFirstName("Sam");
        customer3.setLastName("Axe");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLine1("1 Little Cuba Road");
        customer3.getBillingAddress().setCity("Miami");
        customer3.getBillingAddress().setState("Florida");
        customer3.getBillingAddress().setZipCode("33101");
        customer3.setEmail("sam@burnnotice.com");
        customer3.setPhoneNumber("305.426.9832");

        user3.setCustomer(customer3);
        userService.saveOrUpdate(user3);
    }

    public void loadProducts() {

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
