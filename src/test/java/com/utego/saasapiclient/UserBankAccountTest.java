package com.utego.saasapiclient;

import com.utego.saasapiclient.data.common.Bank;
import com.utego.saasapiclient.data.common.BankAccount;
import com.utego.saasapiclient.data.common.TransactionSearchRequest;
import com.utego.saasapiclient.data.createaccount.CreateAccountRequest;
import com.utego.saasapiclient.data.createaccount.CreateAccountResponse;
import com.utego.saasapiclient.data.createuserbanktransaction.CreateUserBankTransactionRequest;
import com.utego.saasapiclient.data.getuserbanks.BankAccountResult;
import com.utego.saasapiclient.exceptions.NotFound;
import com.utego.saasapiclient.exceptions.SaasClientException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class UserBankAccountTest {
    SaasClientConfig config = new SaasClientConfig(
            System.getenv("SAAS_URL"),
            System.getenv("SAAS_ACCESS_KEY"),
            System.getenv("SAAS_SECRET_KEY")
    );

    SaasClient saasClient = new SaasClient(config);

    @Test
    public void user_bank_account_flow_show_works() throws SaasClientException, IOException {
        Long userId = saasClient.createUser().id;

        Assert.assertTrue(
                "User banks should be empty after user creation",
                saasClient.getUserBanks(userId).banks.isEmpty()
        );

        Assert.assertEquals("User bank balance should 0.0", saasClient.getUserBanksBalance(userId).balance, new BigDecimal("0.0"));

        Assert.assertTrue(
                "User transactions should be empty after user creation",
                saasClient
                        .getUserTransactions(userId, 1, 100, new TransactionSearchRequest(null, null, null, null, null))
                        .result
                        .isEmpty()
        );

        String iban1 = "KZ025282291669716348";
        String iban2 = "KZ031741616824137938";
        String iban3 = "GA6114432643997723311383988";

        Assert.assertThrows(
                "Create account should fail for non existing bank",
                NotFound.class, () -> saasClient.createAccount(userId, -1L, new CreateAccountRequest(new BankAccount(iban1))));

        List<Bank> banks = saasClient.getBanks(null, null, null).result;

        CreateAccountResponse account1 = saasClient.createAccount(userId, banks.get(1).id, new CreateAccountRequest(new BankAccount(iban1)));
        CreateAccountResponse account2 = saasClient.createAccount(userId, banks.get(1).id, new CreateAccountRequest(new BankAccount(iban2)));
        CreateAccountResponse account3 = saasClient.createAccount(userId, banks.get(2).id, new CreateAccountRequest(new BankAccount(iban3)));

        List<BankAccountResult> userBanksAccounts = saasClient.getUserBanks(userId).banks;

        Assert.assertTrue(
                "User banks should be compute by user accounts",
                containSameElements(
                        Arrays.asList(userBanksAccounts.stream().map(res -> res.bankId).distinct().toArray()),
                        Arrays.asList(banks.get(1).id, banks.get(2).id)
                )
        );

        Assert.assertTrue(
                "User accounts should contains all just created account",
                containSameElements(
                        Arrays.asList(userBanksAccounts.stream().map(res -> res.accountId).distinct().toArray()),
                        Arrays.asList(account1.accountId, account2.accountId, account3.accountId)
                )
        );

        Assert.assertEquals("User bank balance should 0.0", saasClient.getUserBanksBalance(userId).balance, new BigDecimal("0"));

        Assert.assertTrue(
                "User transactions should be empty before inserting any",
                saasClient
                        .getUserTransactions(userId, 1, 100, new TransactionSearchRequest(null, null, null, null, null))
                        .result
                        .isEmpty()
        );

        BigDecimal transactionValue1 = new BigDecimal("1000.10");
        BigDecimal transactionValue2 = new BigDecimal("1020.10");
        BigDecimal transactionValue3 = new BigDecimal("1040.10");
        BigDecimal transactionValue4 = new BigDecimal("1050.10");

        saasClient.createUserBankTransaction(userId, banks.get(1).id,
                new CreateUserBankTransactionRequest(transactionValue1, ZonedDateTime.now(), null, null));

        saasClient.createUserBankTransaction(userId, banks.get(2).id,
                new CreateUserBankTransactionRequest(transactionValue2, ZonedDateTime.now(), null, null));

        saasClient.createUserBankTransaction(userId, banks.get(3).id,
                new CreateUserBankTransactionRequest(transactionValue3, ZonedDateTime.now(), null, null));

        saasClient.createUserBankTransaction(userId, banks.get(3).id,
                new CreateUserBankTransactionRequest(transactionValue4, ZonedDateTime.now(), null, null));


        Assert.assertTrue(
                "After adding transaction to bank without user account, new account should be created",
                containSameElements(
                        Arrays.asList(saasClient.getUserBanks(userId).banks.stream().map(res -> res.bankId).distinct().toArray()),
                        Arrays.asList(banks.get(1).id, banks.get(2).id, banks.get(3).id)));

        Assert.assertEquals("User bank balance should be sum of all transactions",
                saasClient.getUserBanksBalance(userId).balance,
                transactionValue1.add(transactionValue2).add(transactionValue3).add(transactionValue4));

        Assert.assertTrue(
                "User transactions should contains all created transactions",
                containSameElements(
                        Arrays.asList(saasClient.getUserTransactions(userId, 1, 100,
                                new TransactionSearchRequest(null, null, null, null, null))
                                .result.stream().map(t -> t.value).distinct().toArray()
                        ),
                        Arrays.asList(transactionValue1, transactionValue2, transactionValue3, transactionValue4)));

        Assert.assertTrue(
                "User transactions should be filtered by amount correctly",
                containSameElements(
                        Arrays.asList(saasClient.getUserTransactions(userId, 1, 100,
                                new TransactionSearchRequest(null, new BigDecimal("1030.10"), null, null, null))
                                .result.stream().map(t -> t.value).distinct().toArray()
                        ),
                        Arrays.asList(transactionValue3, transactionValue4)));

        Assert.assertTrue(
                "User transactions should be filtered by amount correctly",
                containSameElements(
                        Arrays.asList(saasClient.getUserTransactions(userId, 1, 100,
                                new TransactionSearchRequest(null, null, new BigDecimal("1030.10"), null, null))
                                .result.stream().map(t -> t.value).distinct().toArray()
                        ),
                        Arrays.asList(transactionValue1, transactionValue2)));

        Assert.assertTrue(
                "User transactions should be filtered by amount correctly",
                containSameElements(
                        Arrays.asList(saasClient.getUserTransactions(userId, 1, 100,
                                new TransactionSearchRequest(null, null, null, transactionValue3, null))
                                .result.stream().map(t -> t.value).distinct().toArray()
                        ),
                        Arrays.asList(transactionValue3)));

        Assert.assertTrue(
                "User transactions should be filtered by bank correctly",
                containSameElements(
                        Arrays.asList(saasClient.getUserBankTransactions(userId, banks.get(2).id, 1, 100,
                                new TransactionSearchRequest(null, null, null, null, null))
                                .result.stream().map(t -> t.value).distinct().toArray()
                        ),
                        Arrays.asList(transactionValue2)));

        Assert.assertTrue(
                "User transactions should be filtered by bank correctly",
                containSameElements(
                        Arrays.asList(saasClient.getUserBankTransactions(userId, banks.get(3).id, 1, 100,
                                new TransactionSearchRequest(null, null, null, null, null))
                                .result.stream().map(t -> t.value).distinct().toArray()
                        ),
                        Arrays.asList(transactionValue3, transactionValue4)));

        Assert.assertTrue(
                "User transactions should be filtered by bank and amount correctly",
                containSameElements(
                        Arrays.asList(saasClient.getUserBankTransactions(userId, banks.get(3).id, 1, 100,
                                new TransactionSearchRequest(null, null, null, transactionValue4, null))
                                .result.stream().map(t -> t.value).distinct().toArray()
                        ),
                        Arrays.asList(transactionValue4)));

        saasClient.deleteAccount(userId, account3.accountId);


        Assert.assertTrue(
                "After deleting account, get user bank should not return it",
                containSameElements(
                        Arrays.asList(saasClient.getUserBanks(userId).banks.stream().map(res -> res.bankId).distinct().toArray()),
                        Arrays.asList(banks.get(1).id, banks.get(3).id)
                )
        );

        Assert.assertEquals("After deleting account, user balance should be compute correctly",
                saasClient.getUserBanksBalance(userId).balance,
                transactionValue1.add(transactionValue3).add(transactionValue4));

        Assert.assertTrue(
                "After deleting account, user transaction should be return correctly",
                containSameElements(
                        Arrays.asList(saasClient.getUserTransactions(userId, 1, 100,
                                new TransactionSearchRequest(null, null, null, null, null))
                                .result.stream().map(t -> t.value).distinct().toArray()
                        ),
                        Arrays.asList(transactionValue1, transactionValue3, transactionValue4)));


    }

    private <T> Boolean containSameElements(List<T> first, List<T> second) {
        return first.size() == second.size() && first.containsAll(second) && second.containsAll(first);
    }
}
