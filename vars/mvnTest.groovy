// def call(){
//     sh 'mvn test'
// }

// def call() {
//     sh '''
//        export MAVEN_OPTS="--add-opens=jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED"
//        mvn clean package -DskipTests
//     '''
// }
def call() {
    sh '''
                    export MAVEN_OPTS="--add-opens=jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED \
--add-opens=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED \
--add-opens=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED"
                    mvn clean package -DskipTests
                '''
}
