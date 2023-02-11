FROM influxdb:latest

# Copy the configuration file to the correct location
COPY ./config/influxdb.yml /etc/influxdb/influxdb.conf

# Set the working directory
WORKDIR /etc/influxdb

# Expose the ports for HTTP API and the InfluxDB administration interface
EXPOSE 8086
EXPOSE 8083

# Set environment variables for InfluxDB
ENV INFLUXDB_DB=metrics
ENV INFLUXDB_HTTP_AUTH_ENABLED=true
ENV INFLUXDB_ADMIN_ENABLED=true
ENV INFLUXDB_ADMIN_USER=admin
ENV INFLUXDB_ADMIN_PASSWORD=${INFLUXDB_ADMIN_PASSWORD}
ENV INFLUXDB_USER=boet-metric-api
ENV INFLUXDB_USER_PASSWORD=${INFLUXDB_USER_PASSWORD}
