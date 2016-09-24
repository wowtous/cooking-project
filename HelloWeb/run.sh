#! /bin/bash

set -e

TOMCAT_HOME=$TOMCAT_HOME

if [[ $1 ]]; then
    TOMCAT_HOME=$1
fi

if [[ $TOMCAT_HOME ]]; then
    mvn clean package
    mv target/HelloWeb.war $TOMCAT_HOME/webapps
else
    echo "TOMCAT_HOME is not assign!"
fi
