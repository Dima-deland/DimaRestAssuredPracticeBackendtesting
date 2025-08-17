pipeline {
    agent {label 'built-in'}
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