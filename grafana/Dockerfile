# Use the official Grafana image as the base image
FROM grafana/grafana:7.3.3

# Set environment variables for Grafana
ENV GF_SECURITY_ADMIN_PASSWORD=${GF_SECURITY_ADMIN_PASSWORD}

# Copy the grafana configuration file to the container
COPY ./config/grafana.yml /etc/grafana/grafana.ini
