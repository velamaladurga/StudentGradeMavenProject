pipeline {

    agent any

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
    }

    post {

        success {
            echo 'Student Grade CI Pipeline completed successfully!'

            emailext(
                subject: "SUCCESS: StudentGradeMavenPipeline #${BUILD_NUMBER}",
                body: """The Student Grade Maven Pipeline completed successfully.

Build Number: ${BUILD_NUMBER}
Job Name: ${JOB_NAME}
Status: SUCCESS
""",
                to: "velamaladurga.23.cse@anits.edu.in"
            )
        }

        failure {
            echo 'Student Grade CI Pipeline failed!'

            emailext(
                subject: "FAILED: StudentGradeMavenPipeline #${BUILD_NUMBER}",
                body: """The Student Grade Maven Pipeline failed.

Build Number: ${BUILD_NUMBER}
Job Name: ${JOB_NAME}
Status: FAILED

Please check the Jenkins Console Output for details.
""",
                to: "velamaladurga.23.cse@anits.edu.in"
            )
        }
    }
}