pipeline {
  agent any  
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  stages {
    stage('SonarQube Analysis') {
      steps {
        withSonarQubeEnv(installationName: 'SonarQube') { 
          bat """./mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar \
          -Dsonar.java.binaries=. """
        }
      }
    }
  }
}