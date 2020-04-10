package net.imashamed.vsim;

/*
 *  This file is part of vending-simulator.
 *
 *  vending-simulator is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  vending-simulator is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with vending-simulator.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Objects;

/**
 * A Product object to be used as merchandise in {@link VendingMachine}
 * @author nathan
 *         created on 2017-02-18.
 */
public class Product {
    private final double PRICE;
    private final String NAME;

    /**
     * Constructs a new product with a given NAME and PRICE
     * @param name the NAME of the product - case sensitive
     * @param price the dollar PRICE of the product
     */
    public Product(String name, double price) {
        this.NAME = name;
        this.PRICE = price;
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
            if ((product.getPrice() == this.PRICE) &&
                    (product.getName().equals(this.NAME))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the dollar PRICE of the product
     */
    public double getPrice() {
        return this.PRICE;
    }

    /**
     * @return the NAME of the product
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * @return the hash code of the Product
     */
    public int hashCode() {
        return Objects.hash(NAME, PRICE);
    }

    /**
     * @return a string representation of the product in the format Product[NAME, PRICE]
     */
    public String toString() {
        return String.format("Product[%s, $%.2f]", NAME, PRICE);
    }
}