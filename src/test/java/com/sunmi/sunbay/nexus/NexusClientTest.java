package com.sunmi.sunbay.nexus;

import com.sunmi.sunbay.nexus.exception.SunbayBusinessException;
import com.sunmi.sunbay.nexus.exception.SunbayNetworkException;
import com.sunmi.sunbay.nexus.model.common.Amount;
import com.sunmi.sunbay.nexus.model.common.PaymentMethodInfo;
import com.sunmi.sunbay.nexus.model.request.*;
import com.sunmi.sunbay.nexus.model.response.*;
import com.sunmi.sunbay.nexus.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * SunbayClient business test
 * Note: These tests require a real API connection
 *
 * @since 2025-12-10
 */
@Slf4j
public class NexusClientTest {

    private NexusClient client;

    @Before
    public void setUp() {
        // Initialize client with test credentials
        client = new NexusClient.Builder()
                .apiKey("o5zoxq8y9berp71ngrr6p85vjj5xsgg7")
                .baseUrl("https://open.sunbay.dev")
                .connectTimeout(30000)
                .readTimeout(60000)
                .build();
    }

    /**
     * Test sale transaction
     * Note: This test requires real API connection, mark as @Ignore if API is not available
     */
    @Test
    @Ignore("Requires real API connection")
    public void testSale() {
        // Build amount with all fields
        Amount amount = new Amount();
        amount.setOrderAmount(100.00);
        amount.setPricingCurrency("USD");

        // Build sale request
        SaleRequest request = new SaleRequest();
        request.setAppId("test_sm6par3xf4d3tkum");
        request.setMerchantId("M1254947005");
        request.setReferenceOrderId("ORDER20231119001");
        request.setTransactionRequestId("ORDER20231119001" + System.currentTimeMillis());
        request.setAmount(amount);
        request.setDescription("Starbucks - Americano x2");
        request.setTerminalSn("TESTSN1764580772062");
        request.setAttach("{\"storeId\":\"STORE001\",\"tableNo\":\"T05\"}");
        request.setNotifyUrl("https://merchant.com/notify");
        
        // Set timeExpire (format: yyyy-MM-DDTHH:mm:ss+TIMEZONE, ISO 8601)
        ZonedDateTime expireTime = ZonedDateTime.now().plusMinutes(10);
        String timeExpire = expireTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
        request.setTimeExpire(timeExpire);

        try {
            SaleResponse response = client.sale(request);
            assertNotNull(response);
            assertNotNull(response.getTransactionId());
            log.info("Sale transaction successful: {}", JsonUtil.toJson(response));

        } catch (SunbayNetworkException e) {
            log.error("Network Error: {}", e.getMessage());
        } catch (SunbayBusinessException e) {
            log.error("API Error: code={}, msg={}", e.getCode(), e.getMessage());
        }
    }

    /**
     * Test authorization transaction
     */
    @Test
    @Ignore("Requires real API connection")
    public void testAuth() {
        AuthRequest request = new AuthRequest();
        request.setAppId("test_sm6par3xf4d3tkum");
        request.setMerchantId("M1254947005");
        request.setReferenceOrderId("ORDER" + System.currentTimeMillis());
        request.setTransactionRequestId("ORDER20231119002" + System.currentTimeMillis());
        request.setAmount(Amount.of(200.00, "USD"));
        request.setPaymentMethod(PaymentMethodInfo.card("VISA"));
        request.setTerminalSn("TESTSN1764580772062");
        request.setDescription("Hotel reservation");

        try {
            AuthResponse response = client.auth(request);
            assertNotNull(response);
            assertNotNull(response.getTransactionId());
        } catch (SunbayNetworkException e) {
            log.error("Network Error: {}", e.getMessage());
        } catch (SunbayBusinessException e) {
            log.error("API Error: code={}, msg={}", e.getCode(), e.getMessage());
        }
    }

    /**
     * Test forced authorization transaction
     */
    @Test
    @Ignore("Requires real API connection")
    public void testForcedAuth() {
        ForcedAuthRequest request = new ForcedAuthRequest();
        request.setAppId("test_sm6par3xf4d3tkum");
        request.setMerchantId("M1254947005");
        request.setReferenceOrderId("FORCED" + System.currentTimeMillis());
        request.setTransactionRequestId("PAY_REQ_" + System.currentTimeMillis());
        request.setAmount(Amount.of(100.00, "USD"));
        request.setTerminalSn("TESTSN1764580772062");
        request.setDescription("Forced authorization");
        request.setAttach("{\"reason\":\"chip_damaged\"}");
        request.setNotifyUrl("https://merchant.com/notify");

        try {
            ForcedAuthResponse response = client.forcedAuth(request);
            assertNotNull(response);
            assertNotNull(response.getTransactionId());
        } catch (SunbayNetworkException e) {
            log.error("Network Error: {}", e.getMessage());
        } catch (SunbayBusinessException e) {
            log.error("API Error: code={}, msg={}", e.getCode(), e.getMessage());
        }
    }

    /**
     * Test incremental authorization transaction
     */
    @Test
    @Ignore("Requires real API connection")
    public void testIncrementalAuth() {
        IncrementalAuthRequest request = new IncrementalAuthRequest();
        request.setAppId("test_sm6par3xf4d3tkum");
        request.setMerchantId("M1254947005");
        request.setOriginalTransactionId("TXN20231119001");
        request.setReferenceOrderId("INCR" + System.currentTimeMillis());
        request.setTransactionRequestId("PAY_REQ_" + System.currentTimeMillis());
        request.setAmount(Amount.of(20.00, "USD"));
        request.setTerminalSn("TESTSN1764580772062");
        request.setDescription("Increase authorization amount");
        request.setAttach("{\"reason\":\"additional_charge\"}");

        try {
            IncrementalAuthResponse response = client.incrementalAuth(request);
            assertNotNull(response);
            assertNotNull(response.getTransactionId());
        } catch (SunbayNetworkException e) {
            log.error("Network Error: {}", e.getMessage());
        } catch (SunbayBusinessException e) {
            log.error("API Error: code={}, msg={}", e.getCode(), e.getMessage());
        }
    }

    /**
     * Test post authorization transaction
     */
    @Test
    @Ignore("Requires real API connection")
    public void testPostAuth() {
        PostAuthRequest request = new PostAuthRequest();
        request.setAppId("test_sm6par3xf4d3tkum");
        request.setMerchantId("M1254947005");
        request.setOriginalTransactionId("TXN20231119001");
        request.setReferenceOrderId("POST" + System.currentTimeMillis());
        request.setTransactionRequestId("PAY_REQ_" + System.currentTimeMillis());
        
        Amount amount = new Amount();
        amount.setOrderAmount(100.00);
        amount.setTipAmount(5.00);
        amount.setTaxAmount(8.00);
        amount.setPricingCurrency("USD");
        request.setAmount(amount);
        
        request.setTerminalSn("TESTSN1764580772062");
        request.setDescription("Pre-auth completion");
        request.setAttach("{\"checkoutTime\":\"2023-11-19T12:00:00+08:00\"}");

        try {
            PostAuthResponse response = client.postAuth(request);
            assertNotNull(response);
            assertNotNull(response.getTransactionId());
        } catch (SunbayNetworkException e) {
            log.error("Network Error: {}", e.getMessage());
        } catch (SunbayBusinessException e) {
            log.error("API Error: code={}, msg={}", e.getCode(), e.getMessage());
        }
    }

    /**
     * Test refund transaction
     */
    @Test
    @Ignore("Requires real API connection")
    public void testRefund() {
        RefundRequest request = new RefundRequest();
        request.setAppId("test_sm6par3xf4d3tkum");
        request.setMerchantId("M1254947005");
        request.setOriginalTransactionId("TXN20231119001");
        request.setAmount(Amount.of(50.00, "USD"));
        request.setTerminalSn("TESTSN1764580772062");
        request.setDescription("Refund for quality issue");

        try {
            RefundResponse response = client.refund(request);
            assertNotNull(response);
            assertNotNull(response.getTransactionId());
        } catch (SunbayNetworkException e) {
            log.error("Network Error: {}", e.getMessage());
        } catch (SunbayBusinessException e) {
            log.error("API Error: code={}, msg={}", e.getCode(), e.getMessage());
        }
    }

    /**
     * Test void transaction
     */
    @Test
    @Ignore("Requires real API connection")
    public void testVoid() {
        VoidRequest request = new VoidRequest();
        request.setAppId("test_sm6par3xf4d3tkum");
        request.setMerchantId("M1254947005");
        request.setOriginalTransactionId("TXN20231119001");
        request.setReferenceOrderId("VOID" + System.currentTimeMillis());
        request.setTerminalSn("TESTSN1764580772062");
        request.setDescription("Cancel by mistake");

        try {
            VoidResponse response = client.voidTransaction(request);
            assertNotNull(response);
            assertNotNull(response.getTransactionId());
        } catch (SunbayNetworkException e) {
            log.error("Network Error: {}", e.getMessage());
        } catch (SunbayBusinessException e) {
            log.error("API Error: code={}, msg={}", e.getCode(), e.getMessage());
        }
    }

    /**
     * Test abort transaction
     */
    @Test
    @Ignore("Requires real API connection")
    public void testAbort() {
        AbortRequest request = new AbortRequest();
        request.setAppId("test_sm6par3xf4d3tkum");
        request.setMerchantId("M1254947005");
        request.setOriginalTransactionId("TXN20231119001");
        request.setTerminalSn("TESTSN1764580772062");
        request.setDescription("Customer cancelled payment");
        request.setAttach("{\"reason\":\"customer_cancel\"}");

        try {
            AbortResponse response = client.abort(request);
            assertNotNull(response);
            assertNotNull(response.getOriginalTransactionId());
        } catch (SunbayNetworkException e) {
            log.error("Network Error: {}", e.getMessage());
        } catch (SunbayBusinessException e) {
            log.error("API Error: code={}, msg={}", e.getCode(), e.getMessage());
        }
    }

    /**
     * Test tip adjust transaction
     */
    @Test
    @Ignore("Requires real API connection")
    public void testTipAdjust() {
        TipAdjustRequest request = new TipAdjustRequest();
        request.setAppId("test_sm6par3xf4d3tkum");
        request.setMerchantId("M1254947005");
        request.setOriginalTransactionId("TXN20231119001");
        request.setTransactionRequestId("PAY_REQ_" + System.currentTimeMillis());
        request.setTipAmount(8.00);
        request.setOperatorId("OP001");
        request.setAttach("{\"reason\":\"service_charge\"}");

        try {
            TipAdjustResponse response = client.tipAdjust(request);
            assertNotNull(response);
            assertNotNull(response.getTransactionId());
        } catch (SunbayNetworkException e) {
            log.error("Network Error: {}", e.getMessage());
        } catch (SunbayBusinessException e) {
            log.error("API Error: code={}, msg={}", e.getCode(), e.getMessage());
        }
    }

    /**
     * Test query transaction
     */
    @Test
    @Ignore("Requires real API connection")
    public void testQuery() {
        QueryRequest request = new QueryRequest();
        request.setAppId("test_sm6par3xf4d3tkum");
        request.setMerchantId("M1254947005");
        request.setTransactionId("TXN20231119001");

        try {
            QueryResponse response = client.query(request);
            assertNotNull(response);
            assertNotNull(response.getTransactionStatus());
        } catch (SunbayNetworkException e) {
            log.error("Network Error: {}", e.getMessage());
        } catch (SunbayBusinessException e) {
            log.error("API Error: code={}, msg={}", e.getCode(), e.getMessage());
        }
    }

    /**
     * Test batch close
     */
    @Test
    @Ignore("Requires real API connection")
    public void testBatchClose() {
        BatchCloseRequest request = new BatchCloseRequest();
        request.setAppId("test_sm6par3xf4d3tkum");
        request.setMerchantId("M1254947005");
        request.setEnablePushToTerminal(false);
        request.setDescription("End of day settlement");

        try {
            BatchCloseResponse response = client.batchClose(request);
            assertNotNull(response);
            assertNotNull(response.getBatchNo());
        } catch (SunbayNetworkException e) {
            log.error("Network Error: {}", e.getMessage());
        } catch (SunbayBusinessException e) {
            log.error("API Error: code={}, msg={}", e.getCode(), e.getMessage());
        }
    }
}
