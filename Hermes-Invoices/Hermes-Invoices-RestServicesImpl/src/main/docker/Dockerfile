FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/invoicesService
ADD @project.build.finalName@.jar /usr/local/invoicesService/
RUN mkdir -p /usr/local/invoicesService/plugins
ADD disabled.txt /usr/local/invoicesService/plugins
ADD enabled.txt /usr/local/invoicesService/plugins
RUN mkdir -p /usr/local/invoicesService/plugins/plugin1
ADD Invoices-PluginA-0.0.1-SNAPSHOT.jar /usr/local/invoicesService/plugins/plugin1
ADD plugin.properties /usr/local/invoicesService/plugins/plugin1
ADD run.sh /usr/local/run.sh
RUN chmod +x /usr/local/run.sh
CMD sh /usr/local/run.sh