package guru.springframework.services;

import guru.springframework.domain.Product;
import org.junit.Before;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by YSkakun on 11/1/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("jpadao")
public class ProductsServiceJpaDaoImplTest {

    @Autowired
    private ProductService productService;

    private static Product expectedProduct1;

    private static Product expectedProduct3;

    private static Product newProduct;

    @BeforeClass
    public static void setup() {
        expectedProduct1 = new Product();
        expectedProduct1.setId(1);
        expectedProduct1.setDescription("Product 1");
        expectedProduct1.setPrice(new BigDecimal("12.99"));
        expectedProduct1.setImageUrl("http://example.com/product1");

        expectedProduct3 = new Product();
        expectedProduct3.setId(3);
        expectedProduct3.setDescription("Product 3");
        expectedProduct3.setPrice(new BigDecimal("34.99"));
        expectedProduct3.setImageUrl("http://example.com/product3");

        newProduct = new Product();
        newProduct.setDescription("New Product");
        newProduct.setPrice(new BigDecimal("55.55"));
        newProduct.setImageUrl("http://example.com/newproduct");
    }

    @Test
    public void testListMethod() throws Exception {
        List<Product> products = (List<Product>) productService.listAll();

        assertEquals(5, products.size());

        Product actualProduct = products.get(0);

        assertEquals(expectedProduct1.getId(), actualProduct.getId());
        assertEquals(expectedProduct1.getDescription(), actualProduct.getDescription());
        assertEquals(expectedProduct1.getPrice(), actualProduct.getPrice());
        assertEquals(expectedProduct1.getImageUrl(), actualProduct.getImageUrl());
    }

    @Test
    public void testGetByIdMethod() throws Exception {
        Integer id = 1;
        Product actualProduct = productService.getById(id);

        assertEquals(expectedProduct1.getId(), actualProduct.getId());
        assertEquals(expectedProduct1.getDescription(), actualProduct.getDescription());
        assertEquals(expectedProduct1.getPrice(), actualProduct.getPrice());
        assertEquals(expectedProduct1.getImageUrl(), actualProduct.getImageUrl());
    }

    @Test
    public void testDeleteMethod() throws Exception {
        Integer id = 2;
        Product productToDelete = productService.getById(id);
        productService.delete(id);

        List<Product> products = (List<Product>) productService.listAll();
        Product returnedProduct = productService.getById(id);

        assertNull(returnedProduct);
        assertEquals(4, products.size());

        Product actualProduct = products.get(1);

        assertEquals(expectedProduct3.getId(), actualProduct.getId());
        assertEquals(expectedProduct3.getDescription(), actualProduct.getDescription());
        assertEquals(expectedProduct3.getPrice(), actualProduct.getPrice());
        assertEquals(expectedProduct3.getImageUrl(), actualProduct.getImageUrl());

        productService.saveOrUpdate(productToDelete);// insert product back
    }

    @Test
    public void testSaveOrUpdateNewProductMethod() throws Exception {
        Product actualProduct = productService.saveOrUpdate(newProduct);
        assertThat(actualProduct.getId(), notNullValue());
        assertThat(actualProduct.getVersion(), is(0));
        assertEquals(newProduct.getImageUrl(), actualProduct.getImageUrl());
        assertEquals(newProduct.getDescription(), actualProduct.getDescription());
        assertEquals(newProduct.getPrice(), actualProduct.getPrice());

        productService.delete(actualProduct.getId());//cleanup
    }

    @Test
    public void testSaveOrUpdateExistingProductMethod() throws Exception {
        Integer id = 4;
        Product expectedProduct = productService.getById(id);
        expectedProduct.setDescription("updated description");
        expectedProduct.setPrice(new BigDecimal("11.11"));
        expectedProduct.setImageUrl("new image URL");
        Integer versionBefore = expectedProduct.getVersion();

        Product actualProduct = productService.saveOrUpdate(expectedProduct);
        assertEquals(expectedProduct.getId(), actualProduct.getId());
        assertEquals(expectedProduct.getDescription(), actualProduct.getDescription());
        assertEquals(expectedProduct.getPrice(), actualProduct.getPrice());
        assertEquals(expectedProduct.getDescription(), actualProduct.getDescription());
        assertThat(actualProduct.getVersion(), is(versionBefore+1));
    }
}
