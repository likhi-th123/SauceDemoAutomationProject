pipeline {
    agent any

    tools {
        jdk 'Java-17'          // Configure JDK in Jenkins global tools
        maven 'Maven-3.9.11'   // Configure Maven in Jenkins global tools
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/likhi-th123/SauceDemoAutomationProject.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat "mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml"
            }
        }
    }
}

