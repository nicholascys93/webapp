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
       def tomcatDevIp = '172.20.4.13'
	   def tomcatHome = '/opt/tomcat/'
	   def webApps = tomcatHome+'webapps/'
	   def tomcatStart = "${tomcatHome}bin/startup.sh"
	   def tomcatStop = "${tomcatHome}bin/shutdown.sh"
	   
	   sshagent(['tomcat-dev']) {
         sh 'scp -o StrictHostKeyChecking=no target/*.war admsde@72.20.4.13:/opt/tomcat/webapps/'
      }
	   
	wget --http-user=deployer --http-password=password "http://172.20.4.13:9090/manager/text/deploy?war=file:/target/*.war&path=/SomeWar" -O -
   
	//   sshagent (credentials: ['tomcat-dev']) {
	//      bat "scp -o StrictHostKeyChecking=no target/*.war admsde@${tomcatDevIp}:${webApps}*.war"
        //  bat "ssh admsde@${tomcatDevIp} ${tomcatStop}"
	//	  bat "ssh admsde@${tomcatDevIp} ${tomcatStart}"
       //}
   }
}   
