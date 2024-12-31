pipeline {
    agent any
     tools {
            jdk 'JDK_17' // This name must match the one in Global Tool Configuration
            maven 'M3'
        }
    environment {
        // Docker Hub credentials
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials-id')
        // GitHub repository URL
        GITHUB_REPO_URL = 'https://github.com/Ezekiel-Saul/worker-salary.git'
        // GitHub credentials for additional operations if needed
        GITHUB_CREDENTIALS = credentials('github-credentials')

         // Heroku app names
                HEROKU_APP_SERVICE1 = 'service1-app'
                HEROKU_APP_SERVICE2 = 'service2-app'
                // Heroku API key
                HEROKU_API_KEY = credentials('heroku-api-key')
    }

    stages {
        stage('Checkout') {
            steps {
                // Using the GITHUB_CREDENTIALS for authentication if needed
                git branch: 'master', url: "${GITHUB_REPO_URL}", credentialsId: 'github-credentials'
            }
        }


       stage('Build') {
                   steps {
                       withMaven(maven: 'M3') {
                           sh 'mvn clean package'
                       }
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

         stage('Deploy to Heroku') {
                    steps {
                        script {
                            withCredentials([string(credentialsId: 'heroku-api-key', variable: 'HEROKU_API_KEY')]) {
                                sh '''
                                echo "Logging into Heroku"
                                echo $HEROKU_API_KEY | heroku login -i

                                echo "Deploying service1 to Heroku"
                                heroku container:login
                                heroku container:push web --app $HEROKU_APP_SERVICE1
                                heroku container:release web --app $HEROKU_APP_SERVICE1

                                echo "Deploying service2 to Heroku"
                                heroku container:push web --app $HEROKU_APP_SERVICE2
                                heroku container:release web --app $HEROKU_APP_SERVICE2
                                '''
                            }
                        }
                    }
         }
    }
}