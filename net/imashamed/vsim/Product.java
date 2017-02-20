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
 * A net.imashamed.vsim.Product object to be used as merchandise in {@link VendingMachine}
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
     * @return the hash code of the net.imashamed.vsim.Product
     */
    public int hashCode() {
        return Objects.hash(name, price);
    }

    /**
     * @return a string representation of the product in the format net.imashamed.vsim.Product[name, price]
     */
    public String toString() {
        return String.format("net.imashamed.vsim.Product[%s, $%.2f]", name, price);
    }
}