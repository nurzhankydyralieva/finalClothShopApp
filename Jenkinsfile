pipeline {
	agent any
	tools{
		maven 'maven:3.8.3'
		jdk 'jdk17'
	}
  stages {
  	stage('Maven Install') {
    	agent {
      	docker {
        	image 'maven:3.8.3'
        }
      }
      steps {
      	sh 'mvn clean install'
      }
    }
  }
}
