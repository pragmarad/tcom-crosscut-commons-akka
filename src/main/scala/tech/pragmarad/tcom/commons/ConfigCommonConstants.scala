package tech.pragmarad.tcom.commons

/**
 * Config related constants
 *
 * @author pragmarad
 * @since 2020-03-09
 */
object ConfigCommonConstants {
  val APP_PREFIX = "tcom"

  /**
   * Default values
   */
  object Default {
    val CONFIG_FILE: String = "application." + APP_PREFIX + ".conf"
    val HOST = "127.0.0.1"
    val PORT = "1661"
    val ACTOR_SYSTEM_NAME = "TcpAkkaStreamsActorSys"
  }

  /**
   * Config property names
   */
  object PropNames {
    private val PREFIX = APP_PREFIX + "."
    val HOST: String = PREFIX + "host"
    val PORT: String = PREFIX + "port"
    val ACTOR_SYSTEM_NAME: String = PREFIX + "actor-system-name"
  }


}
