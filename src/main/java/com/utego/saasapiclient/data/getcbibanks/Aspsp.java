package com.utego.saasapiclient.data.getcbibanks;

import java.util.List;
import java.util.Objects;

public class Aspsp {
    public String id;
    public String aspspCode;
    public String businessName;
    public String status;
    public List<AspspAttribute> attribute; //can be null
    public String creationDate;
    public String updatedDate;
    public String aspspPublicUrl; //can be null
    public List<AspspProduct> aspspProductsList;

    public Aspsp(String id,
                 String aspspCode,
                 String businessName,
                 String status,
                 List<AspspAttribute> attribute,
                 String creationDate,
                 String updatedDate,
                 String aspspPublicUrl,
                 List<AspspProduct> aspspProductsList) {
        this.id = id;
        this.aspspCode = aspspCode;
        this.businessName = businessName;
        this.status = status;
        this.attribute = attribute;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
        this.aspspPublicUrl = aspspPublicUrl;
        this.aspspProductsList = aspspProductsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aspsp aspsp = (Aspsp) o;
        return id.equals(aspsp.id) && aspspCode.equals(aspsp.aspspCode) && businessName.equals(aspsp.businessName) && status.equals(aspsp.status) && Objects.equals(attribute, aspsp.attribute) && creationDate.equals(aspsp.creationDate) && updatedDate.equals(aspsp.updatedDate) && Objects.equals(aspspPublicUrl, aspsp.aspspPublicUrl) && aspspProductsList.equals(aspsp.aspspProductsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aspspCode, businessName, status, attribute, creationDate, updatedDate, aspspPublicUrl, aspspProductsList);
    }
}
