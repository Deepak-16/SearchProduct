package repository;

import model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    static Map<Integer, Product> productMap = new HashMap<>();

    public static Product getProduct(int productId){
        return productMap.get(productId);
    }

    public static Collection<Product> getAllProduct(){
        return productMap.values();
    }

    public static void addProduct(Product product){
        if(productMap.containsKey(product.getId())){
            throw new IllegalArgumentException("Product already exists!");
        }

        productMap.put(product.getId(), product);
    }

    public static void updateProduct(Product product){
        productMap.put(product.getId(), product);
    }

    public static void removeProduct(Product product){
        productMap.remove(product.getId());
    }
}
