def call(String project, String imageTag, String hubUser){
    sh """
    docker rmi  ${hubUser}/${project}:${imageTag}
    docker rmi  ${hubUser}/${project}:latest
    """
}