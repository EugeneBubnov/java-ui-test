package utils;

import java.util.List;
import java.util.Map;

public class ProductData {
    private static final List<Map<String, String>> PRODUCTS = List.of(
            Map.of(
                    "name", "Sauce Labs Backpack",
                    "desc", "carry.allTheThings() with the sleek, " +
                            "streamlined Sly Pack that melds uncompromising " +
                            "style with unequaled laptop and tablet protection.",
                    "price", "$29.99"
            ),
            Map.of(
                    "name", "Sauce Labs Bike Light",
                    "desc", "A red light isn't the desired state in testing " +
                            "but it sure helps when riding your bike at night. " +
                            "Water-resistant with 3 lighting modes, 1 AAA battery included.",
                    "price", "$9.99"
            ),
            Map.of(
                    "name", "Sauce Labs Bolt T-Shirt",
                    "desc", "Get your testing superhero on with the Sauce Labs " +
                            "bolt T-shirt. From American Apparel, 100% ringspun combed cotton, " +
                            "heather gray with red bolt.",
                    "price", "$15.99"
            ),
            Map.of(
                    "name", "Sauce Labs Fleece Jacket",
                    "desc", "It's not every day that you come across a midweight " +
                            "quarter-zip fleece jacket capable of handling everything " +
                            "from a relaxing day outdoors to a busy day at the office.",
                    "price", "$49.99"
            ),
            Map.of(
                    "name", "Sauce Labs Onesie",
                    "desc", "Rib snap infant onesie for the junior automation engineer " +
                            "in development. Reinforced 3-snap bottom closure, two-needle " +
                            "hemmed sleeved and bottom won't unravel.",
                    "price", "$7.99"
            ),
            Map.of(
                    "name", "Test.allTheThings() T-Shirt (Red)",
                    "desc", "This classic Sauce Labs t-shirt is perfect to wear when " +
                            "cozying up to your keyboard to automate a few tests. " +
                            "Super-soft and comfy ringspun combed cotton.",
                    "price", "$15.99"
            )
    );

    public static List<Map<String, String>> getAllProducts() {
        return PRODUCTS;
    }

    public static Map<String, String> getProductByName(String productName) {
        return PRODUCTS.stream()
                .filter(product -> product.get("name").equals(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(productName + " not found"));
    }
}
