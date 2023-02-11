ARG NODE_VERSION

FROM node:${NODE_VERSION}

WORKDIR /app

COPY . .

RUN npm install

CMD ["node", "App.js"]
