FROM bellsoft/liberica-openjdk-alpine:17
EXPOSE 8080
ADD /build/libs/invest-advisor*.jar /usr/local/invest-advisor/invest-advisor.jar
ENTRYPOINT ["java", "-Dapp.home=/usr/src/invest-advisor/home", "-jar", "/usr/local/invest-advisor/invest-advisor.jar"]
