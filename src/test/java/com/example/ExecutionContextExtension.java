package com.example;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.IOException;
import java.util.Properties;

public class ExecutionContextExtension implements ExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        Properties properties = new Properties();
        String executionContext = "";

        try {
            properties.load(ExecutionContextExtension.class.getClassLoader()
                    .getResourceAsStream("context.properties"));
            executionContext = properties.getProperty("context");
            if (!"regular".equalsIgnoreCase(executionContext) && !"low".equalsIgnoreCase(executionContext)) {
                return ConditionEvaluationResult.disabled("Test disabled outside regular and low contexts");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ConditionEvaluationResult.enabled("Test enabled on the " + executionContext + " context");
    }
}
