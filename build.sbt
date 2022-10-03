name := """play-to-tapir"""
organization := "com.ming"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.9"

libraryDependencies += guice
libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "com.softwaremill.sttp.tapir" %% "tapir-play-server" % "1.0.0",
  "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % "1.0.0"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.ming.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.ming.binders._"
