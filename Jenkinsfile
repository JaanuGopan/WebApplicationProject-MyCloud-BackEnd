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
        DOCKER_CREDENTIAL = credentials('DockerHub')

        BACKEND_CONTAINER_NAME = 'mycloud-backend-2'
        FRONTEND_CONTAINER_NAME = 'mycloud-frontend-2'
        DATABASE_CONTAINER_NAME = 'myclouddb'
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
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DockerHub', usernameVariable: 'DOCKER_CREDENTIAL_USR', passwordVariable: 'DOCKER_CREDENTIAL_PSW')]) {
                        sh "echo '${DOCKER_CREDENTIAL_PSW}' | docker login -u '${DOCKER_CREDENTIAL_USR}' --password-stdin"
                    }
                }
            }
        }

        stage('Dockerize Backend') {
            steps {
                dir('backend') {
                    script {
                        try {
                            sh "docker build -t ${BACKEND_IMAGE} ."
                            sh "docker tag ${BACKEND_IMAGE} janugopan/mycloud:backend"

                            // sh "docker push janugopan/mycloud:backend"
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
                            sh "docker build -t ${FRONTEND_IMAGE} ."
                            sh "docker tag ${FRONTEND_IMAGE} janugopan/mycloud:frontend"

                            // sh "docker push janugopan/mycloud:frontend"
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
                        sh '''
                            docker-compose down
                            docker-compose up -d
                        '''
                    }
                }
            }
        }

        stage('Check and Restart All Containers') {
            steps {
                script {
                    sh 'sleep 10' // Wait for Docker services to stabilize

                    def containers = [
                        DATABASE_CONTAINER_NAME,
                        BACKEND_CONTAINER_NAME,
                        FRONTEND_CONTAINER_NAME
                    ]

                    containers.each { container ->
                        def isRunning = sh(script: "docker ps -f name=${container} | grep -q ${container}", returnStatus: true)
                        if (isRunning == 0) {
                            echo "${container} is already running."
                        } else {
                            echo "${container} is not running. Restarting..."
                            sh "docker start ${container}"
                            if(container == DATABASE_CONTAINER_NAME){
                                sh 'sleep 10'
                            }
                        }
                    }
                }
            }
        }
    }

    /* post {
        always {
            script {
                // Clean up Docker Compose
                sh 'sleep 10 && docker-compose down || true'
            }
        }
    } */
}
