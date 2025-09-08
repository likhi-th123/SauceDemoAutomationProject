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

        stage('Build & Install') {
            steps {
                // Clean, install dependencies and update snapshots
                bat "mvn clean install -U"
            }
        }

        stage('Run Tests') {
            steps {
                // Run TestNG tests
                bat "mvn test -DsuiteXmlFile=testng.xml"
            }
        }

        stage('Reports') {
            steps {
                // Publish TestNG results
                junit '**/test-output/testng-results.xml'

                // Archive screenshots and reports
                archiveArtifacts artifacts: 'reports/**/*', fingerprint: true
                archiveArtifacts artifacts: 'screenshots/**/*', fingerprint: true
            }
        }
    }

    post {
        always {
            echo "Cleaning workspace..."
            deleteDir()
        }
    }
}
