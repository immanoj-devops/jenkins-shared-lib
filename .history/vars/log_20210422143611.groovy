def call(String GURL) {
pipeline {
    agent any 
           parameters {
                string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
            }
    stages {
        stage('fasfsfs'){
            steps {

                script {
                    log.info "WelcomeManoj MSG: ${GURL} and you love ${params.PERSON}"
                    log.warning "pleasDontTouchPo"
                }
            }
        }
    }
  }  
}

def warning(message) {
    echo "WARNING: ${message}"
}