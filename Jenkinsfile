pipeline {
	agent any
	tools{
		maven "MAVEN_HOME"
		jdk "JAVA_HOME"
	}
	stages {
		stage('Initialize'){
			steps {
				def dockerHome = tool 'myDocker'
				env.PATH = "${dockerHome}/bin:${env.PATH}"
			}
		}
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
