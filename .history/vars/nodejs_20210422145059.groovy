def call(String COMPONENT_NAME) {
pipeline {
    agent any
    stages {
        stage('Sonar Scan') {
            steps {
               sh '''
                    sonar-scanner \
                    -Dsonar.projectKey=frontend \
                    -Dsonar.sources=. \
                    -Dsonar.host.url=http://172.31.74.214:9000 \
                    -Dsonar.login=2f7e9cbf5079973a6f2258d0676c937738515ef7

               '''
                }
            }
        }
    }
}