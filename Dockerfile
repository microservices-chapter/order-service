FROM java:8-jre
MAINTAINER Aravind Natarajan

ADD ./build/libs/order-service-1.0-SNAPSHOT.jar /service/
CMD ["java", "-Xmx200m", "-jar", "/service/order-service-1.0-SNAPSHOT.jar"]

EXPOSE 8099
