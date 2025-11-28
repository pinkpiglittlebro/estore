FROM tomcat:9.0-jdk11

COPY target/estore.war /usr/local/tomcat/webapps/estore.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
