package com.pm.entity.state;

import com.pm.entity.enums.Denomination;

public interface State {

    void insertMoney(Denomination denomination);

    void selectProduct(String productId);

    void dispense();

    void cancel();
}