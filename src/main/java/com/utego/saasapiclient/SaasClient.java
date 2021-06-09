package com.utego.saasapiclient;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.utego.saasapiclient.data.common.*;
import com.utego.saasapiclient.data.createaccount.CreateAccountRequest;
import com.utego.saasapiclient.data.createaccount.CreateAccountResponse;
import com.utego.saasapiclient.data.createbank.CreateBankRequest;
import com.utego.saasapiclient.data.createuser.CreateUserResponse;
import com.utego.saasapiclient.data.createuserbanktransaction.CreateUserBankTransactionRequest;
import com.utego.saasapiclient.data.getbanks.GetBanksResponse;
import com.utego.saasapiclient.data.getcbibankproducts.CBIBankProduct;
import com.utego.saasapiclient.data.getcbibanks.Aspsp;
import com.utego.saasapiclient.data.getuserbanks.GetUserBanksResponse;
import com.utego.saasapiclient.data.getuserbanksbalance.GetUserBanksBalanceResponse;
import com.utego.saasapiclient.data.login.LoginRequest;
import com.utego.saasapiclient.data.login.LoginResponse;
import com.utego.saasapiclient.data.loginrefreshtoken.LoginRefreshTokenRequest;
import com.utego.saasapiclient.data.loginrefreshtoken.LoginRefreshTokenResponse;
import com.utego.saasapiclient.data.readconflicts.ReadConflictsResponse;
import com.utego.saasapiclient.data.retrieveadapterscastatus.RetrieveAdapterSCAStatusResponse;
import com.utego.saasapiclient.data.retrieveoccurringrefresh.RetrieveOccurringRefreshResponse;
import com.utego.saasapiclient.data.solvescachallenge.SolveSCAChallengeRequest;
import com.utego.saasapiclient.data.startsca.*;
import com.utego.saasapiclient.exceptions.*;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SaasClient {

    protected SaasClientConfig config;
    protected OkHttpClient client;
    protected Gson gson;

    public SaasClient(SaasClientConfig config) {
        this.config = config;
        this.client = new OkHttpClient();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        gsonBuilder.registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeDeserializer());
        gsonBuilder.registerTypeAdapter(Instant.class, new InstantSerializer());
        gsonBuilder.registerTypeAdapter(Instant.class, new InstantDeserializer());
        gsonBuilder.registerTypeAdapter(SCAKind.class, new SCAKindDeserializer());
        gson = gsonBuilder.create();

    }

    public LoginResponse login() throws SaasClientException, IOException {
        LoginRequest requestData = new LoginRequest(config.accessKey, config.secretKey);

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/auth/login")
                .post(RequestBody.create(gson.toJson(requestData), MediaType.parse("application/json")))
                .build();

        return handleResponse(request, LoginResponse.class);
    }


    public LoginRefreshTokenResponse loginRefreshToken(String token) throws SaasClientException, IOException {
        LoginRefreshTokenRequest requestData = new LoginRefreshTokenRequest(token);

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/auth/refresh")
                .post(RequestBody.create(gson.toJson(requestData), MediaType.parse("application/json")))
                .build();

        return handleResponse(request, LoginRefreshTokenResponse.class);
    }


    public CreateUserResponse createUser() throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users")
                .method("POST", RequestBody.create("", MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, CreateUserResponse.class);
    }

    public Bank createBank(CreateBankRequest createBankRequest) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/banks")
                .method("POST", RequestBody.create(gson.toJson(createBankRequest), MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, Bank.class);
    }

    public GetBanksResponse getBanks(String query, Integer page, Integer size) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        ArrayList<String> params = new ArrayList<>();

        if (query != null) {
            params.add("name=" + URLEncoder.encode(query, "UTF-8"));
        }
        if (page != null) {
            params.add("page=" + page);
        }
        if (size != null) {
            params.add("size=" + size);
        }
        String paramsQuery;
        if (!params.isEmpty()) paramsQuery = params.stream().collect(Collectors.joining("&", "?", ""));
        else paramsQuery = "";

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/banks" + paramsQuery)
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();


        return handleResponse(request, GetBanksResponse.class);
    }

    public Integer countBanks() throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/banks/count")
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, Integer.class);
    }

    public BankEntry findBank(Long id) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/banks/" + id.toString())
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, BankEntry.class);
    }

    public BankEntry updateBank(BankEntry bank) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/banks/" + bank.id.toString())
                .method("PATCH", RequestBody.create(gson.toJson(bank), MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, BankEntry.class);
    }

    public void deleteBank(Long id) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/banks/" + id.toString())
                .method("DELETE", null)
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        handleResponse(request, Void.class);
    }

    public Map<String, Aspsp> getCBIBanks() throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/cbi-banks")
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        Type cbiResponseType = new TypeToken<Map<String, Aspsp>>() {
        }.getType();
        return handleResponse(request, cbiResponseType);
    }

    public List<CBIBankProduct> getCBIBankProducts(Long id) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/cbi-banks/" + id.toString() + "/products")
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        Type cbiResponseType = new TypeToken<List<CBIBankProduct>>() {
        }.getType();
        return handleResponse(request, cbiResponseType);
    }

    public CreateAccountResponse createAccount(Long userId, Long bankId, CreateAccountRequest createAccountRequest) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/" + userId.toString() + "/banks/" + bankId)
                .method("POST", RequestBody.create(gson.toJson(createAccountRequest), MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, CreateAccountResponse.class);
    }

    public void deleteAccount(Long userId, Long accountId) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/" + userId.toString() + "/accounts/" + accountId)
                .method("DELETE", null)
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        handleResponse(request, Void.class);
    }

    public GetUserBanksResponse getUserBanks(Long userId) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/" + userId.toString() + "/banks")
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, GetUserBanksResponse.class);
    }

    public GetUserBanksBalanceResponse getUserBanksBalance(Long userId) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/" + userId.toString() + "/banks/balance")
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, GetUserBanksBalanceResponse.class);
    }

    public PaginedTransactionResult getUserTransactions(Long userId, Integer page, Integer size, TransactionSearchRequest transactionSearchRequest) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        ArrayList<String> params = new ArrayList<>();
        params.add("page=" + page.toString());
        params.add("name=" + size.toString());

        if (transactionSearchRequest.description != null) {
            params.add("description=" + URLEncoder.encode(transactionSearchRequest.description, "UTF-8"));
        }
        if (transactionSearchRequest.lessThanAmount != null) {
            params.add("lessThanAmount=" + transactionSearchRequest.lessThanAmount.toString());
        }
        if (transactionSearchRequest.moreThanAmount != null) {
            params.add("moreThanAmount=" + transactionSearchRequest.moreThanAmount.toString());
        }
        if (transactionSearchRequest.exactAmount != null) {
            params.add("exactAmount=" + transactionSearchRequest.exactAmount.toString());
        }
        if (transactionSearchRequest.descSort != null) {
            params.add("sortDesc=" + transactionSearchRequest.descSort.toString());
        }
        String paramsQuery;
        if (!params.isEmpty()) paramsQuery = params.stream().collect(Collectors.joining("&", "?", ""));
        else paramsQuery = "";

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/" + userId.toString() + "/transactions" + paramsQuery)
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, PaginedTransactionResult.class);
    }

    public PaginedTransactionResult getUserBankTransactions(Long userId, Long bankId, Integer page, Integer size, TransactionSearchRequest transactionSearchRequest) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        ArrayList<String> params = new ArrayList<>();
        params.add("page=" + page.toString());
        params.add("name=" + size.toString());

        if (transactionSearchRequest.description != null) {
            params.add("description=" + URLEncoder.encode(transactionSearchRequest.description, "UTF-8"));
        }
        if (transactionSearchRequest.lessThanAmount != null) {
            params.add("lessThanAmount=" + transactionSearchRequest.lessThanAmount.toString());
        }
        if (transactionSearchRequest.moreThanAmount != null) {
            params.add("moreThanAmount=" + transactionSearchRequest.moreThanAmount.toString());
        }
        if (transactionSearchRequest.exactAmount != null) {
            params.add("exactAmount=" + transactionSearchRequest.exactAmount.toString());
        }
        if (transactionSearchRequest.descSort != null) {
            params.add("sortDesc=" + transactionSearchRequest.descSort.toString());
        }
        String paramsQuery;
        if (!params.isEmpty()) paramsQuery = params.stream().collect(Collectors.joining("&", "?", ""));
        else paramsQuery = "";

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/" + userId.toString() + "/banks/" + bankId + "/transactions" + paramsQuery)
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, PaginedTransactionResult.class);
    }

    public Transaction createUserBankTransaction(Long userId, Long bankId, CreateUserBankTransactionRequest createUserBankTransactionRequest) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/" + userId.toString() + "/banks/" + bankId.toString() + "/transactions")
                .method("POST", RequestBody.create(gson.toJson(createUserBankTransactionRequest), MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, Transaction.class);
    }

    public StartSCAResponse startSCA(Long userId, Long bankId, JsonObject requestData) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/" + userId.toString() + "/banks/" + bankId.toString() + "/sca")
                .method("POST", RequestBody.create(requestData.toString(), MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, StartSCAResponse.class);
    }

    public void solveSCAChallenge(Long userId, Long bankId, SolveSCAChallengeRequest requestData) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();
        
        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/" + userId.toString() + "/banks/" + bankId.toString() + "/sca/challenge")
                .method("POST", RequestBody.create(gson.toJson(requestData), MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        handleResponse(request, Void.class);
    }

    public ReadConflictsResponse readConflicts(Long userId, Long bankId, JsonObject requestData) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/" + userId.toString() + "/banks/" + bankId.toString() + "/sca/conflicts/read")
                .method("POST", RequestBody.create(requestData.toString(), MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, ReadConflictsResponse.class);
    }

    public RetrieveAdapterSCAStatusResponse retrieveAdapterSCAStatus(Long scaId) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/sca/" + scaId.toString() + "/status")
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, RetrieveAdapterSCAStatusResponse.class);
    }

    public RetrieveOccurringRefreshResponse retrieveOccurringRefresh(Long userId) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/" + userId.toString() + "/refresh")
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        return handleResponse(request, RetrieveOccurringRefreshResponse.class);
    }

    public void refreshUserBanks(Long userId) throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/" + userId.toString() + "/banks/refresh")
                .method("POST", RequestBody.create("{}", MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        handleResponse(request, Void.class);
    }

    public void refreshUsersBanks() throws SaasClientException, IOException {
        LoginResponse loginResponse = login();

        Request request = new Request.Builder()
                .url(config.url + "/api/v1/users/refresh")
                .method("POST", RequestBody.create("{}", MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + loginResponse.accessToken)
                .build();

        handleResponse(request, Void.class);
    }

    private <T> T handleResponse(Request request, Type typeOfT) throws SaasClientException, IOException {
        Response response = client.newCall(request).execute();
        int code = response.code();
        if (code >= 200 && code <= 205) {

            if (typeOfT == Void.class) {
                return (T) null;
            } else {
                String body = response.body().string();
                try {
                    T responseData = gson.fromJson(body, typeOfT);
                    if (responseData == null) {
                        throw new ParseJsonError("Unable to create proper response object");
                    }
                    return responseData;
                } catch (JsonParseException e) {
                    throw new ParseJsonError(e.getMessage() + " during object: " + body);
                }
            }
        } else if (code == 400) {
            throw new BadRequest();
        } else if (code == 401) {
            throw new Unauthorized();
        } else if (code == 403) {
            throw new Forbidden();
        } else if (code == 404) {
            throw new NotFound();
        } else {
            throw new UnknownApiResponse(response);
        }
    }

    private final DateTimeFormatter zonedDateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    private final DateTimeFormatter offsetDateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private final DateTimeFormatter instantFormatter = DateTimeFormatter.ISO_INSTANT;

    class ZonedDateTimeSerializer implements JsonSerializer<ZonedDateTime> {
        @Override
        public JsonElement serialize(ZonedDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(zonedDateTimeFormatter.format(localDateTime));
        }
    }

    class ZonedDateTimeDeserializer implements JsonDeserializer<ZonedDateTime> {
        @Override
        public ZonedDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return ZonedDateTime.parse(jsonElement.getAsString(), zonedDateTimeFormatter);
        }
    }

    class OffsetDateTimeSerializer implements JsonSerializer<OffsetDateTime> {
        @Override
        public JsonElement serialize(OffsetDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(offsetDateTimeFormatter.format(localDateTime));
        }
    }

    class OffsetDateTimeDeserializer implements JsonDeserializer<OffsetDateTime> {
        @Override
        public OffsetDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return OffsetDateTime.parse(jsonElement.getAsString(), offsetDateTimeFormatter);
        }
    }

    class InstantSerializer implements JsonSerializer<Instant> {
        @Override
        public JsonElement serialize(Instant instant, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(instantFormatter.format(instant));
        }
    }

    class InstantDeserializer implements JsonDeserializer<Instant> {
        @Override
        public Instant deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return Instant.parse(jsonElement.getAsString());
        }
    }

    class SCAKindDeserializer implements JsonDeserializer<SCAKind> {

        @Override
        public SCAKind deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject json = jsonElement.getAsJsonObject();

            String kind = json.getAsJsonPrimitive("kind").getAsString();
            if (kind.equals("decoupled")) {
                return new SCAKindDecoupled();
            } else if (kind.equals("decoupled-message")) {
                String message = json.getAsJsonPrimitive("message").getAsString();
                return new SCAKindDecoupledMessage(message);
            } else if (kind.equals("oauth2")) {
                String url = json.getAsJsonPrimitive("url").getAsString();
                String param = json.getAsJsonPrimitive("param").getAsString();
                return new SCAKindOAuth2(url, param);
            } else if (kind.equals("picture-challenge")) {
                String picture = json.getAsJsonPrimitive("picture").getAsString();
                ByteBuffer pictureBytes = ByteBuffer.wrap(Base64.getDecoder().decode(picture));
                return new SCAKindPictureChallenge(pictureBytes);
            } else if (kind.equals("picture-link-challenge")) {
                String pictureUrl = json.getAsJsonPrimitive("picture-url").getAsString();
                return new SCAKindPictureLinkChallenge(pictureUrl);
            } else if (kind.equals("push")) {
                return new SCAKindPush();
            } else if (kind.equals("redirect")) {
                String url = json.getAsJsonPrimitive("url").getAsString();
                return new SCAKindRedirect(url);
            } else if (kind.equals("sms")) {
                JsonPrimitive phoneNumberElem = json.getAsJsonPrimitive("phoneNumber");
                String phoneNumber = null;
                if (phoneNumberElem != null) {
                    phoneNumber = phoneNumberElem.getAsString();
                }
                return new SCAKindSMS(phoneNumber);
            } else {
                throw new JsonParseException("Unhandled sca kind: \"" + kind + "\";");
            }

        }
    }
}
