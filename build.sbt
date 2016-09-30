name := """H2o"""
organization:="tabnerinc"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
javaJdbc,
  cache,
  javaWs,
  javaJpa,
  javaJdbc,
  "org.mongodb.morphia" % "morphia" % "0.107",
  javaWs,javaJpa,"org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final","org.mariadb.jdbc" % "mariadb-java-client" % "1.1.7",
  "com.google.code.gson" % "gson" % "2.3.1"
)

/*The following keys lets the eclipse to understand that the following project is Java Flavour*/
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses,

EclipseCreateSrc.ManagedResources)

EclipseKeys.preTasks := Seq(compile in Compile)

