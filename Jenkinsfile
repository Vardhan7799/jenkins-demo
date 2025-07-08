pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
    }

    environment {
        DB_URL = 'jdbc:mysql://host.docker.internal:3306/bootex2?createDatabaseIfNotExist=true'
        DB_USERNAME = 'root'
        DB_PASSWORD = 'Vardhan36Shift'
        DOCKER_IMAGE = 'rajyavardhan36/student_app'
        DOCKER_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout'){
            steps{
                checkout scmGit(branches: [[name: '*/development']], extensions: [], userRemoteConfigs: [[credentialsId: '5617bf8c-5ecf-4e69-8e3b-8c5df32fb461', url: 'https://github.com/Vardhan7799/jenkins-demo']])
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install -Dspring.datasource.url=$DB_URL -Dspring.datasource.username=$DB_USERNAME -Dspring.datasource.password=$DB_PASSWORD'
            }
        }
        stage('Build Docker image'){
            steps{
                sh 'docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .'
            }
        }
        stage('Push to Docker Hub'){
            steps{
                withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    sh "docker login -u rajyavardhan36 -p ${dockerhubpwd}"

                    sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                }
            }
        }
        stage('Deploy using Docker Compose'){
            steps {
                sh 'TAG=${BUILD_NUMBER} docker compose pull'
                sh 'TAG=${BUILD_NUMBER} docker compose up -d'
            }
        }
    }
}