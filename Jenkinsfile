pipeline {
  agent any  
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  stages {
    stage('Scan') {
      steps {
        withSonarQubeEnv(installationName: 'SonarQube') { 
           sh" ${SCANNER_HOME**}**/bin/sonar-scanner \
           -Dsonar.projectKey=Sonar_finalClothShopApp \
           -Dsonar.sources=. "
        }
      }
    }
  }
}
