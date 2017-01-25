package guru.springframework.converters;

import guru.springframework.commands.*;
import guru.springframework.domain.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by YSkakun on 1/25/2017.
 */
@Component
public class ProductFormToProduct implements Converter<ProductForm, Product> {
    @Override
    public Product convert(ProductForm productForm) {

        Product product = new Product();
        product.setId(productForm.getId());
        product.setVersion(productForm.getVersion());
        product.setPrice(productForm.getPrice());
        product.setDescription(productForm.getDescription());
        product.setImageUrl(productForm.getImageUrl());

        return product;
    }
}
