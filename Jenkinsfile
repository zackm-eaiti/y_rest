pipeline {
	agent any

	triggers {
    	githubPush()
  	}

	// first stage
	stages {
		stage('Hello') {
			steps {
				checkout scm
				sh './gradlew bootJar'
			}
		}      
	}

	post {
		always {
			echo 'Exiting'
		}
	}
}