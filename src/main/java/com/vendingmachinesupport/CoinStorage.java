package com.vendingmachinesupport;

import com.vendingmachinesupport.exceptions.ChangeNotAvailableException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinStorage {
    private Map<Coin, Integer> coins = new HashMap<>();

    public void initialize(List<Coin> coins) {
        for(Coin coin: coins)
          addToStorage(coin);
    }


    public List<Coin> checkIfChangeAvailable(long changeValue) throws ChangeNotAvailableException {
        List<Coin> change = new ArrayList<Coin>();
        while(changeValue > 0){
            if(changeValue >= Coin.QUARTER.getValue() && hasCoin(Coin.QUARTER)){
                change.add(Coin.QUARTER);
                changeValue -= Coin.QUARTER.getValue();
                deductFromStorage(Coin.QUARTER);
                continue;
            } else if(changeValue >= Coin.DIME.getValue() && hasCoin(Coin.DIME)){
                change.add(Coin.DIME);
                changeValue -= Coin.DIME.getValue();
                deductFromStorage(Coin.DIME);
                continue;
            } else if(changeValue >= Coin.NICKLE.getValue() && hasCoin(Coin.NICKLE)){
                change.add(Coin.NICKLE);
                changeValue -= Coin.NICKLE.getValue();
                deductFromStorage(Coin.NICKLE);
                continue;
            } else if(changeValue >= Coin.PENNY.getValue() && hasCoin(Coin.PENNY)){
                change.add(Coin.PENNY);
                changeValue -= Coin.PENNY.getValue();
                deductFromStorage(Coin.PENNY);
                continue;
            } else {
                // Sufficient change is not available. Return back change to the coin collection
                returnToStorage(change);
                throw new ChangeNotAvailableException();
            }
        }
        return change;
    }


    private boolean hasCoin(Coin coin){
       return (coins.containsKey(coin) && coins.get(coin)>0);
    }

    private void addToStorage(Coin coin) {
        if (!coins.containsKey(coin)) {
            coins.put(coin, 1);
        } else {
            coins.put(coin, coins.get(coin) - 1);
        }
    }

    private void deductFromStorage(Coin coin) {
        coins.put(coin, coins.get(coin)-1);
    }

    private void returnToStorage(List<Coin> change) {
        for(Coin coin : change){
            coins.put(coin, coins.get(coin)+1);
        }
    }

    public void clear() {
        coins.clear();
    }
}

