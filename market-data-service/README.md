# Market Data Service

A Spring Boot microservice that serves stock market data through REST endpoints and streams live prices over WebSocket. It provides endpoints to fetch all stocks, a specific stock by symbol, and the list of holding stock names.

## Ports & Endpoints

- **Service Port:** `8082`
- **Base Path:** `/api/v1/market`
- **WebSocket path:** `/ws/**` (proxied via gateway as `ws://localhost:8082`)
- **Swagger UI (direct):** `http://localhost:8082/swagger-ui/index.html`
- **Swagger UI (via Gateway):** `http://localhost:8080/swagger-ui/index.html` → select **market-data-service**

## API Reference

All REST endpoints require a valid JWT token.

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| GET | `/api/v1/market/stocks` | Get all stocks | JWT |
| GET | `/api/v1/market/stocks/{symbol}` | Get stock by symbol | JWT |
| GET | `/api/v1/market/stocks/list` | Holding stocks name | JWT |
| WS | `/ws/**` | Real-time market data stream | — (gateway route) |

## Schemas

### `StockPrice`
| Field | Type |
|-------|------|
| symbol | string |
| companyName | string |
| price | number |
| change | number |
| changePercent | number |
| high | number |
| low | number |
| open | number |
| volume | integer (int64) |
| timestamp | date-time |

### `Stock`
| Field | Type |
|-------|------|
| symbol | string |
| companyName | string |
| initialPrice | number |
| exchange | string |
| currency | string |
| active | boolean |

## WebSocket

The gateway exposes a dedicated WebSocket route:

```yaml
- id: market-websocket
  uri: ws://localhost:8082
  predicates:
    - Path=/ws/**