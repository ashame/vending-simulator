package net.imashamed.vendingsimulator;

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