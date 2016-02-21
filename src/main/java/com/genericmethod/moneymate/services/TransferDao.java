package com.genericmethod.moneymate.services;

import com.genericmethod.moneymate.model.Transfer;

public interface TransferDao {
    void transfer(Transfer moneyAmount);
}
