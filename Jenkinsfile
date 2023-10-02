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
        def scannerHome = tool name: 'SonarQubeScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallattion'
        withSonarQubeEnv(installationName: 'SonarQube') { 
           def scannerCmd = "${scannerHome}/bin/sonar-scanner"
           def projectKey = 'Sonar_finalClothShopApp'
           def projectName = 'Sonar_finalClothShopApp'
          
          bat """
             ${scannerCmd} -Dsonar.projectKey=${projectKey} \
             -Dsonar.projectName=${projectName} \
             -Dsonar.java.binaries=. \
             -Dsonar.sources=src \
              """
        }
      }
    }
  }
}
 
