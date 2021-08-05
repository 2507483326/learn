package com.epat.strategies;

/**
 * @author 李涛
 * @date : 2021/7/31 9:20
 */
public interface  PayStrategy {
    boolean pay(int paymentAmount);
    void collectPaymentDetails();
}
