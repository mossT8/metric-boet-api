FROM liquibase/liquibase:4.27

WORKDIR /liquibase/data

COPY ./database .

CMD ["--defaultsFile=/liquibase/data/liquibase.properties", "--contexts=beta", "--changeLogFile=master_changelog.xml", "update"]