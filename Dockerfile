FROM tomcat:8

ENV MYPATH /usr/local/tomcat

WORKDIR $MYPATH

ADD ./webapps/topic.war $MYPATH/webapps

ENTRYPOINT ["catalina.sh","run"]

EXPOSE 8080
