package service;

import model.Product;
import model.Query;
import model.QueryType;
import model.TrieNode;
import repository.ProductRepository;

import java.lang.reflect.Field;
import java.util.*;

public class ProductSearchService {
    public static TrieNode root;

    Set<String> indexes = new HashSet<>();

    public ProductSearchService() {
        this.root = new TrieNode();
    }

    public Set<Integer> searchProduct(String searchString){
        TrieNode trie = this.root;
        for(int i=0; i<searchString.length(); i++){
            if(getIgnoreRegex().contains(String.valueOf(searchString.charAt(i)))){
                continue;
            }
            trie = trie.getNodes(searchString.charAt(i));
            if(trie == null){
                return new HashSet<>();
            }
        }
        return trie.getProductIds();
    }

    public Set<Integer> searchProduct(String searchString, Integer offset, Integer count){
        TrieNode trie = this.root;
        for(int i=0; i<searchString.length(); i++){
            if(getIgnoreRegex().contains(String.valueOf(searchString.charAt(i)))){
                continue;
            }
            trie = trie.getNodes(searchString.charAt(i));
            if(trie == null){
                return new HashSet<>();
            }
        }

        List<Integer> list = new ArrayList<>();
        list.addAll(trie.getProductIds());
        list.sort(Comparator.naturalOrder());

        // optimization would be to used sorted list or linked hashSet in product instead of creating one at runtime.
        Set<Integer> set = new HashSet<>();
        for(int i=offset; i<count && i<list.size(); i++){
            set.add(list.get(i));
        }
        return set;
    }

    //not finished.
    public Set<Integer> searchProduct(Query query){
        Collection<Product> products = ProductRepository.getAllProduct();
        Set<Integer> sol = new HashSet<>();

        if(query.left == null && query.right == null){
            for(Product product : products){
                //check for match based on key value
                // add to set
            }
            return sol;
        }


        Set<Integer> leftQuery = searchProduct(query.left);
        Set<Integer> rightQuery = searchProduct(query.right);

        if(query.queryType == QueryType.AND){
            leftQuery.removeAll(rightQuery);
        }else{
            leftQuery.addAll(rightQuery);
        }
        sol = leftQuery;

        return sol;
    }

    public void removeSearchStringToProduct(String searchString, Product product){
        String[] array = searchString.split(getSplitRegex());

        for(String cur : array){
            TrieNode trie = this.root;
            for(int i=0; i<cur.length(); i++){
                if(getIgnoreRegex().contains(String.valueOf(cur.charAt(i)))){
                    continue;
                }
                if(trie == null) return;
                trie = trie.addNode(cur.charAt(i));
            }
            if(trie == null) return;
            trie.removeProductId(product.getId());
        }
    }



    /*
    reflection for indexes.
    public void addSearchStringToProduct(Product product){

        for(Field field : product.getClass().getDeclaredFields()){
            if(indexes.contains(field.getName())){
                this.addSearchStringToProduct(field.get(), product);
            }
        }
    }
     */

    public void addSearchStringToProduct(String searchString, Product product){
        String[] array = searchString.split(getSplitRegex());

        for(String cur : array){
            TrieNode trie = this.root;
            for(int i=0; i<cur.length(); i++){
                if(getIgnoreRegex().contains(String.valueOf(cur.charAt(i)))){
                    continue;
                }
                trie = trie.addNode(cur.charAt(i));
            }
            trie.addProductId(product.getId());
        }
    }

    public Product searchProduct(int productId){
        return ProductRepository.getProduct(productId);
    }
    public String getSplitRegex(){
        return " ";
    }

    public String getIgnoreRegex(){
        return "";
    }
}
