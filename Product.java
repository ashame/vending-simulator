import java.util.Objects;

/**
 * A Product object to be used as merchandise in {@link VendingMachine}
 * @author nathan
 *         created on 2017-02-18.
 */
public class Product {
    private final double price;
    private final String name;

    /**
     * Constructs a new product with a given name and price
     * @param name the name of the product - case sensitive
     * @param price the dollar price of the product
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Checks if an object of the same type is equal to this product
     * @param o the object to check
     * @return whether the object is equal
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Product) {
            Product product = (Product) o;
            if ((product.getPrice() == this.price) &&
                    (product.getName().equals(this.name))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the dollar price of the product
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @return the name of the product
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the hash code of the Product
     */
    public int hashCode() {
        return Objects.hash(name, price);
    }

    /**
     * @return a string representation of the product in the format Product[name, price]
     */
    public String toString() {
        return String.format("Product[%s, $%.2f]", name, price);
    }
}