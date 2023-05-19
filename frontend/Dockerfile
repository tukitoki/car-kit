FROM node:lts-alpine AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm install -g @angular/cli
RUN ng build --configuration=production

FROM nginx:1.23-alpine
ENV CONFIGURATION='production'
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/car-kit /usr/share/nginx/html
