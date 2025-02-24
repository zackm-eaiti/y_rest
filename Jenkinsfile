pipeline {
	agent any

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