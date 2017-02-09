package guru.springframework.services;

import guru.springframework.commands.ProductForm;
import guru.springframework.domain.Product;

import java.util.List;

/**
 * Created by Fudjitsu on 29.10.16.
 */
public interface ProductService extends CRUDService<Product> {

    ProductForm saveOrUpdate(ProductForm productForm);

}
