def call(String project, String imageTag, String hubUser){

    sh """
    trivy image  ${hubUser}/${project}:latest > trivy-report.txt
    cat trivy-report.txt
    """
}


// def call(String aws_account_id, String region, String ecr_repoName){
    
//     sh """
//     trivy image ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:latest > scan.txt
//     cat scan.txt
//     """
// }