FROM liquibase/liquibase:4.27

WORKDIR /liquibase/data

COPY . .

CMD ["--defaultsFile=liquibase.properties", "--contexts=beta", "--changeLogFile=master_changelog.xml", "update"]