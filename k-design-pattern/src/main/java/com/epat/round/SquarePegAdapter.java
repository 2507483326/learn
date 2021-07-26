package com.epat.round;

/**
 * @author 李涛
 * @date : 2021/7/13 19:19
 */
public class SquarePegAdapter extends RoundPeg{

    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        double result;
        // Calculate a minimum circle radius, which can fit this peg.
        result = (Math.sqrt(Math.pow((peg.getWidth() / 2), 2) * 2));
        return result;
    }

}
