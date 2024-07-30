# Use the official PostgreSQL image as the base
FROM postgres:14-alpine

# Set environment variables
ENV POSTGRES_PASSWORD=1234
ENV POSTGRES_USER=root
ENV POSTGRES_DB=urlshortenerdb

# Expose the default PostgreSQL port
EXPOSE 5432

# Optional: If you have any SQL scripts to initialize the database, copy them
# COPY init.sql /docker-entrypoint-initdb.d/

# Optional: Set up volumes (this is more commonly handled in docker-compose.yml)
VOLUME ["/var/lib/postgresql/data"]

# No need to specify CMD or ENTRYPOINT as the base image already has it set to start PostgreSQL
