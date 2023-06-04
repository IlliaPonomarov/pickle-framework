package com.pickle.services.parsers;

import com.pickle.models.OperationTestCase;

import java.util.Map;
import java.util.UUID;

public interface EndpointParser {
    Map<UUID,? extends OperationTestCase> createTestCase();
}
