organization := "ch.datascience"
name := "graph-mutation-service"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.11.8"

lazy val root = Project(
  id   = "graph-mutation-service",
  base = file(".")
).dependsOn(
  core,
  mutationWorker
).settings(
  projectDependencies +=
    (projectID in mutationWorker).value.exclude("org.slf4j", "slf4j-log4j12").exclude("org.slf4j", "slf4j-nop")
).enablePlugins(
  PlayScala
)

lazy val core = RootProject(file("../graph-core"))

lazy val mutationWorker = RootProject(file("../graph-mutation-worker"))

resolvers += DefaultMavenRepository

lazy val play_slick_version = "2.1.0"
lazy val rabbitmq_version = "4.1.0"
lazy val postgresql_version = "42.0.0"

libraryDependencies += filters
libraryDependencies += "com.typesafe.play" %% "play-slick" % play_slick_version
libraryDependencies += "org.postgresql" % "postgresql" % postgresql_version

lazy val scalatestplus_play_version = "2.0.0"

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % scalatestplus_play_version % Test

lazy val janusgraph_version = "0.1.0"

libraryDependencies += "org.janusgraph" % "janusgraph-cassandra" % janusgraph_version //% Runtime

import com.typesafe.sbt.packager.docker._

dockerBaseImage := "openjdk:8-jre-alpine"

dockerCommands ~= { cmds => cmds.head +: ExecCmd("RUN", "apk", "add", "--no-cache", "bash") +: cmds.tail }
// Replace entry point
dockerCommands ~= { cmds =>
  cmds.map {
    case ExecCmd("ENTRYPOINT", args@_*) => ExecCmd("ENTRYPOINT", args ++ Seq("-Dconfig.resource=application.docker.conf"): _*)
    case cmd => cmd
  }
}