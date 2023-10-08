FROM node:latest
WORKDIR /ProgramData/Jenkins/.jenkins/workspace

RUN apt-get -y install git
