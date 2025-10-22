// def call(String project, String imageTag, String hubUser){
//     withCredentials([usernamePassword(credentialsId: 'dockerhub',
//                      usernameVariable: 'DOCKER_HUB_USER', 
//                      passwordVariable: 'DOCKER_HUB_PASS')]) {
//         sh """
//         docker login -u '$DOCKER_HUB_USER' -p '$DOCKER_HUB_PASS'
//         docker push ${hubUser}/${project}:${imageTag}
//         docker push ${hubUser}/${project}:latest
//         """
//     }}

// def call(String aws_account_id, String region, String ecr_repoName){
    
//     sh """
//      aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${region}.amazonaws.com
//      docker push ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:latest
//     """
// }

def call(String aws_account_id, String region, String ecr_repoName) {
    withCredentials([usernamePassword(credentialsId: 'aws-ecr-credentials', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY')]) {
        sh """
            # Configure AWS CLI with Jenkins credentials
            aws configure set aws_access_key_id $AWS_ACCESS_KEY_ID
            aws configure set aws_secret_access_key $AWS_SECRET_ACCESS_KEY
            aws configure set default.region ${region}

            # Authenticate Docker to AWS ECR
            aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${region}.amazonaws.com

            # Push the Docker image
            docker push ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:latest
        """
    }
}
