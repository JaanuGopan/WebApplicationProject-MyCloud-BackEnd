pipeline {
    agent any

    tools {
        maven 'Maven3.9.8'
        git 'Default'
        jdk 'JAVA_HOME'
    }

    environment {
        DB_IMAGE = 'mysql:8.0.35'
        BACKEND_IMAGE = 'webapp-mycloud-backend2'
        FRONTEND_IMAGE = 'webapp-mycloud-frontend2'
        DOCKER_COMPOSE_PATH = '/usr/local/bin'
    }

    stages {
        stage('Checkout Backend') {
            steps {
                dir('backend') {
                    git url: 'https://github.com/JaanuGopan/WebApplicationProject-MyCloud-BackEnd.git', branch: 'main'
                    sh 'ls -al'
                }
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    script {
                        sh 'mvn clean install'
                    }
                }
            }
        }

        stage('Dockerize Backend') {
            steps {
                dir('backend') {
                    script {
                        try {
                            // Docker build and push
                            withDockerRegistry(credentialsId: 'DockerHub') {
                                sh "docker build -t ${BACKEND_IMAGE} ."
                                sh "docker push janugopan/mycloud:backend"
                            }
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
                    sh 'ls -al'
                }
            }
        }

        stage('Build And Dockerize Frontend') {
            steps {
                dir('frontend') {
                    script {
                        try {
                            // Docker build, tag, and push
                            withDockerRegistry(credentialsId: 'DockerHub') {
                                sh "docker build -t ${FRONTEND_IMAGE} ."
                                sh "docker tag ${FRONTEND_IMAGE} janugopan/mycloud:frontend"
                                sh "docker push janugopan/mycloud:frontend"
                            }
                        } catch (Exception e) {
                            echo "Docker build failed: ${e}"
                            currentBuild.result = 'FAILURE'
                            error("Docker build failed")
                        }
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    withEnv(["PATH+DOCKER_COMPOSE=${DOCKER_COMPOSE_PATH}"]) {
                        // Ensure Docker Compose files are available and valid
                        sh '''
                            docker-compose down
                            docker-compose up -d
                            '''
                    }
                    // Start containers as needed
                    sh 'docker start mycloud-backend-2 || true'
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker Compose resources after deployment
            script {
                withEnv(["PATH+DOCKER_COMPOSE=${DOCKER_COMPOSE_PATH}"]) {
                    sh 'docker-compose down'
                }
            }
        }
    }
}
