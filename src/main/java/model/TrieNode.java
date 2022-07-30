package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrieNode {
    TrieNode[] nodes;
    Set<Integer> productIds;

    public static int totalChars = 256;

    public TrieNode(){
        nodes = new TrieNode[256];
        productIds = new HashSet<>();
    }

    public TrieNode getNodes(char ch) {
        return nodes[ch];
    }

    public TrieNode addNode(char ch) {
        if(nodes[ch] == null){
            nodes[ch] = new TrieNode();
        }
        return nodes[ch];
    }

    public Set<Integer> getProductIds() {
        return productIds;
    }

    public void addProductId(Integer productId) {
        this.productIds.add(productId);
    }

    public void removeProductId(Integer productId) {
        this.productIds.remove(productId);
    }
}
