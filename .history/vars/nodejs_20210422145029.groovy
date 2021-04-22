def call(String COMPONENT_NAME) {
pipeline {
    agent any
    stages {
        stage('Sonar Scan') {
            steps {
               sh '''
                    sonar-scanner \
                    -Dsonar.projectKey=${COMPONENT_NAME} \
                    -Dsonar.sources=. \
                    -Dsonar.host.url=http://172.31.74.214:9000 \
                    -Dsonar.login=AXj5W3WSZsEvmxA35znT

               '''
                }
            }
        }
    }
}