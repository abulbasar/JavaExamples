package com.example;

import com.example.exceptions.RecordNotException;
import com.example.models.BankAccount;
import com.example.services.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) // Use this annotation to process Mockito annotation processing
public class AccountServiceTest {

    @Mock
    AccountService accountService; // Use @Mock annotation to create mock instance

    @Test()
    public void loadAccount() throws SQLException, RecordNotException {
        final AccountService accountService = mock(AccountService.class);
        assertNull(accountService.loadAccount(0L)); // No argument matcher is found

        final BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountId(1L);
        bankAccount.setStatus("Active");
        bankAccount.setAmount(100.0);
        bankAccount.setName("Test Name");
        accountService.loadAccount(1L);

        when(accountService.loadAccount(1L)).thenReturn(bankAccount);
        doReturn(bankAccount).when(accountService).loadAccount(1L);



        assertNull(accountService.loadAccount(0L));
        assertNotNull(accountService.loadAccount(1L));
        when(accountService.loadAccount(anyLong())).thenReturn(bankAccount);

        verify(accountService).getAccountStatus(1L);
    }

    @Test
    public void saveAccount() {
    }

    @Test
    public void getAccountStatus() {
    }

    @Test
    public void loadAccounts() {
    }
}