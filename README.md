
# Metric Boet API

Project is a working progress. I like to play around with coding and development and wanted to showcase a full stack solution. So, I created simple java spring boot application which connects to a postrgessql database. I used a Vue frontend application to then interact with my backend end application. A more detailed version is to come I promise but for the time being can just Deploy solution locally and play around with frontend. Basic project directory is 

```
  |- backend 
  |- database
  |- frontend
  .env
  backend.dockerfile
  database.dockerfile
  frontend.dockerfile
  docker-compose.yml
  README.md
```
## Run Locally

Clone this project to desired local directory

```bash
  git clone https://github.com/mossT8/metric-boet-api.git
```

Go to the project directory

```bash
  cd metric-boet-api
```

Build Java Binaries

```bash
  cd backend
  mvn package
```

Start local enviroment with docker

```bash
  docker compose up --build
```


## Tests Accounts

Once local enviroment is up you can go to 
```url
  http://localhost:8081/home
```
you can also select the login button from the side navigation or go to

```url
  http://localhost:8081/user/login
```

Here you can use one of the following:

| Username         | Password   |
| ---------------- | ---------  |
| System Auto      | password   |
| Admin            | password   |
| Moderator        | password   |
| Blikkies         | password   |

