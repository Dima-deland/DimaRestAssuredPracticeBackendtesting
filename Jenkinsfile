pipeline {
    agent {label 'ec2-agent'}
    environment {
        GITHUB_TOKEN=credentials('GitHub-pat')
    }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}