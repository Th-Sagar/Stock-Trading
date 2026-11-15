# User Service

A Spring Boot microservice responsible for user registration, authentication, and wallet management. It issues JWT tokens (access & refresh) and manages user funds through add, credit, and deduct operations.

## Ports & Endpoints

- **Service Port:** `8081`
- **Base Path:** `/api/v1/users`
- **Swagger UI (direct):** `http://localhost:8081/swagger-ui/index.html`
- **Swagger UI (via Gateway):** `http://localhost:8080/swagger-ui/index.html` → select **user-service**

## API Reference

### Public Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/users/register` | Register User |
| POST | `/api/v1/users/login` | Login User |

### Protected Endpoints (JWT required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/users/{userId}` | Get user by ID |
| GET | `/api/v1/users/me` | Login User details |
| POST | `/api/v1/users/{userId}/funds/add` | Add funds |
| POST | `/api/v1/users/{userId}/funds/credit` | Funds credit |
| POST | `/api/v1/users/{userId}/funds/deduct` | Funds deduction |

## Schemas

### `RegisterRequest`
| Field | Type | Constraints |
|-------|------|-------------|
| email | string | required, min 1 char |
| password | string | required, 6–2147483647 chars |
| firstName | string | required, min 1 char |
| lastName | string | required, min 1 char |
| initialDeposit | number | optional |

### `LoginRequest`
| Field | Type | Constraints |
|-------|------|-------------|
| email | string | required, min 1 char |
| password | string | required, 6–2147483647 chars |

### `AuthResponse`
| Field | Type |
|-------|------|
| userId | string |
| email | string |
| firstName | string |
| lastName | string |
| walletBalance | number |
| accessToken | string |
| refreshToken | string |
| tokenType | string |

### `UserResponse`
| Field | Type |
|-------|------|
| id | string |
| email | string |
| firstName | string |
| lastName | string |
| walletBalance | number |
| status | string |
| createdAt | date-time |

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

## Running Locally

```bash
# Ensure dependencies are up (from project root)
docker compose up -d mysql redis kafka

# Run the service
./mvnw spring-boot:run