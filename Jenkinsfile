node{
//def sonarUrl = 'sonar.host.url=http://172.31.30.136:9000'
   def mvn = tool (name: 'maven 3.6.1', type: 'maven') + '\\bin\\mvn'
   def source = ""%CD%"\\target\\sampleApp-2.0.1.RELEASE.war"


def clouscan_ssc = "https://sde-fssc-01.codesparks.ncs.com.sg:8443/ssc"
def ssctoken = "b8b2b68c-0a61-4ed8-9298-a78187241d75"
   stage('SCM Checkout'){
	checkout scm
   }


stage('Mvn Package'){
	   // Build using maven
	   
	   bat "${mvn} install"
   }
   
   
//   stage('deploy-to tomcat'){		 
//bat label: '', script: 'curl --upload-file "%CD%"\\target\\sampleApp-2.0.1.RELEASE.war "http://deployer:password@172.20.4.13:9090/manager/text/deploy?path=/sampleApp-2.0.1.RELEASE"'
//   }
	
	stage('Sonar Qube')
	{
	bat label: '', script: '''mvn sonar:sonar -Dsonar.projectKey=jenkinsdemo -Dsonar.host.url=http://172.20.4.25:9000 -Dsonar.login=ad0e5f82ebd92ade43d8e5d3a1a8ccc356f693f4'''
	
	}
	stage('QA nexus iq')
	{
		nexusPolicyEvaluation advancedProperties: '', failBuildOnNetworkError: false, iqApplication: selectedApplication('jenkinsdemo'), iqStage: 'stage-release', jobCredentialsId: ''
	}
	
	
	
	
    
    stage ('Fortify Clean') {
        bat "sourceanalyzer -b java1.5 -clean"
    }
    
    stage ('Fortify Translate') {
        bat "sourceanalyzer -b java1.5 -source 1.5 ${source}"
    }

    stage ('Fortify CloudScan Scan and Upload') {
        bat "cloudscan.bat -sscurl ${clouscan_ssc} -ssctoken ${ssctoken} start -upload -versionid 2 -b java1.5 -uptoken ${ssctoken} -scan -Xmx2G"
    }
    
	
	stage('UITest') {
        echo 'Start tosca UI test...'
        dir("C:/toscaci")
        {
            sh '''
            java -jar ToscaCIJavaClient.jar -m distributed -c "filter-seab.xml"
            cp result.xml "${WORKSPACE}"
            '''
        }
        junit 'result.xml'
        echo 'End tosca UI test...'
    }
}   
