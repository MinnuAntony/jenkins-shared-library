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

def call(String aws_account_id, String region, String ecr_repoName){
    
    sh """
     aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${region}.amazonaws.com
     docker push ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:latest
    """
}



// def call(String aws_account_id, String region, String ecr_repoName){
    
//     sh """
//      aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${region}.amazonaws.com
//      docker push ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:v1
//     """
// }
