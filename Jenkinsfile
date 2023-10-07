pipeline {
    tools {
      maven "MAVEN_HOME"
      jdk "JAVA_HOME"
    }
    agent { any { image 'maven:3.8.3-eclipse-temurin-17-alpine' } }
    stages {
        stage('GetCode'){
            steps{
                git branch: 'main',
                changelog: false,
                poll: false,
                url: "https://github.com/nurzhankydyralieva/finalClothShopApp.git"
            }
        }

        stage('Build with Maven'){
            steps{
                echo "Cleaning..."
                bat 'mvn clean install'
                bat 'docker build -t my-maven-app .'
            }
        }

    }

}


    
