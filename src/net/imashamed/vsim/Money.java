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

/**
 * An enumerated type containing all available denominations of money to be used
 * @author nathan
 *         created on 2017-02-18.
 */
public enum Money {
    LOONIE(1.00),
    TOONIE(2.00),
    FIVE(5.00),
    TEN(10.00),
    TWENTY(20.00);

    private final double value;

    Money(double value) {
        this.value = value;
    }

    /**
     * @return the dollar value
     */
    public double value() {
        return this.value;
    }
}