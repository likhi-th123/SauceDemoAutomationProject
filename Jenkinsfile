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

        stage('Commit & Push Changes') {
            steps {
                script {
                    echo 'Checking for changes to push...'
                    bat """
                        git config user.email "jenkins@pipeline.com"
                        git config user.name "Jenkins CI"

                        git status
                        git add .

                        REM Commit only if there are changes
                        git diff --cached --quiet || git commit -m "Jenkins: Auto-commit after build"

                        REM Push to GitHub
                        git push https://likhi-th123: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@github.com/likhi-th123/SauceDemoAutomationProject.git main
                    """
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
