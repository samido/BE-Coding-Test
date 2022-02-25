package za.co.spandigital.be.coding.service;

import za.co.spandigital.be.coding.utils.Constants;
/**
 * @author Sam Rabophala - sam.rabophala@gmail.com
 * @version 0.0-SNAPSHOT
 * @since JDK1.8
 */
public class Calculator implements PointsCalculator {
    @Override
    public int winPoints(int currentPoints) {
        return currentPoints + Constants.THREE;
    }
}
