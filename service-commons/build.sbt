organization := "ch.datascience"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.11.8"

lazy val projectName = "service-commons"
name := projectName

lazy val root = Project(
  id   = projectName,
  base = file(".")
).dependsOn(
  core
)

lazy val core = RootProject(file("../graph-core"))

resolvers += DefaultMavenRepository

lazy val play_version = "2.5.14"
libraryDependencies += "com.typesafe.play" %% "play" % play_version
libraryDependencies += "com.typesafe.play" %% "play-json" % play_version
libraryDependencies += "com.typesafe.play" %% "play-ws" % play_version

lazy val java_jwt_version = "3.2.0"
libraryDependencies += "com.auth0" % "java-jwt" % java_jwt_version

lazy val janusgraph_version = "0.1.0"
libraryDependencies += "org.janusgraph" % "janusgraph-core" % janusgraph_version
