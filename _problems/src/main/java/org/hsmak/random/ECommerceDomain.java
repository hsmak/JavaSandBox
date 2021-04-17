package org.hsmak.random;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Links:
 *      - https://www.baeldung.com/spring-angular-ecommerce
 *      - https://medium.com/adyen/building-an-e-commerce-application-using-java-react-54015b81d6c9
 */
public class ECommerceDomain {

    class User {
        int id;
        String username;
        String firstName;
        String lastName;
        String email;
    }

    class CustomerDetails {
        User user;
        String phone;
        String addressLine1;
        String addressLine2;
        String city;
        String country;
    }

    class Item {
        int id;
        String name;
        String description;
        double price;
    }

    class PlacedItem {
        Item item;
        int quantity;
    }

    class Cart {
        List<PlacedItem> placedItems;
        boolean orderPlaced; // once customer hits "Place Order", this will be set to true and used to create an Order

        double getTotalPrice() {
            double sum = 0;
            for (PlacedItem placedItem : placedItems)
                sum += placedItem.item.price;
            return sum;
        }

        int getTotalNumberOfItems() {
            return placedItems.size();
        }
    }

    class Order {
        int id;
        LocalDateTime datePlaced;
        OrderStatus orderStatus;
        Cart cart;
        int totalItems; // can be retrieved from Cart
        double totalPrice; // can be retrieved from Cart
    }
    enum OrderStatus {
        PENDING, PAID, COMPLETED, CANCELLED
    }
}
