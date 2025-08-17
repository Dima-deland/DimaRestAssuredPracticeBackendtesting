pipeline {
    agent {label 'built-in'}
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