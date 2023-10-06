pipeline {
    agent any
    tools {
      maven "MAVEN_HOME"
    }
    
    environment {
        PATH ="$PATH:/opt/apache-maven-3.9.2/bin"
    }
    
    stages{
        stage('GetCode'){
            steps{
                git branch: 'main',
                changelog: false,
                poll: false,
                url: "https://github.com/nurzhankydyralieva/finalClothShopApp.git"
            }
        }

         stage('Clean'){
            steps{
                echo "Cleaning..."
                sh 'mvn clean'
            } 
        }

        stage('Test'){
            steps{
                echo "Runnig tests..."
                bat 'mvn test'
            }
        }
         
        stage('SonarQube Analysis'){
            steps{
                withSonarQubeEnv('SonarQube'){
                    echo "Running static code scanner"
                    bat """./mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar \
                    -Dsonar.java.binaries=. """
                }
            }
        }
        stage('Quality gate'){
            steps{
                timeout(time: 2, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build'){
            steps{
                echo "Creating build"
                bat 'mvn build'
            }
        }
    }
    post{
        success{
            deploy adapters: [tomcat8(credentialsId: 'deploy', path: '',
            url: 'http://localhost:8080')],
            contextPath: '/finalClothShopApp',
            onFailure: false,
            war: '**/*.war'
        }
    }
}

