package guru.springframework.services;

import guru.springframework.domain.Product;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by YSkakun on 11/2/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("map")
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Ignore
    @Test
    public void testListMethod() throws Exception {
        List<Product> products = (List<Product>) productService.listAll();

        assertEquals(5, products.size());

    }
}