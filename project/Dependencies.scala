import sbt._

object Dependencies {
  val javaVersion = "11" // NOTE: well, Java 8 can be used too, but let's enforce newer version..
  val curScalaVersion = "2.13.1"

  //----------
  // Command line option library:
  val cmdLineOptionVersion = "1.0.0"
  val cmdLineOptions = "com.monovore" %% "decline" % cmdLineOptionVersion
  //----------

  //----------
  // Loggers:
  object Log {
    val slf4jVersion = "1.7.30"
    val logbackVersion = "1.2.3"

    val slf4j = "org.slf4j" % "slf4j-api" % slf4jVersion
    val logbackClassic = "ch.qos.logback" % "logback-classic" % logbackVersion

    lazy val loggerDependencies: Seq[ModuleID] = Seq(slf4j, logbackClassic)

  }
  //----------

  //----------
  // Akka libs:
  object Akka {
    val akkaVersion = "2.6.4"
    val akkaGroup = "com.typesafe.akka"

    val akkaStream = akkaGroup %% "akka-stream" % akkaVersion
    val akkaStreamTyped = akkaGroup %% "akka-stream-typed" % akkaVersion
    // to be used slightly in followers example
    val akkaTyped = akkaGroup %% "akka-actor-typed" % akkaVersion

    // TODO: to put in Test?
    val akkaTestKit = akkaGroup %% "akka-stream-testkit" % akkaVersion % Test
    val akkaStreamTestKit = akkaGroup %% "akka-stream-testkit" % akkaVersion % Test

    // Config:
    val cfgVersion = "1.4.0"
    val cfg = "com.typesafe" % "config" % cfgVersion

    lazy val akkaDependencies: Seq[ModuleID] = Seq(cfg, akkaStream, akkaStreamTyped, akkaTyped, akkaTestKit, akkaStreamTestKit)
  }


  //----------
  // Test libs:
  object Tst {
    //----
    val scalaTestVersion = "3.1.1"
    val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion % Test

    val scalaCheckVersion = "1.14.0"
    val scalaCheck = "org.scalacheck" %% "scalacheck" % scalaCheckVersion % Test

    lazy val testDependencies: Seq[ModuleID] = Seq(scalaTest, scalaCheck)
  }

  //----------


}