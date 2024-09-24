FROM liquibase/liquibase:4.27

WORKDIR /liquibase/data

COPY . .

CMD ["--defaultsFile=/liquibase/data/liquibase.properties", "--contexts=beta", "--changeLogFile=master_changelog.xml", "update"]