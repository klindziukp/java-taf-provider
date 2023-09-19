# java-platform-taf-provider-service
Java platform TAF provider service

## Service set up via docker-compose
- Build Docker images via command(it will take time)
```bash
sh script image-build/image-build.sh
```
- Start system with docker compose
```bash
docker-compose -f docker-compose.yml up
```
## Service set up via docker-compose + IntelliJ
- Start infra system with docker compose
```bash
docker-compose -f docker-compose-infra.yml up
```

## Open API documentation
- Open `http://{server}:{port}/api-docs` to view documentation in JSON format for TDM service
```bash
http://localhost:8081/v3/api-docs
```
- Open `http://{server}:{port}/swagger-ui.html` to view api endpoints for TDM service
```bash
http://localhost:8081/swagger-ui/index.html
```
