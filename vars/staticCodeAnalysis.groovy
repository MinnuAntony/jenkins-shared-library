
// def call(credentialsId){

//     withSonarQubeEnv(credentialsId: credentialsId) {
//          sh 'mvn clean package sonar:sonar'
//     }
// }

def call() {
    withSonarQubeEnv('sonar-api') {  // 'sonarqube' is the name configured in Jenkins global tool config
        sh '''
            echo "Running SonarQube Static Code Analysis..."
            mvn clean verify -DskipTests sonar:sonar \
                -Dsonar.projectKey=kubernetes-configmap-reload \
                -Dsonar.projectName="SpringBoot Sample" \
                -Dsonar.host.url=$SONAR_HOST_URL \
                -Dsonar.login=$SONAR_AUTH_TOKEN
        '''
    }

    
}

