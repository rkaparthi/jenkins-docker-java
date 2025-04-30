# Use Amazon Corretto 17 with Tomcat 9
#FROM amazoncorretto:17-alpine as builder

# Optional: Build WAR here if you want multi-stage
# Otherwise, skip this stage and use Maven build before Docker


FROM tomcat:10.1-jdk17-corretto
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR to Tomcat webapps directory
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
