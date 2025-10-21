def call(String project, String imageTag, String hubUser){
    sh """
    docker build -t ${hubUser}/${project} .
    docker tag ${hubUser}/${project} ${hubUser}/${project}:${imageTag}
    docker tag ${hubUser}/${project} ${hubUser}/${project}:latest

    """
}