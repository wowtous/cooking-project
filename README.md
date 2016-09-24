## MAVEN + Spring example
1.build project with maven
```sh
mvn archetype:generate \
-DgroupId=org.darebeat \
-DartifactId=HelloWeb \
-DarchetypeArtifactId=maven-archetype-webapp \
-DarchetypeCatalog=local \
-DinteractiveMode=false
```

2.package project and publish
```sh
# package
cd HelloWeb && ./run.sh $M2_HOME

# start tomcat service
cd $TOMCAT_HOME && bin/startup.sh
# stop tomcat service
cd $TOMCAT_HOME && bin/shutdown.sh
```

3.open [http://localhost:8080/HelloWeb/index](localhost:8080/HelloWeb/index)
