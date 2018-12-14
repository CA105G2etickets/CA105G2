package com.TRANSACTION_HISTORY.model;

import java.util.List;

public interface TransactionHistoryDAO_interface {
	
	public void insert(TransactionHistoryVO transactionHistory);
    public void update(TransactionHistoryVO transactionHistory);
    public void delete(String transactionHistoryNo);
    public TransactionHistoryVO findByPrimaryKey(String transactionHistoryNo);
    public List<TransactionHistoryVO> getAll();
    
}
