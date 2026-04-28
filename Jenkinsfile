pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = 'dockerhub'
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
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    bat "docker build -t %DOCKER_IMAGE%:latest ."
                }
            }
        }
        stage('Login Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS')]) {
                        bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                    }
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                bat "docker push %DOCKER_IMAGE%:latest"
            }
        }
        stage('Deploy') {
            steps {
                bat "docker stop harmoweb || echo ok"
                bat "docker rm harmoweb || echo ok"
                bat "docker run -d --name harmoweb -p 8081:8081 cyril54000/harmogestion-web:latest"
            }
        }
    }
}
