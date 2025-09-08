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

        stage('Build') {
            steps {
                // Build dependencies without running tests
                bat "mvn clean install -DskipTests"
            }
        }

        stage('Run Cucumber Tests') {
            steps {
                // Run your Cucumber TestRunner
                bat "mvn test -Dcucumber.options='--plugin json:target/cucumber-reports/cucumber.json --plugin html:target/cucumber-reports/cucumber-html-report'"
            }
        }

        stage('Reports') {
            steps {
                // Publish cucumber results
                cucumber fileIncludePattern: '**/target/cucumber-reports/cucumber.json', trendsLimit: 10

                // Archive generated reports
                archiveArtifacts artifacts: 'target/cucumber-reports/**/*', fingerprint: true
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
