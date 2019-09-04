name := "hello-akka-http"

version := "1.0"

scalaVersion := "2.12.9"

organization := "site.steveking"

import NativePackagerHelper._
enablePlugins(JavaAppPackaging, DockerPlugin)

libraryDependencies ++= {
  val akkaVersion = "2.5.25"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http"  % "10.1.8",
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "org.slf4j" % "slf4j-log4j12" % "1.7.28"
  )
}

javaOptions in Universal ++= Seq(
  "-Dconfig.file=/usr/local/etc/container.conf",
  "-Dlog4j.configuration=file:/usr/local/etc/log4j.properties"
)

packageName in Docker := packageName.value

version in Docker := version.value

dockerExposedPorts := List(8001)

dockerLabels := Map("maintainer" -> "no-reply@me.com")

dockerBaseImage := "openjdk"

dockerRepository := Some("sjking")

defaultLinuxInstallLocation in Docker := "/usr/local"

daemonUser in Docker := "daemon"

mappings in Universal ++= directory(baseDirectory.value / "src" / "main" / "resources")