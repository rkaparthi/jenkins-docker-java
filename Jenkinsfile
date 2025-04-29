pipeline {
    agent any

    tools{
        maven "mymaven"
    }

    environment {
        DOCKER_IMAGE = 'employee-service'
        DOCKER_REGISTRY = 'docker.io' // Use your registry here
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/rkaparthi/jenkins-docker-java.git'
            }
        }

        stage('Build WAR') {
            steps {
                script {
                    // Run Maven build to package WAR
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    // Build the Docker image
                    sh """
                        docker build -t ${DOCKER_REGISTRY}/${DOCKER_IMAGE}:${DOCKER_TAG} .
                    """
                }
            }
        }

        stage('Deploy to Docker') {
            steps {
                script {
                    // Run the Docker container (Tomcat with WAR)
                    sh """
                        docker run -d -p 80:8080 ${DOCKER_REGISTRY}/${DOCKER_IMAGE}:${DOCKER_TAG}
                    """
                }
            }
        }
    }

    post {
        success {
            echo "Application deployed successfully!"
        }
        failure {
            echo "Deployment failed."
        }
    }
}
