FROM node:latest
WORKDIR /ProgramData/Jenkins/.jenkins/workspace

RUN apk add -U git curl
