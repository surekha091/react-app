pipeline{
        agent any
        stages {
            stage('Checkout') {
                steps{    
                        git credentialsId: 'gitaccess', url: 'https://github.com/surekha091/react-app.git'
               }   
            }
            stage('Environment') {
                steps{    
                          sh 'git --version'
                          echo "Branch: ${env.BRANCH_NAME}"
                          sh 'docker -v'
                          sh 'printenv'
               }      
            }
        }
        post{
            success{
                slackSend(message :currentBuild.result)
            }
            failure{
                slackSend(message :currentBuild.result)
            }
        }
    }
