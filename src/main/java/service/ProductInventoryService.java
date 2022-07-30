package service;

import model.Product;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductInventoryService {

    ProductSearchService searchService;

    public ProductInventoryService(ProductSearchService searchService) {
        this.searchService = searchService;
    }

    public void addProduct(Product product){
        ProductRepository.addProduct(product);
        this.searchService.addSearchStringToProduct(product.getName(), product);
    }

    public void updateProduct(Product product){
        ProductRepository.updateProduct(product);
        this.searchService.removeSearchStringToProduct(product.getName(), product);
        this.searchService.addSearchStringToProduct(product.getName(), product);
    }

    public void removeProduct(Product product){
        ProductRepository.removeProduct(product);
        this.searchService.removeSearchStringToProduct(product.getName(), product);
    }
}
