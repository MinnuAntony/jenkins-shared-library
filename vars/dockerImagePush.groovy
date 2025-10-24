def call(String project, String imageTag, String hubUser){
    withCredentials([usernamePassword(credentialsId: 'dockerhub',
                     usernameVariable: 'DOCKER_HUB_USER', 
                     passwordVariable: 'DOCKER_HUB_PASS')]) {
        sh """
        docker login -u '$DOCKER_HUB_USER' -p '$DOCKER_HUB_PASS'
        docker push ${hubUser}/${project}:${imageTag}
        docker push ${hubUser}/${project}:latest
        """
    }}




