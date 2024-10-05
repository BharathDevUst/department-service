pipeline {
	agent any
	tools {
		maven "maven-3.9.9"
		jdk "java-17"
	}
	stages {
		stage('Clone'){
			steps {
			    git url: "https://github.com/bharathdevust/department-service.git",
			    branch: "master"
			}
		}
		stage('Build'){
		    steps {
			    bat "mvn clean install -DskipTests"
			}
		}
		stage('Test'){
			steps {
			    bat "mvn test"
            }
		}
		stage('Deploy') {
			steps {
			    bat "docker rm -f department-server"
			    bat "docker rmi -f department-image"
			    bat "docker build -t department-image ."
                bat "docker run -p 8090:8090 -d --name department-server department-image"
            }
		}
	}
}
