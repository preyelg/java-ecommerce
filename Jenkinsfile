pipeline {
  agent any
  environment {
    DOCKER_IMAGE = "preyelg/ecommerce-backend:latest"
  }
  stages {
    stage('Clone Repo') {
      steps {
        git 'https://github.com/preyelg/java-ecommerce.git'
      }
    }
    stage('Build') {
      steps {
        sh './mvnw clean package -DskipTests'
      }
    }
    stage('Docker Build & Push') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
          sh """
            docker build -t $DOCKER_IMAGE .
            echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
            docker push $DOCKER_IMAGE
          """
        }
      }
    }
    stage('Deploy to Kubernetes') {
      steps {
        sh 'kubectl apply -f k8s/'
      }
    }
  }
}
