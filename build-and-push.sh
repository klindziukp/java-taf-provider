./gradlew spotlessApply clean build -x test
docker build -t klindziuk/taf-wizard:1.0.2 .
docker push klindziuk/taf-wizard:1.0.2