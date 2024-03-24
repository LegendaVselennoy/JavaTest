package com.example.mock;

import com.example.test.mock.Account;
import com.example.test.mock.AccountManager;
import com.example.test.mock.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestAccountServiceMockito {

    @Mock
    private AccountManager mockAccountManager;

    @Test
    public void testTransferOk() {
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);
        lenient()
                .when(mockAccountManager.findAccountForUser("1"))
                .thenReturn(senderAccount);
        lenient()
                .when(mockAccountManager.findAccountForUser("2"))
                .thenReturn(beneficiaryAccount);
        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("1", "2", 50);
        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }

}
