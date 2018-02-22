name := """EMS"""
organization := "com.target"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies += guice

val jacksonVersion = "2.9.4"

libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
    "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
    "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
    "com.fasterxml.jackson.module" % "jackson-module-paranamer" % jacksonVersion,
    // test dependencies
    "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % jacksonVersion % "test",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-guava" % jacksonVersion % "test",
    "com.fasterxml.jackson.module" % "jackson-module-jsonSchema" % jacksonVersion % "test",
    "org.scalatest" %% "scalatest" % "3.0.0" % "test",
    "junit" % "junit" % "4.11" % "test"
)

libraryDependencies += "com.h2database" % "h2" % "1.4.196"

libraryDependencies += "org.hibernate" % "hibernate-core" % "5.2.9.Final"

libraryDependencies += "org.mongodb" % "mongo-java-driver" % "2.10.1"

