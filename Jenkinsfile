pipeline {
	agent any											// Specify which Agent

	triggers {
		githubPush()
	}

	parameters {
    	booleanParam(name: 'deploy', defaultValue: false, description: 'check to deploy')
    }

    stages {
    	stage('Build') {
            steps {
                checkout scm
                sh '... Building ...'
                sh './gradlew bootJar'
            }
        }

		stage('Test') {
			steps {
				echo '... Testing ...'
				sh './gradlew test'
			}
		}

		stage('Scan') {
			steps {
				echo '... Scanning ...'
				// SonarQube, OWASP, ..., analysis
			}
		}

		stage('Deploy') {
			steps {
				echo '... Creating Deliverable ...'
				script {
		        	if (params.deploy) {
                		echo '... Deploying ...'
                 	}
                }
            }
		}
    }

    post {
   		success {
   			echo 'Great Success'
   		}
   		failure {
   			echo ':('
   		}
        always {
			sh './gradlew clean'
        }
    }

}