package com.pickle.models;
import java.util.Map;
public interface Test{

    Map<String, ? extends TestCase> getTestCases();
}
