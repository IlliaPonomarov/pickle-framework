package com.pickle.models;

public interface OperationTestCase {

    OperationTestCase getTestCases();
    String getRequestName();
    void setRequestName(String requestName);
    Object getId();

}
