pipeline {
    agent any

    stages {
        stage('Maven Test') {
            steps {
                echo 'Testing for Maven Build...'
				//sh "git submodule deinit --all -f"
				//sh "git submodule init"
				//sh "git submodule sync"
				//sh "git submodule update"
				
				sh "mvn test"
				
            }
        }
		
		stage("Maven Package"){
			steps{
				echo 'Maven Packaging...' 
				sh "mvn clean package"
			
			
			}
		}
		
		
		
		
        stage('Docker Build') {
            steps {
				echo 'Building Docker Image...'
				sh "docker context use default" 
                script{
					app = docker.build("transactionms")
				}
            }
        }
		
		
		stage("Docker Tag"){
			steps{
				echo 'Tagging Docker Image...'
				script{
					app.tag(["latest"])
				
				
				}

			}

		}
		
		
		
        stage('Docker Deploy') {
            steps {
				
                echo 'Deploying image to cloud...'
				script{
					docker.withRegistry('https://445292818922.dkr.ecr.us-east-1.amazonaws.com','ecr:us-east-1:aws-creds'){
					app.push("latest")
					}
				}
            }
        }
    }
}