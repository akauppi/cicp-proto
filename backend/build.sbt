// build.sbt
//
organization := "github.akauppi"
version := "0.0.0-SNAPSHOT"
scalaVersion := "2.12.8"    // tbd. upgrade

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "utf8",
  "-feature",
  "-unchecked",
  "-language", "postfixOps"
)

libraryDependencies += "com.typesafe" % "config" % "1.3.4"

// Logging
libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)

val akkaVer = "2.5.23"
//val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVer
val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVer

val akkaHttpVer = "10.1.9"
val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVer
val akkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVer % Test

//val circeVersion = "0.9.2"
//val circeGeneric = "io.circe" %% "circe-generic" % circeVersion   // @JsonCodec

//val akkaHttpCirce = "de.heikoseeberger" %% "akka-http-circe" % "1.20.0"

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8" % Test

libraryDependencies ++= Seq(
  //akkaActor,
  akkaStream,
  akkaHttp,
  scalaTest
)
