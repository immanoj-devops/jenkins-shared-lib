def call(String COMPONENT_NAME) {
pipeline {
    agent any
    environment {
        MAJOR_VERSION=1
    }
    stages {
        stage('Compiling the Code ') {
            steps {

               sh "mvn compile"             
                
                }
            }

        stage('Sonar Scan') {
            steps {
               sh "pwd; ls -ltra"
               sh "sonar-scanner  -Dsonar.projectKey=${COMPONENT_NAME} -Dsonar.java.binaries=./target -Dsonar.sources=. -Dsonar.host.url=http://172.31.74.214:9000 -Dsonar.login=2f7e9cbf5079973a6f2258d0676c937738515ef7"             
                
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
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {                           
                        sh "mvn checkstyle:check"
                    }   
                }
            }
        stage('Packaging the artifacts'){
            steps {
                    sh "mvn package; ls -ltr target/"
                    sh "cp target/${COMPONENT_NAME}-1.0.jar ${COMPONENT_NAME}.${MAJOR_VERSION}.${BUILD_NUMBER}.zip"
                }
            }
        stage('Uploading the artifacts'){
            steps { 
                    sh "ls -ltr target/"
                    sh "curl -v -u admin:Devops@135. --upload-file target/${COMPONENT_NAME}.${MAJOR_VERSION}.${BUILD_NUMBER}.zip http://172.31.77.55:8081/repository/${COMPONENT_NAME}/"

                }
            }

        }
    }
}


