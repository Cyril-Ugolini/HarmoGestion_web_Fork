pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = 'dockerhub'       // ID du credential Docker Hub dans Jenkins
        DOCKER_IMAGE = 'cyril54000/harmogestion-web'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'github_pat',
                    url: 'https://github.com/Cyril-Ugolini/HarmoGestion_Web_Fork.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE}:latest ."
                }
            }
        }

        stage('Login Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS')]) {

                        sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                sh "docker push ${DOCKER_IMAGE}:latest"
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                docker stop harmoweb || true
                docker rm harmoweb || true
                docker run -d --name harmoweb -p 8080:8080 cyril54000/harmogestion-web:latest
                '''
            }
        }
    }
}
