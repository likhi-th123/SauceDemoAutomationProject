pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'   
        jdk 'JAVA_HOME'      
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/your-username/your-repo.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                // Run Cucumber + TestNG tests
                sh 'mvn test'
            }
        }

        stage('Generate Report') {
            steps {
                // If you use ExtentReport or Allure
                sh 'mvn site'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'  // Publish TestNG/Cucumber results
            archiveArtifacts artifacts: 'target/**/*.html', allowEmptyArchive: true
        }
    }
}
