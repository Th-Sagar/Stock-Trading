# Notification Service

A Spring Boot microservice that consumes events from Kafka (orders, fraud alerts, user events, market data) and delivers notifications to users — in-app, email, or push. It decouples notification delivery from the core business services.

## Ports & Endpoints

- **Service Port:** Configurable (default `8085` — not routed through the API Gateway; communication is Kafka-based)
- **Swagger UI (direct):** `http://localhost:8085/swagger-ui/index.html` (if enabled)
- **Swagger UI (via Gateway):** Not applicable

## Responsibilities

- Subscribe to domain event topics from Kafka.
- Build notification payloads per user/channel.
- Send notifications via the configured channel(s): in-app (WebSocket/SSE), email, or push.
- Persist a notification history and delivery status.
- Expose a REST endpoint for users to fetch their notification inbox.

## API Reference

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/notifications` | Get logged-in user's notifications |
| GET | `/api/v1/notifications/{notificationId}` | Get notification by ID |
| PATCH | `/api/v1/notifications/{notificationId}/read` | Mark notification as read |

> Auth: JWT (if exposed via gateway) or internal-only depending on deployment.

## Kafka Topics

| Topic | Direction | Purpose |
|-------|-----------|---------|
| `order-events` | Consume | Order status changes → notify user |
| `fraud-events` | Consume | Fraud alerts → notify user/admin |
| `user-events` | Consume | Registration/login events → welcome/security mails |
| `market-events` | Consume | Price alerts, watchlist triggers |
| `notification-events` | Produce | Delivery status / audit trail |

## Schemas

### `Notification`
| Field | Type |
|-------|------|
| id | string |
| userId | string |
| type | string (enum: ORDER_UPDATE, FRAUD_ALERT, SECURITY, MARKET_ALERT) |
| channel | string (enum: IN_APP, EMAIL, PUSH) |
| title | string |
| message | string |
| status | string (enum: PENDING, SENT, FAILED, READ) |
| referenceId | string |
| createdAt | date-time |
| readAt | date-time |

### `NotificationRequest`
| Field | Type | Constraints |
|-------|------|-------------|
| userId | string | required |
| type | string (enum) | required |
| channel | string (enum) | required |
| title | string | required, min 1 char |
| message | string | required, min 1 char |
| referenceId | string | optional |

### `NotificationResponse`
| Field | Type |
|-------|------|
| id | string |
| userId | string |
| type | string |
| channel | string |
| title | string |
| message | string |
| status | string |
| createdAt | date-time |
| readAt | date-time |

## Environment Variables

| Variable | Description |
|----------|-------------|
| `SPRING_KAFKA_BOOTSTRAP_SERVERS` | Kafka bootstrap servers |
| `SPRING_DATA_REDIS_HOST` | Redis host |
| `SPRING_DATA_REDIS_PORT` | Redis port |
| `SPRING_DATASOURCE_URL` | MySQL JDBC URL |
| `SPRING_DATASOURCE_USERNAME` | DB username |
| `SPRING_DATASOURCE_PASSWORD` | DB password |

## Dependencies

- MySQL (`mysql:3306/trading`)
- Redis (`redis:6379`)
- Kafka (`kafka:29092`)

## Running Locally

```bash
# Ensure dependencies are up (from project root)
docker compose up -d mysql redis kafka

# Run the service
./mvnw spring-boot:run