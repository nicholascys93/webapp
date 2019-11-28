node{
//def sonarUrl = 'sonar.host.url=http://172.31.30.136:9000'
   def mvn = tool (name: 'maven 3.6.1', type: 'maven') + '\\bin\\mvn'
   
   stage('SCM Checkout'){
	checkout scm
   }


stage('Mvn Package'){
	   // Build using maven
	   
	   bat "${mvn} install"
   }
   
   
   stage('deploy-to tomcat'){
      
	
//file:///C:/Program%20Files%20(x86)/Jenkins/workspace/Demo2/target		 
bat label: '', script: 'curl --upload-file "%CD%"\\target\\sampleApp-2.0.1.RELEASE.war "http://deployer:password@172.20.4.13:9090/manager/text/deploy?path=/sampleApp-2.0.1.RELEASE"'
		 
		 
		 
		 
		
   }
}   
