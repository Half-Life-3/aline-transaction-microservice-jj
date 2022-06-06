pipeline {
    agent any

    stages {
        stage('Maven Test') {
            steps {
                echo 'Building..'
				sh "git submodule deinit --all -f"
				sh "git submodule init"
				sh "git submodule sync"
				sh"git submodule update"
				sh "mvn clean package"
				
				script{
					app = docker.build("transactionms")
				}
            }
        }
        stage('Maven Build') {
            steps {
                echo 'Haha, yeah...'
            }
        }
        stage('Docker Deploy') {
            steps {
                echo 'Deploying....'
				script{
						docker.withRegistry('https://445292818922.dkr.ecr.us-east-1.amazonaws.com','ecr:us-east-1:aws-creds'){
					app.push("latest")
					}
				}
            }
        }
    }
}