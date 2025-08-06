#!/bin/sh
MAVEN_PROJECTBASEDIR=`dirname "$0"`
export MAVEN_PROJECTBASEDIR
java -classpath "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"   org.apache.maven.wrapper.MavenWrapperMain "$@"
