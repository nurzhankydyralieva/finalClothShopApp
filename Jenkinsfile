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
         SCANNER_HOME = tool name: 'SonarQubeScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallattion'
        withSonarQubeEnv(installationName: 'SonarQube') { 
            SCANNER_CMD = "${SCANNER_HOME}/bin/sonar-scanner"
            PROJECT_KEY = 'Sonar_finalClothShopApp'
            PROJECT_NAME = 'Sonar_finalClothShopApp'
          
          bat """
             ${SCANNER_CMD} -Dsonar.projectKey=${PROJECT_KEY} \
             -Dsonar.projectName=${PROJECT_NAME} \
             -Dsonar.java.binaries=. \
             -Dsonar.sources=src \
              """
        }
      }
    }
  }
}
 
