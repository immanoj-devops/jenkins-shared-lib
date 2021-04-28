def call(String COMPONENT_NAME) {
pipeline {
    agent any
    stages {
        stage('Sonar Scan') {
            steps {

               sh "sonar-scanner  -Dsonar.projectKey=${COMPONENT_NAME} -Dsonar.sources=. -Dsonar.host.url=http://172.31.74.214:9000 -Dsonar.login=2f7e9cbf5079973a6f2258d0676c937738515ef7"             
                
                }
            }
        stage('Quality Gate'){
            steps {
              
                sh "sonar-quality-gate.sh admin Devops@135. 172.31.74.214 ${COMPONENT_NAME}"
              //STATUS=$(curl -u "admin:Devops@135." "http://172.31.74.214:9000/api/qualitygates/project_status?projectKey=${COMPONENT_NAME}" | jq  '.projectStatus.status' | xargs)
                
                }
            }
        stage('Lint Check'){
            steps {
              
                sh "echo TESTING Stage"
              
                }
            }
        }
    }
}