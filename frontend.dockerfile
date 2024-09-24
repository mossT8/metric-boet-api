FROM node:16.16.0-alpine

# install simple http server for serving static content
RUN yarn global add http-server

# make the 'app' folder the current working directory
WORKDIR /app

# copy both 'package.json' and 'yarn.lock' (if available)
COPY ./frontend/package.json ./frontend/package-lock.json ./.

# install project dependencies
RUN yarn install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY ./frontend .

# build javascript and java script mapping files from tpescript
RUN yarn run build || true

CMD [ "http-server", "dist" ]
