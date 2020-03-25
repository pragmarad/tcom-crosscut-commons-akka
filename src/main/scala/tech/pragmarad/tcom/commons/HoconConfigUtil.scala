package tech.pragmarad.tcom.commons

import com.typesafe.config.{Config, ConfigFactory}

/**
 * HOCON Config helper
 *
 * @author pragmarad, stack_overflow_folks
 * @since 2020-03-09
 */
object HoconConfigUtil {

  /**
   *
   * @return Config with all defaults (file name etc)
   */
  def getConfig(): Config = {
    getConfig("", Option.empty)
  }

  /**
   *
   * @param configAsString         Initial config key/value pairs
   * @param configCustomFileOption Optional name of custom config file
   * @return Config instance
   */
  def getConfig(configAsString: String, configCustomFileOption: Option[String]): Config = {
    val configFile = configCustomFileOption.getOrElse(ConfigCommonConstants.Default.CONFIG_FILE)
    ConfigFactory.parseString(configAsString).withFallback(ConfigFactory.load(configFile))
  }

  /**
   * Credits: idea was from https://stackoverflow.com/questions/52144157/providing-default-value-on-typesafe-config-getters
   *
   * @param config HOCON config
   * @param path   HOCON config property path
   * @return Option of String
   */
  def getStringOption(config: Config, path: String): Option[String] = if (config.hasPath(path)) {
    Some(config.getString(path))
  } else {
    None
  }


}
