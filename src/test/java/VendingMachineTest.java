import com.vendingmachinesupport.*;
import com.vendingmachinesupport.exceptions.ChangeNotAvailableException;
import com.vendingmachinesupport.exceptions.NotFullyPaidException;
import com.vendingmachinesupport.exceptions.ProductNotAvailableException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineTest{

    private VendingMachine vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = VendingMachineFactory.createVendingMachine();
    }

    @Test
    public void testVendingMachineConfirmOrder() throws ProductNotAvailableException, ChangeNotAvailableException, NotFullyPaidException {
        List<Product> productList = new ArrayList<>();
        List<Coin> coins = new ArrayList<>();
        productList.add(Product.Pepsi);
        productList.add(Product.Coke);
        productList.add(Product.Soda);
        coins.add(Coin.NICKLE);
        coins.add(Coin.PENNY);
        coins.add(Coin.PENNY);
        vendingMachine.initialize(productList,coins);
        vendingMachine.selectProduct(Product.Pepsi);
        vendingMachine.addCoin(Coin.QUARTER);
        vendingMachine.addCoin(Coin.DIME);
        vendingMachine.enterDiscountCode(DiscountCode.CODE2);
        vendingMachine.confirmOrder();
        Assert.assertEquals(30,vendingMachine.getTotalSales());
    }

    @Test(expected = NotFullyPaidException.class)
    public void testVendingMachineNotFullyPaid() throws ProductNotAvailableException, ChangeNotAvailableException, NotFullyPaidException {
        List<Product> productList = new ArrayList<>();
        List<Coin> coins = new ArrayList<>();
        productList.add(Product.Pepsi);
        productList.add(Product.Coke);
        productList.add(Product.Soda);
        coins.add(Coin.NICKLE);
        coins.add(Coin.PENNY);
        coins.add(Coin.PENNY);
        vendingMachine.initialize(productList,coins);
        vendingMachine.selectProduct(Product.Pepsi);
        vendingMachine.addCoin(Coin.QUARTER);
        vendingMachine.enterDiscountCode(DiscountCode.CODE2);
        vendingMachine.confirmOrder();
    }

    @Test(expected = ChangeNotAvailableException.class)
    public void testVendingMachineChangeNotAvaiable() throws ProductNotAvailableException, ChangeNotAvailableException, NotFullyPaidException {
        List<Product> productList = new ArrayList<>();
        List<Coin> coins = new ArrayList<>();
        productList.add(Product.Pepsi);
        productList.add(Product.Coke);
        productList.add(Product.Soda);
        coins.add(Coin.PENNY);
        coins.add(Coin.PENNY);
        vendingMachine.initialize(productList,coins);
        vendingMachine.selectProduct(Product.Pepsi);
        vendingMachine.addCoin(Coin.QUARTER);
        vendingMachine.addCoin(Coin.DIME);
        vendingMachine.enterDiscountCode(DiscountCode.CODE2);
        vendingMachine.confirmOrder();
    }

    @Test(expected = ProductNotAvailableException.class)
    public void testVendingMachineProductNotAvailable() throws ProductNotAvailableException, ChangeNotAvailableException, NotFullyPaidException {
        List<Product> productList = new ArrayList<>();
        List<Coin> coins = new ArrayList<>();
        productList.add(Product.Coke);
        productList.add(Product.Soda);
        coins.add(Coin.NICKLE);
        coins.add(Coin.PENNY);
        coins.add(Coin.PENNY);
        vendingMachine.initialize(productList,coins);
        vendingMachine.selectProduct(Product.Pepsi);
        vendingMachine.addCoin(Coin.QUARTER);
        vendingMachine.addCoin(Coin.NICKLE);
        vendingMachine.enterDiscountCode(DiscountCode.CODE2);
        vendingMachine.confirmOrder();
    }

    @Test
    public void testVendingMachineCancelOrder() throws ProductNotAvailableException, ChangeNotAvailableException, NotFullyPaidException {
        List<Product> productList = new ArrayList<>();
        List<Coin> coins = new ArrayList<>();
        productList.add(Product.Pepsi);
        productList.add(Product.Coke);
        productList.add(Product.Soda);
        coins.add(Coin.NICKLE);
        coins.add(Coin.PENNY);
        coins.add(Coin.PENNY);
        vendingMachine.initialize(productList,coins);
        vendingMachine.selectProduct(Product.Pepsi);
        vendingMachine.addCoin(Coin.QUARTER);
        vendingMachine.addCoin(Coin.DIME);
        vendingMachine.enterDiscountCode(DiscountCode.CODE2);
        vendingMachine.cancelOrder();
        Assert.assertEquals(0,vendingMachine.getTotalSales());
    }

    @Test
    public void testVendingMachineReset() throws ProductNotAvailableException, ChangeNotAvailableException, NotFullyPaidException {
        List<Product> productList = new ArrayList<>();
        List<Coin> coins = new ArrayList<>();
        productList.add(Product.Pepsi);
        productList.add(Product.Coke);
        productList.add(Product.Soda);
        coins.add(Coin.NICKLE);
        coins.add(Coin.PENNY);
        coins.add(Coin.PENNY);
        vendingMachine.initialize(productList,coins);
        vendingMachine.selectProduct(Product.Pepsi);
        vendingMachine.addCoin(Coin.QUARTER);
        vendingMachine.addCoin(Coin.DIME);
        vendingMachine.enterDiscountCode(DiscountCode.CODE2);
        vendingMachine.confirmOrder();
        vendingMachine.reset();
        Assert.assertEquals(0,vendingMachine.getTotalSales());
    }


}
