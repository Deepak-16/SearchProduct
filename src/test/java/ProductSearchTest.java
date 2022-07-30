import model.Product;
import org.junit.jupiter.api.Test;
import service.ProductInventoryService;
import service.ProductSearchService;

public class ProductSearchTest {

    @Test
    public void searchProductTests(){
        ProductSearchService productSearchService = new ProductSearchService();
        ProductInventoryService productInventoryService = new ProductInventoryService(productSearchService);

        Product p2 = new Product(2,"book","Oppressed test1", "Is a book written by Brazilian educator Paulo Freire");

        productInventoryService.addProduct(new Product(1,"book","Pedagogy of the Oppressed", "Is a book written by Brazilian educator Paulo Freire"));
        productInventoryService.addProduct(p2);
        productInventoryService.addProduct(new Product(3,"book","test1", "Is a book written by Brazilian educator Paulo Freire"));
        productInventoryService.addProduct(new Product(4,"book","unique", "Is a book written by Brazilian educator Paulo Freire"));

        System.out.println(productSearchService.searchProduct("Oppressed"));

        productInventoryService.removeProduct(p2);

        System.out.println(productSearchService.searchProduct("Oppressed"));

        p2.setName("Oppressed test1");
        productInventoryService.updateProduct(p2);

        System.out.println(productSearchService.searchProduct("Oppressed"));

        System.out.println(productSearchService.searchProduct("Oppressed",0,1));
        System.out.println(productSearchService.searchProduct("Oppressed",1,2));

    }
}
