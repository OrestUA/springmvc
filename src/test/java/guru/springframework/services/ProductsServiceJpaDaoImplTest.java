package guru.springframework.services;

import guru.springframework.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by YSkakun on 11/1/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("jpadao")
public class ProductsServiceJpaDaoImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testListMethod() throws Exception{

        List<Product> products = (List<Product>) productService.listAll();

        assertEquals(5, products.size());
    }
}
