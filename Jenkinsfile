pipeline {
    agent any

    tools {
        jdk 'Java-17'          // Must match Jenkins JDK name
        maven 'Maven-3.9.11'   // Must match Jenkins Maven name
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/likhi-th123/SauceDemoAutomationProject.git'
            }
        }

        stage('Build & Test') {
            steps {
                // Run TestNG suite
                bat "mvn clean test -DsuiteXmlFile=testng.xml"
            }
        }

        stage('Publish Reports') {
            steps {
                // Publish TestNG results
                junit 'target/surefire-reports/*.xml'

                // Archive Cucumber HTML report
                publishHTML(target: [
                    allowMissing: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'cucumber-report.html',
                    reportName: 'Cucumber Report'
                ])

                // Archive screenshots
                archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
        }
        success {
            echo 'Build & Tests completed successfully ✅'
        }
        failure {
            echo 'Build failed ❌ — Check reports and logs.'
        }
    }
}

