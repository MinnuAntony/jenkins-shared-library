def call(String project, String imageTag, String hubUser){

    sh """
    trivy image  ${hubUser}/${project}:latest > trivy-report.txt
    cat trivy-report.txt
    """
}