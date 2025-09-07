pipeline {
    agent any

    tools {
        jdk 'Java-17'          // Name must match JDK configured in Jenkins Global Tool Config
        maven 'Maven-3.9.11'   // Name must match Maven configured in Jenkins Global Tool Config
    }

    environment {
        PATH = "${tool 'Maven-3.9.11'}/bin:${tool 'Java-17'}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/likhi-th123/SauceDemoAutomationProject.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat "mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml -e -X"
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
