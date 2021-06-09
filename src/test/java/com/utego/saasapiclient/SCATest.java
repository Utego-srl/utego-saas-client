package com.utego.saasapiclient;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.utego.saasapiclient.data.common.Bank;
import com.utego.saasapiclient.data.solvescachallenge.SolveSCAChallengeRequest;
import com.utego.saasapiclient.data.startsca.StartSCAResponse;
import com.utego.saasapiclient.exceptions.SaasClientException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.utego.saasapiclient.data.common.Adapter.TokenIOHTTP;

public class SCATest {

    SaasClientConfig config = new SaasClientConfig(
            System.getenv("SAAS_URL"),
            System.getenv("SAAS_ACCESS_KEY"),
            System.getenv("SAAS_SECRET_KEY")
    );

    SaasClient saasClient = new SaasClient(config);

    @Test
    public void sca_flow_show_works() throws SaasClientException, IOException {
        Long userId = saasClient.createUser().id;
        List<Bank> banks = saasClient.getBanks(null, null, null).result;
        Bank tokenIoHttpBank = banks.stream().filter(b -> TokenIOHTTP.equals(b.adapterName)).findFirst().get();

        JsonObject scaInfo = JsonParser.parseString("{\"psuId\": \"1\"}").getAsJsonObject();

        Assert.assertFalse(
            "Read conflicts should returns false before any sca operation",
                saasClient.readConflicts(userId, tokenIoHttpBank.id, scaInfo).isConflicted);

        Assert.assertThrows(
                "Solve sca should throws exception for incorrect id",
                SaasClientException.class,
                () -> saasClient.solveSCAChallenge(userId, tokenIoHttpBank.id, new SolveSCAChallengeRequest(-1L, "test"))
        );

        Assert.assertNull(
            "Status for unknown sca should be null",
                saasClient.retrieveAdapterSCAStatus(-1L).status
        );

        StartSCAResponse sca = saasClient.startSCA(userId, tokenIoHttpBank.id, scaInfo);

        Assert.assertNotNull(
                "Status for known sca should not be null",
                saasClient.retrieveAdapterSCAStatus(sca.scaId).status
        );

        Assert.assertTrue(
                "Read conflicts should returns true after same sca operation",
                saasClient.readConflicts(userId, tokenIoHttpBank.id, scaInfo).isConflicted);

        saasClient.solveSCAChallenge(userId, tokenIoHttpBank.id, new SolveSCAChallengeRequest(sca.scaId, "test"));

        Assert.assertNull(
                "Retrieve occurring refresh should be empty if refresh is not in progress",
                saasClient.retrieveOccurringRefresh(sca.scaId).startDate
        );

        saasClient.refreshUserBanks(userId);

    }
}
