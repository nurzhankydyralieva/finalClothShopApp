pipeline {
	agent any
	tools{
		maven "MAVEN_HOME"
		jdk "JAVA_HOME"
	}
	stages {
		stage('Maven Install') {
		
				steps {
					image 'maven:3.8.3'
				}
	
			steps {
				sh 'mvn clean install'
			}
		}
	}
}
