# Order Service

A Spring Boot microservice responsible for placing and retrieving stock trading orders. Orders are accepted with a symbol, type, and quantity, then processed asynchronously — updating status, execution price, total amount, failure reason, and timestamps.

## Ports & Endpoints

- **Service Port:** `8083`
- **Base Path:** `/api/v1/orders`
- **Swagger UI (direct):** `http://localhost:8083/swagger-ui/index.html`
- **Swagger UI (via Gateway):** `http://localhost:8080/swagger-ui/index.html` → select **order-service**

## API Reference

All endpoints require a valid JWT token.

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/orders` | Place an Order |
| GET | `/api/v1/orders/{orderId}` | Get order by orderId |
| GET | `/api/v1/orders/my-orders` | Login user orders detail |

## Schemas

### `OrderRequest`
| Field | Type | Constraints |
|-------|------|-------------|
| userId | string | optional |
| symbol | string | required, min 1 char |
| type | string (enum) | required |
| quantity | integer (int32) | min 10 |

### `Order`
| Field | Type |
|-------|------|
| id | string |
| userId | string |
| symbol | string |
| type | string (enum) |
| status | string (enum) |
| quantity | integer (int32) |
| price | number |
| totalAmount | number |
| failureReason | string |
| createdAt | date-time |
| executedAt | date-time |

## Environment Variables

| Variable | Description |
|----------|-------------|
| `SPRING_DATASOURCE_URL` | MySQL JDBC URL |
| `SPRING_DATASOURCE_USERNAME` | DB username |
| `SPRING_DATASOURCE_PASSWORD` | DB password |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | Hibernate DDL mode |
| `SPRING_DATA_REDIS_HOST` | Redis host |
| `SPRING_DATA_REDIS_PORT` | Redis port |
| `SPRING_KAFKA_BOOTSTRAP_SERVERS` | Kafka bootstrap servers |

## Dependencies

- MySQL (`mysql:3306/trading`)
- Redis (`redis:6379`)
- Kafka (`kafka:29092`)

## Order Lifecycle

1. Client submits `POST /api/v1/orders`.
2. Order is persisted with a pending status.
3. Kafka event is published; the order is validated / executed by downstream consumers.
4. Order is updated with execution price, total amount, and final status.
5. Fraud detection (AI service) may flag suspicious orders.

## Running Locally

```bash
# Ensure dependencies are up (from project root)
docker compose up -d mysql redis kafka

# Run the service
./mvnw spring-boot:run