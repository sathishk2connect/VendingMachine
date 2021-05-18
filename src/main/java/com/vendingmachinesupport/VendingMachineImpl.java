package com.vendingmachinesupport;

import com.vendingmachinesupport.exceptions.ChangeNotAvailableException;
import com.vendingmachinesupport.exceptions.NotFullyPaidException;
import com.vendingmachinesupport.exceptions.ProductNotAvailableException;

import java.util.*;

public class VendingMachineImpl implements VendingMachine {
    private CoinStorage coinStorage;
    private List<Product> productStorage = new ArrayList<>();

    private Product selectedProduct;
    private List<Coin> addedCoins;
    private long addedCoinValue;
    private DiscountCode enteredDiscountCode;

    private long totalSales;

    public VendingMachineImpl(){
        productStorage = new ArrayList<>();
        coinStorage = new CoinStorage();
        addedCoins = new ArrayList<>();
        addedCoinValue = 0;
    }

    @Override
    public void initialize(List<Product> products, List<Coin> coins) {
        //Add products to the vending machine
        for(Product product: products)
            productStorage.add(product);

        // Add coins to the coin storage
        coinStorage.initialize(coins);
    }

    @Override
    public void selectProduct(Product product) {
        selectedProduct = product;
    }

    @Override
    public void addCoin(Coin coin) {
        addedCoins.add(coin);
        addedCoinValue += coin.getValue();
    }

    @Override
    public void enterDiscountCode(DiscountCode discountCode) {
        enteredDiscountCode = discountCode;
    }

    @Override
    public void cancelOrder() {
        selectedProduct = null;
        addedCoins.clear();
        addedCoinValue = 0;
        enteredDiscountCode = null;
    }

    @Override
    public void confirmOrder() throws ProductNotAvailableException, ChangeNotAvailableException, NotFullyPaidException {
        checkIfProductAvailable();
        long priceAfterDiscount = calculatePriceAfterDiscount();
        checkIfFullyPaid(priceAfterDiscount);
        long changeValue =  addedCoinValue - priceAfterDiscount;
        List<Coin> changeCoins = coinStorage.checkIfChangeAvailable(changeValue);
        updateSales(priceAfterDiscount);
        System.out.println("Product : " + selectedProduct.getProductName());
        StringBuilder jsonReceipt = new StringBuilder();
        jsonReceipt.append("{ qty : 1, discount : ")
                   .append(enteredDiscountCode.name())
                   .append(", totalprice : ")
                   .append( priceAfterDiscount )
                   .append(" }");
        System.out.println("Receipt :" + jsonReceipt);
        System.out.println("Remaining change : " + changeValue);
    }

    private void checkIfFullyPaid(long priceAfterDiscount) throws NotFullyPaidException {
        if(addedCoinValue < priceAfterDiscount)
            throw new NotFullyPaidException();
    }

    private void updateSales(long priceAfterDiscount) {
        totalSales += priceAfterDiscount;
    }

    private void checkIfProductAvailable() throws ProductNotAvailableException {
        if(!productStorage.contains(selectedProduct))
            throw new ProductNotAvailableException();
    }

    private long calculatePriceAfterDiscount() {
        return selectedProduct.getPrice() + enteredDiscountCode.getDiscount();
    }

    @Override
    public void reset() {
         productStorage.clear();
         coinStorage.clear();
         totalSales = 0;
    }

    public long getTotalSales() {
        return totalSales;
    }

}
