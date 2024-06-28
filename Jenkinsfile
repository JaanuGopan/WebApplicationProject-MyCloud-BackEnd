pipeline {
    agent any

    tools {
        maven 'Maven3.9.8'
        git 'Default'
        dockerTool 'DOCKER_HOME'
        jdk 'JAVA_HOME'
    }

    environment {
        DB_IMAGE = 'mysql:8.0.35'
        BACKEND_IMAGE = 'webapp-mycloud-backend2'
        FRONTEND_IMAGE = 'webapp-mycloud-frontend2'
        DOCKER_COMPOSE_PATH = '/usr/local/bin'
        DOCKER_USERNAME = credentials('DockerHub-UserName')
        DOCKER_PASSWORD = credentials('DockerHub-Password')

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

        stage('Docker Login') {
            environment {
                DOCKER_AUTH = "${DOCKER_USERNAME}:${DOCKER_PASSWORD}"
            }
            steps {
                script {
                    dockerLogin = "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
                    sh "${dockerLogin}"
                }
            }
        }

        /* stage('Docker Login') {
            steps {
                script {
                    sh "echo '${DOCKER_PASSWORD}' | docker login -u '${DOCKER_USERNAME}' --password-stdin"
                }
            }
        } */
       /*  stage('Docker Login') {
            steps {
                script {
                    sh 'docker login -u "${DOCKER_USERNAME}" -p "${DOCKER_PASSWORD}"'
                }
            }
        } */
        /* stage('Docker Login') {
            steps {
                script {
                    // Use Docker credential helper to securely login
                    sh 'docker-credential-osxkeychain get'
                    sh 'docker login -u "${DOCKER_USERNAME}" -p "$(docker-credential-osxkeychain get)"'
                }
            }
        } */


        stage('Dockerize Backend') {
            steps {
                dir('backend') {
                    script {
                        try {
                            sh 'docker build -t ${BACKEND_IMAGE} .'
                            sh 'docker push ${BACKEND_IMAGE}'  // Push the backend image to Docker Hub
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
                        sh 'docker build -t ${FRONTEND_IMAGE} .'
                        sh 'docker push ${FRONTEND_IMAGE}'  // Push the frontend image to Docker Hub
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    withEnv(["PATH+DOCKER_COMPOSE=${DOCKER_COMPOSE_PATH}"]) {
                        sh '''
                            docker-compose down
                            docker-compose up -d
                            '''
                    }
                    sh 'docker start mycloud-backend-2 || true'
                }
            }
        }
    }

    /* post {
        always {
            // Clean up Docker Compose
            withEnv(["PATH+DOCKER_COMPOSE=${DOCKER_COMPOSE_PATH}"]) {
                sh 'docker-compose down'
            }
        }
    } */
}
