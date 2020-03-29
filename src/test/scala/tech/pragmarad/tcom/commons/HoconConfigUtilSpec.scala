package tech.pragmarad.tcom.commons

import com.typesafe.config.Config
import org.scalatest.flatspec.AnyFlatSpec

/**
 * Tests for Config
 *
 * @author pragmarad
 * @since 2020-03-09
 */
class HoconConfigUtilSpec extends AnyFlatSpec {

  "Config" should "load value from file" in {
    val configFileName = "application.test.conf"

    val configPropName1 = "testapp.test.value"
    val configPropValue1 = "value1"

    val configSample2PropName = "local-sample2"
    val configSample2PropValue = "sample2"

    // Check load from file config:
    val cfgString = s"${configSample2PropName} = ${configSample2PropValue}" // Add own conf values if needed
    val cfg: Config = HoconConfigUtil.getConfig(cfgString, Option.apply(configFileName))
    val configPropResultValue1 = cfg.getString(configPropName1)
    assert(configPropResultValue1 == configPropValue1)

    // Check load from string config:
    val configPropResultSample2Value = cfg.getString(configSample2PropName)
    assert(configPropResultSample2Value == configSample2PropValue)
  }

  "Config" should "load value from file from system property" in {
    val configFileName = "application.test.conf"
    System.setProperty(ConfigCommonConstants.Default.CONFIG_FILE_SYS_PROP_NAME, configFileName)

    val configPropName1 = "testapp.test.value"
    val configPropValue1 = "value1"


    // Check load from file config:
    val cfgString = "" // Add own conf values if needed
    val cfg: Config = HoconConfigUtil.getConfig(cfgString, Option.empty)
    val configPropResultValue1 = cfg.getString(configPropName1)
    assert(configPropResultValue1 == configPropValue1)
    System.clearProperty(ConfigCommonConstants.Default.CONFIG_FILE_SYS_PROP_NAME)
  }

  "Default Config" should "load host and other properties" in {
    val configPropNameOfRootNode = "tcomakka"

    val configPropNameForHost = s"${configPropNameOfRootNode}.host"
    val expectedHostPropValue = "127.0.0.1"

    val configPropNameForSysName = s"${configPropNameOfRootNode}.actor-system-name"
    val expectedSysNamePropValue = "TcomSampleSys"

    val cfg: Config = HoconConfigUtil.getConfig()

    // Check host:
    val configPropNameForHostResultValue = cfg.getString(configPropNameForHost)
    assert(configPropNameForHostResultValue == expectedHostPropValue)

    // Check sys name:
    val configPropNameForSysNameResultValue = cfg.getString(configPropNameForSysName)
    assert(configPropNameForSysNameResultValue == expectedSysNamePropValue)
  }

}