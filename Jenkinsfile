node{
//def sonarUrl = 'sonar.host.url=http://172.31.30.136:9000'
   def mvn = tool (name: 'maven 3.6.1', type: 'maven') + '/bin/mvn'
   
  // stage('SCM Checkout'){
    // Clone repo
	//git branch: 'master', 
	//credentialsId: 'github', 
	//url: 'https://github.com/javahometech/myweb'
   
 //  }


stage('Mvn Package'){
	   // Build using maven
	   
	   sh "${mvn} clean package deploy"
   }
   
   
   stage('deploy-to tomcat'){
       def tomcatDevIp = '172.20.4.13'
	   def tomcatHome = '/opt/tomcat/'
	   def webApps = tomcatHome+'webapps/'
	   def tomcatStart = "${tomcatHome}bin/startup.sh"
	   def tomcatStop = "${tomcatHome}bin/shutdown.sh"
	   
	   sshagent (credentials: ['tomcat-dev']) {
	      sh "scp -o StrictHostKeyChecking=no target/*.war admsde@${tomcatDevIp}:${webApps}sampleApp-2.0.1.RELEASE.war"
          sh "ssh admsde@${tomcatDevIp} ${tomcatStop}"
		  sh "ssh admsde@${tomcatDevIp} ${tomcatStart}"
       }
   }
}   
