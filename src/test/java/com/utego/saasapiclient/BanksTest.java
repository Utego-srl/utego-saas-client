package com.utego.saasapiclient;

import com.utego.saasapiclient.data.common.Adapter;
import com.utego.saasapiclient.data.common.Bank;
import com.utego.saasapiclient.data.common.BankEntry;
import com.utego.saasapiclient.data.createbank.CreateBankRequest;
import com.utego.saasapiclient.data.getbanks.GetBanksResponse;
import com.utego.saasapiclient.exceptions.NotFound;
import com.utego.saasapiclient.exceptions.SaasClientException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

public class BanksTest {

    SaasClientConfig config = new SaasClientConfig(
            System.getenv("SAAS_URL"),
            System.getenv("SAAS_ACCESS_KEY"),
            System.getenv("SAAS_SECRET_KEY")
    );

    SaasClient saasClient = new SaasClient(config);

    @Test
    public void bank_flow_show_works() throws SaasClientException, IOException {
        Integer banksCountBeforeInsert = saasClient.countBanks();
        Assert.assertTrue("Count banks should be positive", banksCountBeforeInsert > 0);

        Long banksCountBeforeInsert2 = saasClient.getBanks(null, null, null).count;
        Assert.assertEquals(
                "Banks count should be equal",
                (long) banksCountBeforeInsert, (long) banksCountBeforeInsert2);

        Assert.assertThrows(
                "Not found should be raised for non-existing bank",
                NotFound.class, () -> saasClient.findBank(-1L));

        String randomString = UUID.randomUUID().toString();
        Bank bank1 = saasClient.createBank(new CreateBankRequest(randomString + "-1", null));
        Bank bank2 = saasClient.createBank(new CreateBankRequest(randomString + "-2", Adapter.Auriga));

        Assert.assertEquals(
                "Find banks should return just created bank",
                bank1.name, saasClient.findBank(bank1.id).name);

        Assert.assertEquals(
                "Find banks should return just created bank",
                bank2.name, saasClient.findBank(bank2.id).name);

        GetBanksResponse getBanksResponse1 = saasClient.getBanks(randomString, null, null);
        Assert.assertEquals(
                "Get banks should returns just created banks by query string",
                2L, (long) getBanksResponse1.count);
        Assert.assertEquals(
                "Get banks should returns just created banks by query string",
                2L, (long) getBanksResponse1.result.size());

        Assert.assertTrue(
                "Get banks should contains just created bank",
            getBanksResponse1.result.stream().anyMatch(bank -> bank.id.equals(bank1.id)));

        Assert.assertTrue(
                "Get banks should contains just created bank",
                getBanksResponse1.result.stream().anyMatch(bank -> bank.id.equals(bank2.id)));

        GetBanksResponse getBanksResponse2 = saasClient.getBanks(randomString,
                1, 1);
        Assert.assertEquals(
                "Get banks with page 1 size 1 should return total count of 2 but only 1 bank",
                2L, (long) getBanksResponse2.count);
        Assert.assertEquals(
                "Get banks with page 1 size 1 should return total count of 2 but only 1 bank",
                1L, (long) getBanksResponse2.result.size());

        GetBanksResponse getBanksResponse3 = saasClient.getBanks(randomString,
                5, 1);
        Assert.assertEquals(
                "Get banks with page 5 size 1 should return total count of 2 but no banks in result",
                2L, (long) getBanksResponse3.count);
        Assert.assertEquals(
                "Get banks with page 5 size 1 should return total count of 2 but no banks in result",
                0L, (long) getBanksResponse3.result.size());

        Assert.assertThrows(
                "Not found should be raised for updating non-existing bank",
                NotFound.class, () -> saasClient.updateBank(new BankEntry(-1L, "test", "test", null, true)));

        String newBank1Name = randomString + "new-1";

        saasClient.updateBank(new BankEntry(bank1.id, newBank1Name, null, null, true));

        Assert.assertEquals(
                "Find should returns updated bank",
                newBank1Name, saasClient.findBank(bank1.id).name);

        saasClient.deleteBank(bank2.id);

        Assert.assertThrows(
                "Bank should not be reachable after delete",
                NotFound.class, () -> saasClient.findBank(bank2.id));

        Assert.assertFalse(
                "Bank should not be reachable after delete",
                saasClient.getBanks(randomString, null, null).result.stream().anyMatch(bank -> bank.id.equals(bank2.id)));

        Assert.assertTrue(
                "Bank should not be reachable by name substring",
                saasClient.getBanks(bank1.name.substring(3, 12), null, null).result.stream().anyMatch(bank -> bank.id.equals(bank1.id)));

    }
}