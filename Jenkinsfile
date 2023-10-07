pipeline {
    tools {
      maven "MAVEN_HOME"
      jdk "JAVA_HOME"
    }
    agent { any { image 'maven:3.8.3-eclipse-temurin-17-alpine' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
    }

}


    
