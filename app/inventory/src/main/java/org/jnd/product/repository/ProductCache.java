package org.jnd.product.repository;

import org.jnd.microservices.model.Product;
import org.jnd.microservices.model.ProductType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("ProductCache")
public class ProductCache {

    public Map<String, Product> getFood()   {

        HashMap<String, Product> food = new HashMap<>();
        food.put("1", new Product("1", "marmalade", ProductType.FOOD, 1.29f));
        food.put("2", new Product("2", "milk", ProductType.FOOD, 0.83f));
        food.put("3", new Product("3", "chocolate", ProductType.FOOD, 0.79f));
        food.put("4", new Product("4", "bread", ProductType.FOOD, 1.09f));
        food.put("5", new Product("5", "broccoli", ProductType.FOOD, 0.99f));
        food.put("6", new Product("6", "chicken", ProductType.FOOD, 3.99f));
        food.put("7", new Product("7", "coffee", ProductType.FOOD, 3.39f));
        food.put("8", new Product("8", "tea", ProductType.FOOD, 1.29f));
        food.put("9", new Product("9", "biscuits", ProductType.FOOD, 0.79f));
        food.put("10", new Product("10", "cake", ProductType.FOOD, 2.79f));

        return food;
    }

    public Map<String, Product> getClothes() {

        HashMap<String, Product> clothes = new HashMap<>();
        clothes.put("11", new Product("11", "socks", ProductType.CLOTHES, 3.39f));
        clothes.put("12", new Product("12", "jacket", ProductType.CLOTHES, 49.99f));
        clothes.put("13", new Product("13", "shoes", ProductType.CLOTHES, 59.99f));
        clothes.put("14", new Product("14", "hat", ProductType.CLOTHES, 20.99f));
        return clothes;
    }

    public Map<String, Product> getGadgets() {

        HashMap<String, Product> gadgets = new HashMap<>();
        gadgets.put("11", new Product("11", "socks", ProductType.CLOTHES, 3.39f));
        gadgets.put("12", new Product("12", "jacket", ProductType.CLOTHES, 49.99f));
        gadgets.put("13", new Product("13", "shoes", ProductType.CLOTHES, 59.99f));
        gadgets.put("14", new Product("14", "hat", ProductType.CLOTHES, 20.99f));
        return gadgets;
    }
}