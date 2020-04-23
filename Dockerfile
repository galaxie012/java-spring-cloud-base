FROM gradle:6.3-jdk8 AS builder

ADD . /home/gradle/
RUN gradle clean build

FROM openjdk:8-jdk-slim
COPY --from=builder /home/gradle/eureka/build/libs/eureka-0.0.1-SNAPSHOT.jar   eureka.jar
COPY --from=builder /home/gradle/gateway/build/libs/gateway-0.0.1-SNAPSHOT.jar gateway.jar
COPY --from=builder /home/gradle/user/build/libs/user-0.0.1-SNAPSHOT.jar       user.jar
COPY --from=builder /home/gradle/news/build/libs/news-0.0.1-SNAPSHOT.jar       news.jar
RUN sed -i 's/deb.debian.org/mirrors.huaweicloud.com/' /etc/apt/sources.list
RUN sed -i 's/security.debian.org/mirrors.huaweicloud.com/' /etc/apt/sources.list
RUN apt-get clean && apt-get update && apt-get install net-tools procps vim inetutils-ping -y
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar"]
CMD [ "eureka.jar" ]
