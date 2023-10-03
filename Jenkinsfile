pipeline {
    agent any
    environment {
        PATH ="$PATH:/opt/apache-maven-3.9.2/bin"
    }
   options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
    tools {
      maven "MAVEN_HOME"
    }
    stages{
        stage('GetCode'){
            steps{
                git url: "https://github.com/nurzhankydyralieva/finalClothShopApp.git", branch: 'main'
            }
        }
        stage('Build'){
            steps{
                git url: "https://github.com/nurzhankydyralieva/finalClothShopApp.git"
                bat "mvn -Dmaven.test.failure.ignore=true clean compile install"
            }
        }
        stage("Test") {
            steps {
                git url: "https://github.com/nurzhankydyralieva/finalClothShopApp.git"
                 bat "mvn -Dmaven.test.failure.ignore=true clean test"
          }

      }
        stage('SonarQube Analysis'){
            steps{
                withSonarQubeEnv('SonarQube'){
                  bat """./mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar \
                  -Dsonar.java.binaries=. """
                }
            }
        }
        stage("Deploy") {
            steps {
            git url: "https://github.com/nurzhankydyralieva/finalClothShopApp.git"
            bat "mvn -Dmaven.test.failure.ignore=true clean install"
          }
        post {
            success {
                archiveArtifacts 'target/*.jar'
            }
          }
      }
    }
}

