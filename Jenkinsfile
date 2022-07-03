pipeline {
    agent any

    stages {
	
	
	
		stage('Sonarqube analysis'){
			steps{
				script{
					def mvn = tool 'default_maven';
				}
				withSonarQubeEnv(installationName:"aline-financial"){
					sh "mvn clean verify sonar:sonar -Dsonar.projectKey=aline-financial" //${mvn}/bin/
				 
				
				}
			
		
			}
		}
	
	
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
					app = docker.build("transactionms-jj")
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
					docker.withRegistry('https://127780244987.dkr.ecr.us-east-1.amazonaws.com','ecr:us-east-1:aws-team'){
					app.push("latest")
					}
				}
            }
        }
		
		
		
		
    }
}