package guru.springframework.services;

import guru.springframework.domain.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YSkakun on 11/3/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Test
    public void testSaveOrUpdateUser() throws Exception {
        User user = new User();

        user.setUsername("somename");
        user.setPassword("pwd1234");

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getEncryptedPassword());

        System.out.println("Encrypted password:\n" + savedUser.getEncryptedPassword());
    }

    @Test
    public void testSaveOfUserWithCustomer() throws Exception {
        User user = new User();

        user.setUsername("somename");
        user.setPassword("pwd1234");

        Customer customer = new Customer();
        customer.setFirstName("Ivan");
        customer.setLastName("Norris");

        user.setCustomer(customer);

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getVersion());
        assertNotNull(savedUser.getCustomer());
        assertNotNull(savedUser.getCustomer().getId());
    }

    @Test
    public void testAddCartToUser() throws Exception {
        User user = new User();

        user.setUsername("somename");
        user.setPassword("pwd1234");

        user.setCart(new Cart());

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getVersion());
        assertNotNull(savedUser.getCart());
        assertNotNull(savedUser.getCart().getId());
    }

    @Test
    public void testAddCartToUserWithCartDetails() throws Exception {
        User user = new User();

        user.setUsername("somename");
        user.setPassword("pwd1234");

        Cart cart = new Cart();
        user.setCart(cart);

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(cartItemOne);

        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemTwo);


        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getVersion());
        assertNotNull(savedUser.getCart());
        assertNotNull(savedUser.getCart().getCartDetails());
        assertEquals(2, savedUser.getCart().getCartDetails().size());
        assertNotNull(savedUser.getCart().getCartDetails().get(0).getId());
    }

    @Test
    public void testAddAndRemoveCartToUserWithCartDetails() throws Exception {
        User user = new User();

        user.setUsername("somename");
        user.setPassword("pwd1234");

        Cart cart = new Cart();
        user.setCart(cart);

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(cartItemOne);

        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemTwo);


        User savedUser = userService.saveOrUpdate(user);

        assertEquals(2, savedUser.getCart().getCartDetails().size());
        savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(0));

        userService.saveOrUpdate(savedUser);

        assertEquals(1, savedUser.getCart().getCartDetails().size());
    }

}
