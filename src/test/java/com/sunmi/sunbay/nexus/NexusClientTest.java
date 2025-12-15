package com.sunmi.sunbay.nexus;

import com.sunmi.sunbay.nexus.exception.SunbayBusinessException;
import com.sunmi.sunbay.nexus.exception.SunbayNetworkException;
import com.sunmi.sunbay.nexus.model.common.*;
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
 * @since 2025-12-12
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
        // Set timeExpire (format: yyyy-MM-DDTHH:mm:ss+TIMEZONE, ISO 8601)
        ZonedDateTime expireTime = ZonedDateTime.now().plusMinutes(10);
        String timeExpire = expireTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));

        // Build amount with all fields
        SaleAmount amount = SaleAmount.builder()
                .orderAmount(100.00)
                .pricingCurrency("USD")
                .build();

        // Build sale request
        SaleRequest request = SaleRequest.builder()
                .appId("test_sm6par3xf4d3tkum")
                .merchantId("M1254947005")
                .referenceOrderId("ORDER" + System.currentTimeMillis())
                .transactionRequestId("PAY_REQ_" + System.currentTimeMillis())
                .amount(amount)
                .description("Starbucks - Americano x2")
                .terminalSn("P344E51BJ0022")
                .attach("{\"storeId\":\"STORE001\",\"tableNo\":\"T05\"}")
                .notifyUrl("https://merchant.com/notify")
                .timeExpire(timeExpire)
                .build();

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
        AuthRequest request = AuthRequest.builder()
                .appId("test_sm6par3xf4d3tkum")
                .merchantId("M1254947005")
                .referenceOrderId("AUTH" + System.currentTimeMillis())
                .transactionRequestId("PAY_REQ_" + System.currentTimeMillis())
                .amount(AuthAmount.builder()
                        .orderAmount(200.00)
                        .pricingCurrency("USD")
                        .build())
                .paymentMethod(PaymentMethodInfo.builder()
                        .category("CARD")
                        .build())
                .terminalSn("TESTSN1764580772062")
                .description("Hotel reservation")
                .build();

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
        ForcedAuthRequest request = ForcedAuthRequest.builder()
                .appId("test_sm6par3xf4d3tkum")
                .merchantId("M1254947005")
                .referenceOrderId("FORCED" + System.currentTimeMillis())
                .transactionRequestId("PAY_REQ_" + System.currentTimeMillis())
                .amount(AuthAmount.builder()
                        .orderAmount(100.00)
                        .pricingCurrency("USD")
                        .build())
                .terminalSn("TESTSN1764580772062")
                .description("Forced authorization")
                .attach("{\"reason\":\"chip_damaged\"}")
                .notifyUrl("https://merchant.com/notify")
                .build();

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
        IncrementalAuthRequest request = IncrementalAuthRequest.builder()
                .appId("test_sm6par3xf4d3tkum")
                .merchantId("M1254947005")
                .originalTransactionId("7225836233468023")
                .transactionRequestId("PAY_REQ_" + System.currentTimeMillis())
                .amount(AuthAmount.builder()
                        .orderAmount(20.00)
                        .pricingCurrency("USD")
                        .build())
                .terminalSn("TESTSN1764580772062")
                .description("Increase authorization amount")
                .attach("{\"reason\":\"additional_charge\"}")
                .build();

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
        PostAuthAmount amount = PostAuthAmount.builder()
                .orderAmount(100.00)
                .tipAmount(5.00)
                .taxAmount(8.00)
                .pricingCurrency("USD")
                .build();

        PostAuthRequest request = PostAuthRequest.builder()
                .appId("test_sm6par3xf4d3tkum")
                .merchantId("M1254947005")
                .originalTransactionId("7225836146468011")
                .transactionRequestId("PAY_REQ_" + System.currentTimeMillis())
                .amount(amount)
                .terminalSn("TESTSN1764580772062")
                .description("Pre-auth completion")
                .attach("{\"checkoutTime\":\"2023-11-19T12:00:00+08:00\"}")
                .build();

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
        RefundRequest request = RefundRequest.builder()
                .appId("test_sm6par3xf4d3tkum")
                .merchantId("M1254947005")
                .originalTransactionId("7225836146468011")
                .transactionRequestId("PAY_REQ_" + System.currentTimeMillis())
                .amount(RefundAmount.builder()
                        .orderAmount(50.00)
                        .pricingCurrency("USD")
                        .build())
                .terminalSn("TESTSN1764580772062")
                .description("Refund for quality issue")
                .build();

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
        VoidRequest request = VoidRequest.builder()
                .appId("test_sm6par3xf4d3tkum")
                .merchantId("M1254947005")
                .originalTransactionId("7225836146468011")
                .transactionRequestId("PAY_REQ_" + System.currentTimeMillis())
                .terminalSn("TESTSN1764580772062")
                .description("Cancel by mistake")
                .build();

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
        AbortRequest request = AbortRequest.builder()
                .appId("test_sm6par3xf4d3tkum")
                .merchantId("M1254947005")
                .originalTransactionId("7225836146468011")
                .terminalSn("TESTSN1764580772062")
                .description("Customer cancelled payment")
                .attach("{\"reason\":\"customer_cancel\"}")
                .build();

        try {
            AbortResponse response = client.abort(request);
            assertNotNull(response);
            assertTrue(response.isSuccess());
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
        TipAdjustRequest request = TipAdjustRequest.builder()
                .appId("test_sm6par3xf4d3tkum")
                .merchantId("M1254947005")
                .terminalSn("TESTSN1764580772062")
                .originalTransactionId("TXN20231119001")
                .tipAmount(8.00)
                .attach("{\"reason\":\"service_charge\"}")
                .build();

        try {
            TipAdjustResponse response = client.tipAdjust(request);
            assertNotNull(response);
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
        QueryRequest request = QueryRequest.builder()
                .appId("test_sm6par3xf4d3tkum")
                .merchantId("M1254947005")
                //.transactionId("7225836056468005")
                .transactionRequestId("PAY_REQ_1765785418963")
                .build();

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
        BatchCloseRequest request = BatchCloseRequest.builder()
                .appId("test_sm6par3xf4d3tkum")
                .merchantId("M1254947005")
                .transactionRequestId("BATCH_CLOSE_" + System.currentTimeMillis())
                .terminalSn("TESTSN1764580772062")
                .description("End of day settlement")
                .build();

        try {
            BatchCloseResponse response = client.batchClose(request);
            assertNotNull(response);
            assertTrue(response.isSuccess());
        } catch (SunbayNetworkException e) {
            log.error("Network Error: {}", e.getMessage());
        } catch (SunbayBusinessException e) {
            log.error("API Error: code={}, msg={}", e.getCode(), e.getMessage());
        }
    }
}
