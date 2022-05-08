FROM openjdk:11
MAINTAINER giuliamendes67@gmail.com
COPY build/libs/*.jar school.jar
ENTRYPOINT ["java", "-jar", "school.jar"]