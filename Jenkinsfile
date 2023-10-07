pipeline{
agent any

tools{
    maven 'MAVEN_HOME'
}

parameters {
    booleanParam(
        name: 'RELEASE',
        description: 'Build a release from current commit.',
        defaultValue: false
    )
    booleanParam(
        name: 'SCAN_CODE', 
        description: 'Scan your code to see how awesome it is.',
        defaultValue: true
    )        
}

environment {
    ARTIFACT_ID = readMavenPom().getArtifactId()
}

stages{
    stage("Unit tests"){
        steps{
            configFileProvider([configFile(fileId: '1a4e39cc-eacf-4bd0-af47-337f536fd2bd', variable: 'MVN_SETTINGS')]) {
                sh 'mvn -gs $MVN_SETTINGS test '
            }
        }
    }
    stage("SonarQube Scan"){
        when {
            expression { params.SCAN_CODE == true}
        }         
        steps{
            withSonarQubeEnv('SonarQube') {
                configFileProvider([configFile(fileId: '1a4e39cc-eacf-4bd0-af47-337f536fd2bd', variable: 'MVN_SETTINGS')]) {
                    sh 'mvn clean install -gs $MVN_SETTINGS ' + 
                            'org.sonarsource.scanner.maven:sonar-maven-plugin:3.3.0.603:sonar -f pom.xml ' +
                            '-DskipTests' +
                            '-Dsonar.projectKey=com.etisoftware:$ARTIFACT_ID ' +
                            '-Dsonar.login=$DOCKER_REPO_USER ' +
                            '-Dsonar.password=$DOCKER_REPO_PASSWORD ' +
                            '-Dsonar.language=java ' +
                            '-Dsonar.sources=. ' +
                            '-Dsonar.tests=. ' +
                            '-Dsonar.test.inclusions=**/*Test*/** ' +
                            '-Dsonar.exclusions=**/*Test*/**'
                }
            }
        }
    }
    stage("Quality Gate") {
        when {
            expression { params.SCAN_CODE == true}
        }          
        steps {
            timeout(time: 5, unit: 'MINUTES') {
                // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                // true = set pipeline to UNSTABLE, false = don't
                waitForQualityGate abortPipeline: true
            }
        }
    }        
    stage("Build Snapshot"){
        when {
            expression { params.RELEASE == false}
        } 
        steps{
            configFileProvider([configFile(fileId: '1a4e39cc-eacf-4bd0-af47-337f536fd2bd', variable: 'MVN_SETTINGS')]) {
                sh 'mvn -gs $MVN_SETTINGS -DskipTests clean deploy'
            }
        }
    }

    stage("Create Docker Image For SNAPSHOT"){
        when {
            expression { params.RELEASE == false}
        }        
        steps{
            configFileProvider([configFile(fileId: '1a4e39cc-eacf-4bd0-af47-337f536fd2bd', variable: 'MVN_SETTINGS')]) {
                sh 'mvn -gs $MVN_SETTINGS ' +
                    '-Ddocker.username=${DOCKER_REPO_USER} -Ddocker.password=${DOCKER_REPO_PASSWORD} ' +
                    '-DskipTests ' +
                    'clean package spring-boot:repackage ' +
                    'docker:build docker:push'
            }
        }
    }        
    // RELEASES
    stage("Release") {
        when {
            expression { params.RELEASE == true}
        }
        steps {
            configFileProvider([configFile(fileId: '1a4e39cc-eacf-4bd0-af47-337f536fd2bd', variable: 'MVN_SETTINGS')]) {
                sh 'mvn -gs $MVN_SETTINGS clean'
                sh 'mvn -gs $MVN_SETTINGS -B release:prepare'
                sh 'mvn -gs $MVN_SETTINGS -B release:perform'
            }
        }
    }
    stage("Create Docker Image For Release"){
        when {
            expression { params.RELEASE == true}
        }        
        steps{
            configFileProvider([configFile(fileId: '1a4e39cc-eacf-4bd0-af47-337f536fd2bd', variable: 'MVN_SETTINGS')]) {
                sh 'mvn -gs $MVN_SETTINGS ' +
                    '-f target/checkout/pom.xml ' +
                    '-Ddocker.username=${DOCKER_REPO_USER} -Ddocker.password=${DOCKER_REPO_PASSWORD} ' +
                    '-DskipTests ' +
                    'clean package spring-boot:repackage ' +
                    'docker:build docker:push'
            }
        }
    }
}

post{
    always{
        cleanWs()
    }
}
}
