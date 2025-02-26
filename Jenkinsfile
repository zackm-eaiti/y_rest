pipeline {
	agent {
		label 'demo'
	}

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
        always {
			sh './gradlew clean'
            echo 'Great Success'
        }
    }

}