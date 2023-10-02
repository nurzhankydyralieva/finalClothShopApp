pipeline {
  agent any  
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  stages {
    stage('Scan') {
      steps {
        withSonarQubeEnv(installationName: 'SonarQube') { 
            bat './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.10.0.2594:sonar'
        }
      }
    }
  }
}
