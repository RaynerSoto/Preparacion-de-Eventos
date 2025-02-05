FROM postgres:14
LABEL authors="Rayner Alejandro Soto Martínez"
ENV POSTGRES_USER: postgres
ENV POSTGRES_PASSWORD: 0000
EXPOSE 5432
COPY ../../src/main/resources/db/migration/v1__create%20_database.sql /docker-entrypoint-initdb.d/
ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["postgres"]