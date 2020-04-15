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
	   
	   bat "mvn install"
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
	
	stage ('Fortify Clean') {
        bat "sourceanalyzer -b jenkinsdemo -clean"
    }
    
    stage ('Fortify Translate') {
	    
	    bat label: '', script: 'sourceanalyzer -b jenkinsdemo -cp "lib/*.jar" "src/**/*.java"'
	    //sourceanalyzer -b ava1.5 -cp "lib/*.jar" "src/**/*.java"
        //bat "sourceanalyzer -b java1.5 -source 1.5 ${source}"
	//    fortifyTranslate addJVMOptions: '', buildID: 'java1.5', excludeList: '', logFile: '', maxHeap: '', projectScanType: fortifyJava(javaAddOptions: '', javaClasspath: '', javaSrcFiles: 'C:\\Program Files (x86)\\Jenkins\\workspace\\Demo\\src', javaVersion: '1.8')
    }

    stage ('Fortify CloudScan Scan and upload') {
   //bat label: '', script: 'cloudscan.bat -sscurl https://sde-fssc-01.codesparks.ncs.com.sg:8443/ssc -ssctoken b8b2b68c-0a61-4ed8-9298-a78187241d75 start -upload  --application jenkinsdemo --application-version -b jenkinsdemo -uptoken b8b2b68c-0a61-4ed8-9298-a78187241d75 -scan -Xmx2G'
		bat "cloudscan.bat -sscurl ${clouscan_ssc} -ssctoken ${ssctoken} start -upload -versionid 53 -b jenkinsdemo -uptoken ${ssctoken} -scan -Xmx2G"
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
	

