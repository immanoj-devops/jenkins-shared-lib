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
                -Dsonar.host.url=http://18.207.241.47:9000 \
                -Dsonar.login=frontend
               '''
            }
        }
    }
}
}