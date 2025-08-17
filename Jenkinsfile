pipeline {
    aws ec2-agent
    stages {
        stage('build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}