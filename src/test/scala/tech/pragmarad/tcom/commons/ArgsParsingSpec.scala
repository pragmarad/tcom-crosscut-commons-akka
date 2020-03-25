package tech.pragmarad.tcom.commons

import cats.implicits._
import com.monovore.decline.{Command, Opts}
import org.scalatest.flatspec.AnyFlatSpec
import org.slf4j.LoggerFactory

import scala.util.Try

/**
 * Tests for args parsing. <br>
 * NOTE: This test is more like an example of setting up and using args parsing.
 *
 * @author pragmarad
 * @since 2020-03-25
 */
class ArgsParsingSpec extends AnyFlatSpec {
  private val logger = LoggerFactory.getLogger(this.getClass.getName)

  /**
   * Tests sample args parsing with all correct params
   */
  "Args" should "be parsed" in {
    val args = Array[String](s"--${Constants.SAMPLE_HOST_PROP_NAME}", Constants.SAMPLE_HOST
      , s"--${Constants.SAMPLE_PORT_PROP_NAME}", Constants.SAMPLE_PORT)
    appCommand.parse(args) match {
      case Left(help) => {
        logger.error("Test args options failed. Help data:", help)
        fail()
      }
      case Right(appOptions) => {
        logger.info("Test args options: '{}'", appOptions)

        val option1Host = appOptions._1
        assert(option1Host == Constants.SAMPLE_HOST)

        val option2Port = appOptions._2
        assert(option2Port == Constants.SAMPLE_PORT.toInt)
      }
    }
  }

  /**
   * Tests sample args parsing with 1 correct and 1 incorrect param
   */
  "Args" should "be failed on parse because of wrong port format" in {
    val wronglyFormattedPort = "NotDigitalAtAllPort"
    val args = Array[String](s"--${Constants.SAMPLE_HOST_PROP_NAME}", Constants.SAMPLE_HOST
      , s"--${Constants.SAMPLE_PORT_PROP_NAME}", wronglyFormattedPort)

    appCommand.parse(args) match {
      case Left(help) => {
        logger.info("Test args options failed as expected. Help data (should contain incorrect port value): {}", help)
        // TODO: figure out one day how to show detailed failure in help
        //assert(help.toString().contains(wronglyFormattedPort))
      }
      case Right(appOptions) => {
        logger.error("Test args options: '{}' . But this test must fail!", appOptions)
        fail("As this test has wrongly formatted port, it must fail, but didn't!")
      }
    }
  }

  //------------------------
  // Supplement variables and methods:
  object Constants {
    val SAMPLE_HOST_PROP_NAME = "srvhost"
    val SAMPLE_PORT_PROP_NAME = "srvport"
    val SAMPLE_HOST = "testhost123"
    val SAMPLE_PORT = "123"
  }

  private val appOptions = buildAppOptions()

  private val appCommand = Command(
    name = "TestOfArgsParsing",
    header = "Test of args parsing."
  ) {
    appOptions
  }


  /**
   * TODO: consider some kind of map instead of brittle tuple
   *
   * @return Options of srvhost, srvport
   */
  private def buildAppOptions(): Opts[(String, Int)] = {
    // TODO: Figure out more type safe options extraction
    // Params: srvhost,srvport

    // 1. srvhost
    val srvhostFromConf = ConfigCommonConstants.Default.HOST
    val srvhostOrDefault = Opts.option[String](Constants.SAMPLE_HOST_PROP_NAME, short = "h", metavar = "string"
      , help = "Server host name or IP.").withDefault(srvhostFromConf)

    // 2. srvport
    val srvportFromConf = ConfigCommonConstants.Default.PORT
    // TODO: Change String to Int (current approach doesn't allow it)
    val srvportOrDefault = Opts.option[String](Constants.SAMPLE_PORT_PROP_NAME, short = "p", metavar = "int"
      , help = "Server port.").withDefault(srvportFromConf)
      .validate("Port must be positive int!")({ value => Try(value.toInt).isSuccess && value.toInt > 0 }).map {
      _.toInt
    }

    // Gather all options:
    val appOptions = (srvhostOrDefault, srvportOrDefault).tupled
    // TODO: Consider mapN to map into some kind of Map (positional config is brittle)

    appOptions
  }

  //------------------------

}
