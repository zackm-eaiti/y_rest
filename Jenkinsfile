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
			    script {
			        parallel(
			        	'Test 1': {
			        	    try {
                            	sh './gradlew test'
			        	    } catch (Exception e) {
			        	    	echo 'oopsies'
			        	    }
                        },
			        	'Test 2': {
                        	sh './gradlew test2'
                        }
			        )
			    }
			}
		}

		stage('Scan') {
			steps {
				echo "... Scanning ..."
				// SonarQube, OWASP, ..., analysis 
			}
		}
    }

    post {
		// Exiting
        always {
			sh './gradlew clean'
            echo 'Great Success'
        }
    }
}
