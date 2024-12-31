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

        stage('Tag Docker Images for Heroku') {
            steps {
                sh '''
                docker tag service1:latest registry.heroku.com/$HEROKU_APP_SERVICE1/web
                docker tag service2:latest registry.heroku.com/$HEROKU_APP_SERVICE2/web
                '''
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
        stage('Test Heroku Authentication') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'heroku-api-key', variable: 'HEROKU_API_KEY')]) {
                        sh '''
                        heroku auth:token ${HEROKU_API_KEY}
                        heroku auth:whoami
                        '''
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
                         heroku auth:token ${HEROKU_API_KEY}
                         heroku container:login

                         # Set container stack for service1-app
                         heroku stack:set container --app $HEROKU_APP_SERVICE1

                         echo "Deploying service1 to Heroku"
                         heroku container:push web --app $HEROKU_APP_SERVICE1
                         heroku container:release web --app $HEROKU_APP_SERVICE1

                         # Set container stack for service2-app
                         heroku stack:set container --app $HEROKU_APP_SERVICE2

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