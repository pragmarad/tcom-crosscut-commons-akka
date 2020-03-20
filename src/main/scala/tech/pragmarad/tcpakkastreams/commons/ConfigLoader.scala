package tech.pragmarad.tcpakkastreams.commons

import com.typesafe.config.{Config, ConfigFactory}

/**
 * HOCON Config helper
 * @author pragmarad
 * @since 2020-03-09
 */
object ConfigLoader {

  /**
   *
   * @return Config with all defaults (file name etc)
   */
  def getConfig(): Config = {
    getConfig("", Option.empty)
  }

  /**
   *
   * @param configAsString Initial config key/value pairs
   * @param configCustomFileOption Optional name of custom config file
   * @return Config instance
   */
  def getConfig(configAsString: String, configCustomFileOption: Option[String]): Config = {
    val configFile = configCustomFileOption.getOrElse(ConfigCommonConstants.Default.CONFIG_FILE)
    ConfigFactory.parseString(configAsString).withFallback(ConfigFactory.load(configFile))
  }

}
