pipeline {
    agent any

    tools {
        jdk 'Java-17'         
        maven 'Maven-3.9.11'   
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/likhi-th123/SauceDemoAutomationProject.git'
            }
        }

        stage('Build & Test') {
            steps {
                // Build dependencies and run tests
                bat 'mvn clean test -Dheadless=true'
            }
        }
        
        stage('Git Auto Push') {
            steps {
                script {
                    bat 'git config user.email "jenkins@example.com"'
                    bat 'git config user.name "Jenkins"'
                    bat 'git add .'
                    bat 'git commit -m "Jenkins build #${BUILD_NUMBER}" || echo No changes'
                    bat 'git push https://likhi-th123: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@github.com/likhi-th123/SauceDemoAutomationProject.git main'

                }
            }
        }
    }

    post {
        always {
            // Archive all test-output files
            archiveArtifacts artifacts: 'test-output/**', fingerprint: true

            // Publish Extent Report
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output',
                reportFiles: 'dashboard.html',
                reportName: 'Extent Report'
            ])
        }
    }
}
