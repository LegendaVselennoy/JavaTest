package com.example;

import com.example.test.ConnectionManager;
import com.example.test.passengers.PassengerDao;
import com.example.test.passengers.PassengerDaoImpl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class DataAccessObjectParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter()
                .getType()
                .equals(PassengerDao.class);
    }
    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return new PassengerDaoImpl(ConnectionManager.getConnection());
    }
}
