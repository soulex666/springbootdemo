package com.andreev.springboot.services;

public interface TestService {

    String getTestData();

    int getTestDataInt();

    void setTestData(String testData);

    boolean auth(String email, String password);

    void setTestDataInt(int i);
}
