pipeline {
    agent any

    triggers {
        githubPush()
    }

    // first stage
    stages {  
            stage('Build') {
            steps {
                checkout scm
                sh './gradlew bootJar'
            }
        }

		stage('Test') {
			steps {
				sh './gradlew testSetup'
			}
		}

		stage('Scan') {
			steps {
				// SonarQube, OWASP, ..., analysis 
			}
		}
    }

    post {

		// Exiting
        always {
            echo 'Great Success'
        }
    }
}
