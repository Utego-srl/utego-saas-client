package com.utego.saasapiclient;

import com.utego.saasapiclient.data.common.Bank;
import com.utego.saasapiclient.data.getcbibankproducts.CBIBankProduct;
import com.utego.saasapiclient.data.getcbibanks.Aspsp;
import com.utego.saasapiclient.data.getcbibanks.AspspProduct;
import com.utego.saasapiclient.exceptions.SaasClientException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CbiBanksTest {

    SaasClientConfig config = new SaasClientConfig(
            System.getenv("SAAS_URL"),
            System.getenv("SAAS_ACCESS_KEY"),
            System.getenv("SAAS_SECRET_KEY")
    );

    SaasClient saasClient = new SaasClient(config);

    @Test
    public void cbi_bank_flow_show_works() throws SaasClientException, IOException {
        Map<String, Aspsp> cbiBanks = saasClient.getCBIBanks();

        Assert.assertTrue(
                "CbiBanks should not be empty",
                cbiBanks.size() > 0
        );

        String cbiBankKey = "CR Trento";
        Aspsp cbiBank = cbiBanks.get(cbiBankKey);

        Bank bank = saasClient.getBanks(null, null, null).result
                .stream().filter(b -> b.name.equals(cbiBankKey)).findAny().get();

        List<CBIBankProduct> cbiProducts = saasClient.getCBIBankProducts(bank.id);

        Assert.assertArrayEquals(
                "Product list should be the same for cbi banks",
                cbiProducts.stream().map(cbi -> new AspspProduct(cbi.aspspProductCode, cbi.aspspProductDescription, cbi.aspspProductUuid)).toArray(),
                cbiBank.aspspProductsList.toArray()
        );

    }

}
