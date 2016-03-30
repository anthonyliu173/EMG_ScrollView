package com.anthony.emq_scrollview;

import com.anthony.emq_scrollview.Objects.Transaction;

import java.util.ArrayList;

/**
 * Created by anthonyliu on 2016/3/30.
 */
public class DataHandler {

    /**
     * URL is where Transaction is loaded,
     * %d - the start id number in the first returned records
     * %d - total number of records will be returned
     * */
    private final String URL = "https://hook.io/syshen/infinite-list?startIndex=%d&num=%d";

    /**
     * numOfRecords is the number of records to be loaded for each request
     * */
    private final int numOfRecords = 2;

    /**
     * currentIndex is the index(id) of the last transaction object retrieved
     * */
    private int currentIndex;

    /**
     * transactionList is all transaction objects retrieved
     * */
    private ArrayList<Transaction> transactionList;


    private static DataHandler mInstance;

    private DataHandler(){

    }

    public synchronized static DataHandler getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new DataHandler();
            mInstance.init();
        }

        return mInstance;
    }

    private void init(){
        currentIndex = 0;
        transactionList = new ArrayList<>();
    }

    public int getCurrentIndex(){
        return currentIndex;
    }

    public String getUrl(){

        String url = String.format(URL, currentIndex, numOfRecords);
        currentIndex = currentIndex + numOfRecords;
        return url;

    }

    public ArrayList<Transaction> getTransactionList(){
        return transactionList;
    }

    public void addTransactionList(ArrayList<Transaction> transactions){
        transactionList.addAll(transactions);
    }

    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }

}
