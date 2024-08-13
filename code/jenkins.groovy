pipeline {
    agent any
    
    tools {
        jdk 'jdk17'
        nodejs 'node16'
    }
    environment {
        SCANNER_HOME = tool 'sonar-scanner'
    }
    
    stages{
        stage('code-checkout'){
            steps{
               git branch: 'main', changelog: false, poll: false, url: 'https://github.com/abhipraydhoble/netflix.git' 
            }
        }
        stage('sonar-analysis'){
            steps{
               withSonarQubeEnv('sonar-scanner') {
                sh '''$SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=Netflix \
                    -Dsonar.projectKey=sonar-token'''
                } 
            }
        }
    }
}
