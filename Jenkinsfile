node(label: 'slave test 2 layer'){
	
//def sonarUrl = 'sonar.host.url=http://172.31.30.136:9000'
   //def mvn = tool (name: 'maven 3.6.3', type: 'maven') + '\\bin\\mvn'
   //def source = "C:\\Program%20Files%20(x86)\\Jenkins\\workspace\\Demo2\\target\\sampleApp-2.0.1.RELEASE.war"
	
	def source = "C:\\Program Files (x86)\\Jenkins\\workspace\\Demo\\src"


def clouscan_ssc = "https://sde-fssc-01.codesparks.ncs.com.sg:8443/ssc"
def ssctoken = "b313fd42-f02e-48e6-aacf-d4f201b808ea"
   stage('SCM Checkout'){
	checkout scm
   }


stage('Mvn Package'){
	   // Build using maven
	   
	   bat "mvn install -Dmaven.test.failure.ignore=true"
   }
   
   
   stage('deploy-to tomcat'){		 
bat label: '', script: 'curl --upload-file "%CD%"\\target\\sampleApp-2.0.1.RELEASE.war "http://deployer:password@172.20.4.13:9090/manager/text/deploy?path=/sampleApp-2.0.1.RELEASE&update=true"'
   }
	
	
	//stage('Sonar Qube')
	//{
	//bat label: '', script: '''mvn sonar:sonar -Dsonar.projectKey=jenkinsdemo -Dsonar.host.url=http://172.20.4.25:9000 -Dsonar.login=ad0e5f82ebd92ade43d8e5d3a1a8ccc356f693f4'''
	
	//}
	//stage('QA nexus iq')
	//{
	//	nexusPolicyEvaluation advancedProperties: '', failBuildOnNetworkError: false, iqApplication: selectedApplication('jenkinsdemo'), iqStage: 'stage-release', jobCredentialsId: ''
	//}
	
	

    stage ('Fortify Scan and upload') {
  bat label: '', script: '''for /f "usebackq delims=" %%i in (`mvn org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression^=project.version -q -DforceStdout`) do (set APPVER=%%i) & for /f "usebackq delims=" %%i in (`mvn org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression^=project.artifactId -q -DforceStdout`) do (set APPNAME=%%i) & mvn com.fortify.sca.plugins.maven:sca-maven-plugin:clean & mvn package com.fortify.sca.plugins.maven:sca-maven-plugin:19.1.0:translate -DskipTests & mvn com.fortify.sca.plugins.maven:sca-maven-plugin:19.1.0:scan & mvn com.fortify.sca.plugins.maven:sca-maven-plugin:19.1.0:upload -Dfortify.ssc.authToken="e3c8e389-86a4-4d18-a70f-e9ca6d0ae6ee" -Dfortify.ssc.url="https://sde-fssc-01.codesparks.ncs.com.sg:8443/ssc" -Dfortify.ssc.applicationName="%APPNAME%" -Dfortify.ssc.applicationVersion="%APPVER%" & BIRTReportGenerator -template "Developer Workbook" -source "%WORKSPACE%\target\fortify\%APPNAME%-%APPVER%.fpr" -format PDF -showSuppressed -output "D:\FORTIFY-RESULTS-CODESPARKS\%APPNAME%-%APPVER%_Developer_Workbook.pdf"'''
    }    
	       
  
    
    stage('UITest') {
        echo 'Start tosca UI test...'
        bat label: '', script: '''cd c:\\toscaci
        del result.xml
        java -jar ToscaCIJavaClient.jar -m distributed -c "filter-seab.xml"
        copy result.xml "%WORKSPACE%" /y
        cd "%WORKSPACE%"
        '''


        junit 'result.xml'
        echo 'End tosca UI test...'
    }
	
	}
	

