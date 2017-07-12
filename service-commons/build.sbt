organization := "ch.datascience"
name := "service-commons"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.11.8"

lazy val root = Project(
  id   = "service-commons",
  base = file(".")
)

resolvers += DefaultMavenRepository

lazy val play_version = "2.5.14"

libraryDependencies += "com.typesafe.play" %% "play" % play_version
libraryDependencies += "com.typesafe.play" %% "play-json" % play_version
