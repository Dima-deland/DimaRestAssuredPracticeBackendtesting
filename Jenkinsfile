pipeline {
    agent {label 'ec2-agent'}
    environment {
        GITHUB_TOKEN=credentials('GitHub-token')
    }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}