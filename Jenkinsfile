pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME' // Replace with your configured Maven version
        git 'Default' // Assuming Git is configured as Default
        dockerTool 'DOCKER_HOME'
    }

    environment {
        DB_IMAGE = 'mysql:8.0.35'
        BACKEND_IMAGE = 'webapp-mycloud-backend'
        FRONTEND_IMAGE = 'webapp-mycloud-frontend'
    }

    stages {
        stage('Checkout Backend') {
            steps {
                git url: 'https://github.com/JaanuGopan/WebApplicationProject-MyCloud-BackEnd.git', branch: 'main'
                sh 'ls -al' // Debugging: List files to ensure checkout
            }
        }

        stage('Build Backend') {
            steps {
                script {
                    sh 'ls -al'
                    sh 'mvn clean install'
                    sh 'docker build -t ${BACKEND_IMAGE} .'

                }
            }
        }

        stage('Checkout Frontend') {
            steps {
                git url: 'https://github.com/JaanuGopan/WebApplicationProject-MyCloud-FrontEnd.git', branch: 'main'
                sh 'ls -al' // Debugging: List files to ensure checkout
            }
        }

        stage('Build Frontend') {
            steps {
                script {
                    dir('frontend') {
                        sh 'docker build -t ${FRONTEND_IMAGE} .'
                    }
                }
            }
        }

        stage('Run Docker Compose') {
            steps {
                script {
                    writeFile file: 'docker-compose.yml', text: '''
                    version: '3'
                    services:
                      db:
                        image: mysql:8.0.35
                        environment:
                          MYSQL_ROOT_PASSWORD: root
                          MYSQL_DATABASE: cloudstoragemanagment
                          MYSQL_USER: root
                          MYSQL_PASSWORD: root
                        ports:
                          - "3306:3306"

                      backend:
                        image: ${BACKEND_IMAGE}
                        ports:
                          - "8080:8080"
                        environment:
                          SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/cloudstoragemanagment
                          SPRING_DATASOURCE_USERNAME: root
                          SPRING_DATASOURCE_PASSWORD: root
                        depends_on:
                          - db

                      frontend:
                        image: ${FRONTEND_IMAGE}
                        ports:
                          - "80:80"
                        depends_on:
                          - backend
                    '''
                    sh 'docker-compose up -d'
                }
            }
        }
    }

    post {
        always {
            sh 'docker-compose down'
        }
    }
}
