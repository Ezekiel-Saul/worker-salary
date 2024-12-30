pipeline {
    agent any

    environment {
        // Docker Hub credentials
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials-id')
        // GitHub repository URL
        GITHUB_REPO_URL = 'https://github.com/Ezekiel-Saul/worker-salary.git'
        // GitHub credentials for additional operations if needed
        GITHUB_CREDENTIALS = credentials('github-credentials')
    }

    stages {
        stage('Checkout') {
            steps {
                // Using the GITHUB_CREDENTIALS for authentication if needed
                git branch: 'main', url: "${GITHUB_REPO_URL}", credentialsId: 'github-credentials'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials-id') {
                        sh 'docker-compose build'
                    }
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials-id') {
                        sh 'docker-compose push'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                // Here's where you would deploy. Since we're focusing on free solutions:
                // Deploy to a local test server or VM for free
                sh 'docker-compose up -d'
            }
        }
    }
}