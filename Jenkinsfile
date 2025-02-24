pipeline {
	agent any

	triggers {
    	githubPush()
  	}

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