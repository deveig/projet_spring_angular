FROM node:24
WORKDIR /usr/local/app
COPY package*.json .
RUN npm install
COPY . .
RUN npm run build
CMD ["npm", "run", "serve:ssr:frontend"]