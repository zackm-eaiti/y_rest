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
				echo 'Hello World'
			}
		}
	}

	post {
		always {
			echo 'Exiting'
		}
	}
}