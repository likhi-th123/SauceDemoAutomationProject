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
                bat "mvn clean test -DsuiteXmlFile=testng.xml"
            }
        }

        stage('Reports') {
            steps {
                junit '**/test-output/testng-results.xml'
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
