FROM amazoncorretto:21-alpine-jdk
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

ADD build/libs/java-taf-provider-service-0.0.1-SNAPSHOT.jar java-taf-provider.jar

COPY template template
COPY template/tool/maven/.mvn/wrapper template/tool/maven/.mvn/wrapper

EXPOSE 8081
ENTRYPOINT ["sh", "-c", "java -jar java-taf-provider.jar"]