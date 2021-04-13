job('NodeJS Docker example') {
    scm {
        git('https://github.com/pajerr/jenkins-opiskelu/job-dsl/nodejsdocker.groovy') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Testing')
            node / gitConfigEmail('rosk0ajt90awj@iawjt089watj0.watoiuawthji')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('ethernaldice/dokkeri-demoilu')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}