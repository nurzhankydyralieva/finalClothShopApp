pipeline {
  agent any  
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  stages {
    stage('Checkout'){
      steps {
        checkout scm
      }
    }
    stage('Build'){
      steps {
        bat 'javac -source 1.8 -target 1.8 -d target/classes src/**/*.java'
      }
    }
    stage('SonarQube Analysis') {
      steps {
         
        withSonarQubeEnv(installationName: 'SonarQube') { 
          def SCANNER_CMD = "${SCANNER_HOME}/bin/sonar-scanner"
          def PROJECT_KEY = 'Sonar_finalClothShopApp'
          def PROJECT_NAME = 'Sonar_finalClothShopApp'
          
          echo "Project Key: ${PROJECT_KEY}"
          echo "Project Name: ${PROJECT_NAME}"
          
          bat """
             -Dsonar.java.binaries=. \
             -Dsonar.sources=src \
              """
        }
      }
    }
  }
}
 
