package guru.springframework.controllers;

import guru.springframework.commands.ProductForm;
import guru.springframework.converters.ProductToProductForm;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Fudjitsu on 29.10.16.
 */
@RequestMapping("/product")
@Controller
public class ProductController {

    private ProductToProductForm productToProductForm;

    private ProductService productService;

    @Autowired
    public void setProductToProductForm(ProductToProductForm productToProductForm) {
        this.productToProductForm = productToProductForm;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"/list", "/"})
    public String listProducts(Model model) {
        model.addAttribute("products", productService.listAll());
        return "product/list";
    }

    @RequestMapping("/show/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = productService.getById(id);
        ProductForm productForm = productToProductForm.convert(product);
        model.addAttribute("productForm", productForm);
        return "product/productform";
    }

    @RequestMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "product/productform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(@Valid ProductForm productForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "product/productform";
        }
        ProductForm savedProduct = productService.saveOrUpdate(productForm);

        return "redirect:/product/show/" + savedProduct.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/product/list";
    }
}