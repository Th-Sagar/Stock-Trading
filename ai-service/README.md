# AI Service — Fraud Detection

A Spring Boot microservice that consumes order and transaction events via Kafka and applies fraud-detection logic to flag suspicious activity. It publishes fraud verdicts and alerts that can be consumed by the Order Service and User Service.

## Ports & Endpoints

- **Service Port:** Configurable (not routed through the API Gateway — communication is Kafka-based)
- **Swagger UI (direct):** Available on the service's own port if enabled
- **Swagger UI (via Gateway):** Not applicable

## Responsibilities

- Subscribe to order and transaction topics.
- Score each order/user transaction for fraud risk.
- Publish a fraud detection result / alert to a Kafka topic.
- Optionally flag user status (e.g., suspended, review) for high-risk cases.

## Kafka Topics

| Topic | Direction | Purpose |
|-------|-----------|---------|
| `order-events` | Consume | New orders to evaluate |
| `fraud-events` | Produce | Fraud detection verdicts |
| `user-events` | Consume | Registration/login anomalies |

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