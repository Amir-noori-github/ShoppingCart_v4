pipeline {
    agent any

    tools {
        maven 'Maven-3.9.12'
    }

    environment {
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
        DOCKERHUB_CREDENTIALS_ID = 'Docker-Hub'
        DOCKERHUB_REPO = 'amirnoori1/shoppingcart-v3'
        DOCKER_IMAGE_TAG = 'latest'
    }

    stages {

        stage('Check') {
            steps {
                git branch: 'main', url: 'https://github.com/Amir-noori-github/ShoppingCart_v3.git'
            }
        }

        stage('Build Job') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Generate Report') {
            steps {
                bat 'mvn jacoco:report'
            }
        }

        stage('Publish Test Result') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}