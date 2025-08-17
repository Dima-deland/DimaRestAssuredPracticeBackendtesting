pipeline {
    agent {label 'ec2-agent'}
    stages {
        stage('build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}