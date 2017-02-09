package guru.springframework.converters;

import guru.springframework.commands.ProductForm;
import guru.springframework.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Fudjitsu on 29.01.2017.
 */
@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {
    @Override
    public ProductForm convert(Product product) {

        ProductForm productForm = new ProductForm();
        productForm.setId(productForm.getId());
        productForm.setVersion(productForm.getVersion());
        productForm.setPrice(productForm.getPrice());
        productForm.setDescription(productForm.getDescription());
        productForm.setImageUrl(productForm.getImageUrl());

        return productForm;
    }
}
