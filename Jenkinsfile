pipeline {
    tools {
      maven "MAVEN_HOME"
      jdk "JAVA_HOME"
    }
    agent { docker { image 'maven:3.9.4-eclipse-temurin-17-alpine' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }

}


    
