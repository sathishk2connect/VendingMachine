package com.vendingmachinesupport;

import com.vendingmachinesupport.exceptions.ChangeNotAvailableException;
import com.vendingmachinesupport.exceptions.NotFullyPaidException;
import com.vendingmachinesupport.exceptions.ProductNotAvailableException;

import java.util.List;

public interface VendingMachine {
    public void initialize(List<Product> products, List<Coin> coins);
    public void selectProduct(Product product);
    public void addCoin(Coin coin);
    public void enterDiscountCode(DiscountCode code);
    public void cancelOrder();
    public void confirmOrder() throws ProductNotAvailableException, ChangeNotAvailableException, NotFullyPaidException;
    public void reset();
    public long getTotalSales();
}
