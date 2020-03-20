// TCP Akka Streams Commons PoC
//   Inception: 2020-03-07

import Dependencies._
import sbt.Keys.name

name := """tcom-crosscut-commons-akka"""

licenses +=("Apache-2.0", url("http://opensource.org/licenses/apache2.0.php"))
licenses := List(
  ("Apache License, Version 2.0",
    url("https://www.apache.org/licenses/LICENSE-2.0"))
)
homepage := Some(url("https://github.com/pragnmarad/tcom-crosscut-commons-akka"))

pomExtra :=
  <scm>
    <connection>
      scm:git:git://github.com/pragnmarad/tcom-crosscut-commons-akka.git
    </connection>
    <url>
      https://github.com/pragnmarad/tcom-crosscut-commons-akka
    </url>
  </scm>
    <developers>
      <developer>
        <id>pragnmarad</id>
        <name>PragnmaRAD</name>
        <email>pragmarad.tech@gmail.com</email>
      </developer>
    </developers>

lazy val commonSettings = Seq(
  organization := "tech.pragmarad.tcpakkastreams",
  scalaVersion := curScalaVersion,
  // Test options:
  testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v")),

  //---------
  // Resolvers:

  //---------
  //---------
  // - Reduce the maximum number of errors shown by the Scala compiler.
  maxErrors := 50,
  // - Increase the time between polling for file changes when using continuous execution.
  //pollInterval := 1000,
  // - Append several options to the list of options passed to the Java compiler.
  //javacOptions in Compile ++= Seq("-source", javaVersion, "-target", javaVersion
  //  , "-Xlint:unchecked", "-Xlint:deprecation", "-g:lines,source,vars"),
  javacOptions in doc in Compile := Seq("-Xdoclint:none"),

  // Disable sbt log buffering so you can enjoy ScalaTest's built-in event buffering algorithm
  logBuffered in Test := false,
  // If no net connection expected, set true temporarily:
  offline := false,
  // - Include Scala version in output paths and artifacts.
  crossPaths := true,
  //---------

  //---------
  // Settings for || execution:
  // - Fork a new JVM for 'run' and 'test:run'
  fork in Runtime := false,
  // - Fork a new JVM for 'test:run', but not 'run'.
  fork in Test := true,
  // - Only use (or not) a single thread for building.
  parallelExecution := false,
  // - Execute tests in the current project serially.
  //   Tests from other projects may still run concurrently.
  parallelExecution in Test := false,
  //---------

  // - Only show warnings and errors on the screen for compilations.
  //   This applies to both test:compile and compile and is Info by default.
  logLevel in compile := Level.Warn,
  // - Only show warnings and errors on the screen for all tasks (the default is Info).
  //   Individual tasks can then be more verbose using the previous setting.
  logLevel := Level.Info,
  // - Only store messages at info and above (the default is Debug).
  //   This is the logging level for replaying logging with 'last'.
  persistLogLevel := Level.Warn,
  // - Only show 10 lines of stack traces
  traceLevel := 10,
  // - Publish test jar, sources, and docs
  publishArtifact in Test := false,
  // Use sbt updateClassifiers
  transitiveClassifiers := Seq("sources", "javadoc")
)

// - Append -deprecation to the options passed to the Scala compiler.
scalacOptions in Compile ++= Seq(
  "-feature",
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-Xlog-reflective-calls",
  "-Xlint",
  //"-Yno-adapted-args", NOTE: compiler didn't like it..
  "-Ywarn-dead-code",
  "-Ywarn-value-discard",
  //"-Xfuture",
  //"-Xexperimental"
)
scalacOptions in Test += "-Ywarn-value-discard:false" // since this often appears in expectNext(expected) testing style in streams


// Common dep-s:
libraryDependencies in Global ++= Log.loggerDependencies
libraryDependencies in Global ++= Tst.testDependencies
libraryDependencies in Global ++= Akka.akkaDependencies

//---------
// Module aggregator:
lazy val root = (project in file(".")).settings(commonSettings: _*)
//---------

// Init:
initialize := {
  //val _ = initialize.value
  val javaVersionOfRun = sys.props("java.specification.version")
  if (javaVersionOfRun.toInt < javaVersion.toInt)
    sys.error(s"Java 11+ is required for this project. But current version is ${javaVersionOfRun}")
}