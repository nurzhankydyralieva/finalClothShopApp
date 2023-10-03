pipeline {
    agent any
    environment {
        PATH ="$PATH:/opt/apache-maven-3.9.2/bin"
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
        stage('SonarQube Analysis'){
            steps{
                withSonarQubeEnv('SonarQube'){
                  bat """./mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar \
                  -Dsonar.java.binaries=. """
                }
            }
        }
    }
}

