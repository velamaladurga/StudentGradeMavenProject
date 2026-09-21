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

        stage('Deploy') {
            steps {

                bat '''
                if not exist C:\\StudentGradeDeployment mkdir C:\\StudentGradeDeployment

                copy /Y target\\StudentGradeMavenProject-1.0-SNAPSHOT.jar C:\\StudentGradeDeployment\\
                '''

                powershell '''
                $env:JENKINS_NODE_COOKIE = "student-grade-app"

                Get-CimInstance Win32_Process -Filter "Name = 'java.exe'" |
                    Where-Object {
                        $_.CommandLine -like '*StudentGradeMavenProject-1.0-SNAPSHOT.jar*'
                    } |
                    ForEach-Object {
                        Stop-Process -Id $_.ProcessId -Force
                    }

                Start-Process `
                    -FilePath "java.exe" `
                    -ArgumentList "-jar", "C:\\StudentGradeDeployment\\StudentGradeMavenProject-1.0-SNAPSHOT.jar" `
                    -WorkingDirectory "C:\\StudentGradeDeployment" `
                    -WindowStyle Hidden
                '''

                powershell '''
                Start-Sleep -Seconds 8

                if (Test-NetConnection localhost -Port 8081 -InformationLevel Quiet) {
                    Write-Host "Student Grade Application started successfully on port 8081."
                }
                else {
                    Write-Error "Student Grade Application failed to start on port 8081."
                }
                '''
            }
        }
    }

    post {

        success {

            echo 'Student Grade CI/CD Pipeline completed successfully!'

            emailext(
                subject: "SUCCESS: StudentGradeMavenPipeline #${BUILD_NUMBER}",
                body: """The Student Grade Maven CI/CD Pipeline completed successfully.

Build Number: ${BUILD_NUMBER}
Job Name: ${JOB_NAME}
Status: SUCCESS

Application deployed at:
http://localhost:8081/
""",
                to: "velamaladurga.23.cse@anits.edu.in"
            )
        }

        failure {

            echo 'Student Grade CI/CD Pipeline failed!'

            emailext(
                subject: "FAILED: StudentGradeMavenPipeline #${BUILD_NUMBER}",
                body: """The Student Grade Maven CI/CD Pipeline failed.

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