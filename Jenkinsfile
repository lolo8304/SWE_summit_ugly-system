pipeline {
    agent any
    options {
        buildDiscarder(logRotator(daysToKeepStr: '1'))
        disableConcurrentBuilds()
    }
    stages {
        stage('Build artifact') {
            agent {
                docker {
                    reuseNode true
                    image 'maven:3.5.3-jdk-8-slim'
                }
            }
            steps {
                sh '''
                mvn  clean install
                '''
            }
        }
        stage('SonarQube analysis') {
            withSonarQubeEnv('Sonar') {
              // requires SonarQube Scanner for Maven 3.2+
              sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
            }
          }
        stage('Deploy') {
            environment {
                SSH_KEY = credentials('SSH_KEY')
            }
            agent {
                docker {
                    reuseNode true
                    image 'williamyeh/ansible:ubuntu14.04'
                    args '-u root'
                }
            }
            steps {
                sh '''
                cd deploy
                cp $SSH_KEY ./labs-age-devsecops-slave.pem
                chmod 400 ./labs-age-devsecops-slave.pem
                ansible-playbook playbook.yml
                '''
            }
        }
    }

    post {
        always {
            deleteDir() /* clean up our workspace */
            cleanWs()
        }
    }
}