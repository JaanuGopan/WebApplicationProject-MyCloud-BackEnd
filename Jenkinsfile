pipeline {
    agent any

    tools {
        maven 'Maven3.9.8' // Replace with your configured Maven version
        git 'Default' // Assuming Git is configured as Default
        dockerTool 'DOCKER_HOME'
        jdk 'JAVA_HOME'
    }

    environment {
        DB_IMAGE = 'mysql:8.0.35'
        BACKEND_IMAGE = 'webapp-mycloud-backend2'
        FRONTEND_IMAGE = 'webapp-mycloud-frontend2'
    }

    stages {
        stage('Checkout Backend') {
            steps {
                dir('backend') {
                    git url: 'https://github.com/JaanuGopan/WebApplicationProject-MyCloud-BackEnd.git', branch: 'main'
                    sh 'ls -al' // Debugging: List files to ensure checkout
                }
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    script {
                        sh 'mvn clean install'
                        try {
                            sh 'docker build -t ${BACKEND_IMAGE} .'
                        } catch (Exception e) {
                            echo "Docker build failed: ${e}"
                            currentBuild.result = 'FAILURE'
                            error("Docker build failed")
                        }
                    }
                }
            }
        }

        stage('Checkout Frontend') {
            steps {
                dir('frontend') {
                    git url: 'https://github.com/JaanuGopan/WebApplicationProject-MyCloud-FrontEnd.git', branch: 'main'
                    sh 'ls -al' // Debugging: List files to ensure checkout
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    script {
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
                          - "8081:8080"
                        environment:
                          SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/cloudstoragemanagment
                          SPRING_DATASOURCE_USERNAME: root
                          SPRING_DATASOURCE_PASSWORD: root
                        depends_on:
                          - db

                      frontend:
                        image: ${FRONTEND_IMAGE}
                        ports:
                          - "3000:3000"
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
