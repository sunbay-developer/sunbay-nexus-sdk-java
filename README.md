# Sunbay Java SDK

Official Java SDK for Sunbay Payment Platform

## Features

- ✅ Simple and intuitive API
- ✅ Builder pattern for easy request construction
- ✅ Support Java 8+
- ✅ Automatic authentication
- ✅ Automatic retry for GET requests
- ✅ Comprehensive exception handling
- ✅ Minimal dependencies

## Installation

### Maven

```xml
<dependency>
    <groupId>com.sunmi</groupId>
    <artifactId>sunbay-nexus-sdk-java</artifactId>
    <version>1.0.7</version>
</dependency>
```

### Gradle

```gradle
implementation 'com.sunmi:sunbay-nexus-sdk-java:1.0.6'
```

## Quick Start

### 1. Initialize Client

The `NexusClient` is thread-safe and can be reused across multiple threads. You have two options:

**Option 1: Use as a singleton (Recommended for production)**

```java
// Create once and reuse
NexusClient client = new NexusClient.Builder()
    .apiKey("{YOUR_API_KEY}")
    .baseUrl("https://open.sunbay.us")
    .build();

// Use the client throughout your application
// Remember to call client.close() when your application shuts down
```

**Option 2: Use try-with-resources (For temporary usage)**

```java
// The client implements AutoCloseable, so you can use try-with-resources
try (NexusClient client = new NexusClient.Builder()
        .apiKey("{YOUR_API_KEY}")
        .baseUrl("https://open.sunbay.us")
        .build()) {
    // Use client
    SaleResponse response = client.sale(request);
    // Client will be automatically closed when exiting the try block
}
```

### 2. Sale Transaction

```java
import com.sunmi.sunbay.nexus.NexusClient;
import com.sunmi.sunbay.nexus.exception.SunbayBusinessException;
import com.sunmi.sunbay.nexus.exception.SunbayNetworkException;
import com.sunmi.sunbay.nexus.model.common.SaleAmount;
import com.sunmi.sunbay.nexus.model.request.SaleRequest;
import com.sunmi.sunbay.nexus.model.response.SaleResponse;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

// Assume client is already initialized (as singleton or in try-with-resources)
// NexusClient client = ... (from step 1)

// Set expiration time (optional)
ZonedDateTime expireTime = ZonedDateTime.now().plusMinutes(10);
String timeExpire = expireTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));

// Build amount using Builder pattern
// Note: All amount fields use Integer type and are in the smallest currency unit (cents)
// For example: 100.00 USD = 10000 cents
SaleAmount amount = SaleAmount.builder()
    .orderAmount(10000)  // 100.00 USD in cents
    .priceCurrency("USD")
    .build();

// Build sale request using Builder pattern
SaleRequest request = SaleRequest.builder()
    .appId("app_123456")
    .merchantId("mch_789012")
    .referenceOrderId("ORDER20231119001")
    .transactionRequestId("PAY_REQ_" + System.currentTimeMillis())
    .amount(amount)
    .description("Product purchase")
    .terminalSn("T1234567890")
    .timeExpire(timeExpire)
    .build();

// Execute transaction
try {
    SaleResponse response = client.sale(request);
    // If no exception is thrown, the transaction is successful
    System.out.println("Transaction ID: " + response.getTransactionId());
} catch (SunbayNetworkException e) {
    System.err.println("Network Error: " + e.getMessage());
} catch (SunbayBusinessException e) {
    System.err.println("API Error: " + e.getCode() + " - " + e.getMessage());
}
```

## API Methods

All request classes support Builder pattern for easy construction. Here are some examples:

### Transaction APIs

- `sale(SaleRequest)` - Sale transaction
- `auth(AuthRequest)` - Authorization (pre-auth)
- `forcedAuth(ForcedAuthRequest)` - Forced authorization
- `incrementalAuth(IncrementalAuthRequest)` - Incremental authorization
- `postAuth(PostAuthRequest)` - Post authorization (pre-auth completion)
- `refund(RefundRequest)` - Refund
- `voidTransaction(VoidRequest)` - Void transaction
- `abort(AbortRequest)` - Abort transaction
- `tipAdjust(TipAdjustRequest)` - Tip adjust

**Example: Authorization**

```java
AuthRequest request = AuthRequest.builder()
    .appId("app_123456")
    .merchantId("mch_789012")
    .referenceOrderId("AUTH" + System.currentTimeMillis())
    .transactionRequestId("PAY_REQ_" + System.currentTimeMillis())
    .amount(AuthAmount.builder()
        .orderAmount(20000)  // 200.00 USD in cents
        .priceCurrency("USD")
        .build())
    .description("Hotel reservation")
    .terminalSn("T1234567890")
    .build();

AuthResponse response = client.auth(request);
```

**Example: Query Transaction**

```java
QueryRequest request = QueryRequest.builder()
    .appId("app_123456")
    .merchantId("mch_789012")
    .transactionId("TXN20231119001")
    .build();

QueryResponse response = client.query(request);
```

### Query APIs

- `query(QueryRequest)` - Query transaction
- `batchQuery(BatchQueryRequest)` - Batch query

### Settlement APIs

- `batchClose(BatchCloseRequest)` - Batch close

**Example: Batch Close**

```java
BatchCloseRequest request = BatchCloseRequest.builder()
    .appId("app_123456")
    .merchantId("mch_789012")
    .transactionRequestId("BATCH_CLOSE_" + System.currentTimeMillis())
    .terminalSn("T1234567890")
    .description("End of day settlement")
    .build();

BatchCloseResponse response = client.batchClose(request);
```

## Exception Handling

The SDK throws two types of exceptions:

- **SunbayNetworkException**: Network-related errors (connection timeout, network error, etc.)
- **SunbayBusinessException**: Business logic errors (parameter validation, API business errors, etc.)

Always catch `SunbayNetworkException` before `SunbayBusinessException`:

```java
// Build request using Builder pattern
SaleRequest request = SaleRequest.builder()
    .appId("app_123456")
    .merchantId("mch_789012")
    .referenceOrderId("ORDER20231119001")
    .transactionRequestId("PAY_REQ_" + System.currentTimeMillis())
    .amount(SaleAmount.builder()
        .orderAmount(10000)  // 100.00 USD in cents
        .priceCurrency("USD")
        .build())
    .description("Product purchase")
    .terminalSn("T1234567890")
    .build();

try {
    SaleResponse response = client.sale(request);
    // If no exception is thrown, the transaction is successful
    // Use response object here
} catch (SunbayNetworkException e) {
    // Network exception (e.g., connection timeout, network error)
    System.err.println("Network Error: " + e.getMessage());
    if (e.isRetryable()) {
        // Can retry
    }
} catch (SunbayBusinessException e) {
    // Business exception (e.g., insufficient funds, parameter error)
    System.err.println("API Error: " + e.getCode() + " - " + e.getMessage());
    if (e.getTraceId() != null) {
        System.err.println("Trace ID: " + e.getTraceId());
    }
}
```

## Configuration

```java
NexusClient client = new NexusClient.Builder()
    .apiKey("sk_test_xxx")
    .baseUrl("https://open.sunbay.us")  // Default: https://open.sunbay.us
    .connectTimeout(30000)               // Default: 30000ms (30 seconds)
    .readTimeout(60000)                   // Default: 60000ms (60 seconds)
    .maxRetries(3)                        // Default: 3 retries for GET requests
    .maxTotal(200)                        // Default: 200 (max total connections in pool)
    .maxPerRoute(20)                      // Default: 20 (max connections per route)
    .build();
```

### Connection Pool Configuration

The SDK uses Apache HttpClient's connection pool to manage HTTP connections efficiently. You can configure:

- **maxTotal**: Maximum total connections in the connection pool across all routes (default: 200)
  - This is the total number of connections that can be open simultaneously across all hosts/routes
  - Example: If you have 10 different API endpoints, the total connections to all endpoints combined cannot exceed this value

- **maxPerRoute**: Maximum connections per route/host (default: 20)
  - This is the maximum number of connections that can be open to a single host/route
  - A route is typically defined by the scheme (http/https), host, and port
  - Example: For `https://open.sunbay.us`, you can have at most 20 concurrent connections

**Example:**
- If `maxTotal = 200` and `maxPerRoute = 20`
- You can have up to 20 connections to `https://open.sunbay.us` (limited by maxPerRoute)
- But if you're connecting to multiple hosts, the total across all hosts cannot exceed 200 (limited by maxTotal)

These settings help optimize performance for high-concurrency scenarios.

## Requirements

- Java 8 or higher
- Apache HttpClient 4.5.14
- Jackson 2.18.2

## License

MIT License
