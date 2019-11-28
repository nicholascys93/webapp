node{
//def sonarUrl = 'sonar.host.url=http://172.31.30.136:9000'
   def mvn = tool (name: 'maven 3.6.1', type: 'maven') + '/bin/mvn'
   
   stage('SCM Checkout'){
    // Clone repo
	//git branch: 'master', 
	//credentialsId: 'github', 
	file:///C:/Program%20Files/Git/webapp/
   
 //  }


stage('Mvn Package'){
	   // Build using maven
	   
	   bat "${mvn} clean package deploy"
   }
   
   
   stage('deploy-to tomcat'){
       def tomcatDevIp = '172.20.4.13'
	   def tomcatHome = '/opt/tomcat/'
	   def webApps = tomcatHome+'webapps/'
	   def tomcatStart = "${tomcatHome}bin/startup.sh"
	   def tomcatStop = "${tomcatHome}bin/shutdown.sh"
	   
	   sshagent (credentials: ['tomcat-dev']) {
	      bat "scp -o StrictHostKeyChecking=no target/*.war admsde@${tomcatDevIp}:${webApps}sampleApp-2.0.1.RELEASE.war"
          bat "ssh admsde@${tomcatDevIp} ${tomcatStop}"
		  bat "ssh admsde@${tomcatDevIp} ${tomcatStart}"
       }
   }
}   
