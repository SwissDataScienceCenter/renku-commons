organization := "ch.datascience"
name := "service-commons"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.11.8"

lazy val root = Project(
  id   = "service-commons",
  base = file(".")
)

resolvers += DefaultMavenRepository

lazy val play_version = "2.5.14"
libraryDependencies += "com.typesafe.play" %% "play" % play_version
libraryDependencies += "com.typesafe.play" %% "play-json" % play_version

lazy val java_jwt_version = "3.2.0"
libraryDependencies += "com.auth0" % "java-jwt" % java_jwt_version
