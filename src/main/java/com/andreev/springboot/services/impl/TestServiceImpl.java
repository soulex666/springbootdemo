package com.andreev.springboot.services.impl;

import com.andreev.springboot.services.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    private String testData;
    private int testIntData;

    @Override
    public String getTestData() {
        return "Some test data " + this.testData;
    }

    @Override
    public int getTestDataInt() {
        return this.testIntData;
    }

    @Override
    public void setTestData(String testData) {
        this.testData = testData;
    }

    @Override
    public boolean auth(String email, String password) {
        return false;
    }

    @Override
    public void setTestDataInt(int testIntData) {
        this.testIntData = testIntData;
    }
}
