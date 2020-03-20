package tech.pragmarad.tcpakkastreams.commons

/**
 * String utils
 * @author pragmarad
 * @since 2020-03-09
 */
object StringUtil {
  /**
   *
   * @param input
   * @return True if {{input}} is empty after trim
   */
  def isTrimmedEmpty(input: String) = input == null || input.trim.isEmpty

  /**
   *
   * @param input
   * @param fallbackValue
   * @return Either value itself or {{fallback}}
   */
  def getValueWithDefaultFallback(input: String, fallbackValue: String): String = {
    input match {
      case value if isTrimmedEmpty(value) => fallbackValue
      case value => value
    }
  }

}
