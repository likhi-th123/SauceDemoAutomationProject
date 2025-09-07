pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/likhi-th123/SauceDemoAutomationProject.git'
            }
        }
       stage('Build & Test') {
    steps {
        withMaven(maven: 'Maven-3.9.11') {
            bat "mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml"
        }
    }
}
    }
}
