package com.pickle.models.soap;

import com.pickle.models.Test;
import com.pickle.models.OperationTestCase;

import java.util.Map;
import java.util.Objects;

public class SOAPTest implements Test {

    private final Map<String, SOAPOperationTestCase> soapRequests;

    public SOAPTest(Map<String, SOAPOperationTestCase> soapRequests) {
        this.soapRequests = soapRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SOAPTest soapTest)) return false;
        return Objects.equals(soapRequests, soapTest.soapRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(soapRequests);
    }

    @Override
    public Map<String, ? extends OperationTestCase> getTestCases() {
        return null;
    }

    @Override
    public String toString() {
        return "SOAPTest{" +
                "soapRequests=" + soapRequests +
                '}';
    }
}
