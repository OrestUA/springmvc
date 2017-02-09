package guru.springframework.services.jpaservices;

import guru.springframework.commands.ProductForm;
import guru.springframework.converters.ProductFormToProduct;
import guru.springframework.converters.ProductToProductForm;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by YSkakun on 11/1/2016.
 */
@Service
@Profile("jpadao")
public class ProductServiceJpaDaoImpl extends AbstractJpaDaoService implements ProductService {

    private ProductToProductForm productToProductForm;
    private ProductFormToProduct productFormToProduct;

    @Autowired
    public void setProductToProductForm(ProductToProductForm productToProductForm) {
        this.productToProductForm = productToProductForm;
    }

    @Autowired
    public void setProductFormToProduct(ProductFormToProduct productFormToProduct) {
        this.productFormToProduct = productFormToProduct;
    }

    @Override
    public List<Product> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Product.class, id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Product savedProduct = em.merge(domainObject);//creates new if not exists or updates existing one product
        em.getTransaction().commit();
        return savedProduct;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Product.class,id));
        em.getTransaction().commit();
    }

    @Override
    public ProductForm saveOrUpdate(ProductForm productForm) {
        Product newProduct = productFormToProduct.convert(productForm);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Product savedProduct = em.merge(newProduct);//creates new if not exists or updates existing one product
        em.getTransaction().commit();
        return productToProductForm.convert(savedProduct);
    }
}