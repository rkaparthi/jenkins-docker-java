pipeline {
    agent any

    tools{
        maven "mymaven"
    }

    environment {
        ECR_REPO_NAME = 'my-iam-user-jenkins-ecr-repo'
        AWS_REGION = 'ap-south-1'
        AWS_ACCOUNT_ID = '571892790155'
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

        stage('configure AWS creds and ECR Login') {
            steps {
                script{
                    withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws-creds', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                        // some block
                        sh """
                        aws configure set aws_access_key_id $AWS_ACCESS_KEY_ID
                        aws configure set aws_secret_access_key $AWS_SECRET_ACCESS_KEY
                        aws configure set default.region $AWS_REGION
                        aws ecr get-login-password | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com
                        """
                    }  
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    // Build the Docker image
                    sh "docker build -t ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO_NAME}:${IMAGE_TAG} ."
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    // Build the Docker image
                    sh "docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO_NAME}:${IMAGE_TAG}"
                }
            }
        }
        
        stage('Deploy') {
            steps {
                script {
                    // Run the Docker container (Tomcat with WAR)
                    sh """
                        docker run -itd -p $port:8080 ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_REPO_NAME}:${IMAGE_TAG}
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
