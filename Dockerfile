# Use Amazon Corretto 17 with Tomcat 9
#FROM amazoncorretto:17-alpine as builder

# Optional: Build WAR here if you want multi-stage
# Otherwise, skip this stage and use Maven build before Docker

FROM tomcat:8-jre11
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR to Tomcat webapps directory
COPY target/employee-service.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
