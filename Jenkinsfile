pipeline {
    agent any
    
    environment {
        repository = "goals11/repo"
        springImg = ""
        nginxImg = ""
        mysqlImg = ""
        djangoImg = ""
        ver = "0.7.$BUILD_NUMBER"
    }

    stages {
        stage('github clone') {
            steps {
                checkout scmGit(branches: [[name: '*/release']], extensions: [submodule(parentCredentials: true, reference: '', trackingSubmodules: true)], userRemoteConfigs: [[credentialsId: 'git-access-token', url: 'https://lab.ssafy.com/s08-bigdata-recom-sub2/S08P22C201']])
            }
            post {
                success {
                    echo 'Seccussfully Cloned Respository'
                }
                failure {
                    error 'this pipeline stops here...'
                }
            }
        }
        stage('build'){
            steps{
                dir('back/tink'){
                    sh'''
                        echo build start
                        chmod +x gradlew
                        ./gradlew clean bootJar
                        '''
                    script {
                        springImg = docker.build repository + ":spring$ver"
                    } 
                }
                echo 'build nginx-react build start'
                dir("front") {
                    script {
                        nginxImg = docker.build repository + ":react$ver"
                    }
                }
                dir('Django/recommendProject'){
                    script {
                        djangoImg = docker.build repository + ":django$ver"
                    }
                }
                
            }
            post {
                success {
                    mattermostSend (
                        color: "good", 
                        message: "Build SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Link to build>)"
                    )  
                    sh 'docker image prune --force'
                }
                failure {
                    mattermostSend (
                        color: "danger", 
                        message: "Build FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Link to build>)"
                    )
                }
            }
        }

        stage('stop container'){
            parallel{
                stage('stop react') {
                    steps {
                        sh 'docker ps -f name=tink-react -q | xargs --no-run-if-empty docker container stop'
                        sh 'docker container ls -a -f name=tink-react -q | xargs -r docker container rm'
                    }
                    post {
                        failure {
                            echo "not exist $nginxImg"
                        }
                    }
                }
                stage('stop spring'){
                    steps {
                        sh 'docker ps -f name=tink-spring -q | xargs --no-run-if-empty docker container stop'
                        sh 'docker container ls -a -f name=tink-spring -q | xargs -r docker container rm'
                    }
                    post {
                        failure {
                            echo "not exist $springImg"
                        }
                    }
                }
                stage('stop django'){
                    steps {
                        sh 'docker ps -f name=tink-django -q | xargs --no-run-if-empty docker container stop'
                        sh 'docker container ls -a -f name=tink-django -q | xargs -r docker container rm'
                    }
                    post {
                        failure {
                            echo "not exist $djangoImg"
                        }
                    }
                }

            }
        }
        
        
        stage('deploy'){
            steps{
                script{
                    try{
                        echo 'tink spring create container start'
                        sh "docker run -d -p 8081:8081 \
                                    --name tink-spring goals11/repo:spring$ver"

                        echo 'tink-nginx nginx create container start'
                        sh "docker run -d -p 3000:80 \
                                    --name tink-react \
                                    --rm goals11/repo:react$ver"
                                    
                        echo 'tink-django create container start'
                        sh "docker run -d -p 8000:8000 \
                                    --name tink-django \
                                    --rm goals11/repo:django$ver"
                    }catch(error){
                        print(error)
                    }
                }
            }
            post {
                success {
                    sh 'docker image prune --all --force'
                }
            }
            
        }
        

    }
    
}
