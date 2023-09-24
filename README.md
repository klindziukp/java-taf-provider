# java-platform-taf-provider-service
Java platform TAF provider service

## Java Taf Provider service set up via Gradle
- Setup infra with docker compose
```bash
docker-compose -f docker-compose-infra.yml up
```
- Install JDK 21
- Run service via command
```bash
./gradlew clean build bootRun
```

## Service set up via docker-compose
- Install JDK 21
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
