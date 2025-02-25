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
            post {
                success {
                    publishChecks(name: 'Java Build', conclusion: 'SUCCESS', summary: 'Java app built successfully.')
                    echo '============Build Completed============'
                }
                failure {
                    publishChecks(name: 'Java Build', conclusion: 'FAILURE', summary: 'Java app failed to build!')
                    echo '⚠️===========Build Failed===========⚠️'
                }
            }
        }
    }

    post {
        always {
            echo 'Exiting'
        }
    }
}
