package tech.pragmarad.tcpakkastreams.commons

import org.scalatest.flatspec.AnyFlatSpec

/**
 * Tests for String utility
 */
class StringUtilSpec extends AnyFlatSpec {

  "Non empty input" should "return false" in {
    val input = "nonempty"
    val expected = false

    val result = StringUtil.isTrimmedEmpty(input)
    assert(result == expected)
  }

  "Empty input" should "return true" in {
    val input = ""
    val expected = true

    val result = StringUtil.isTrimmedEmpty(input)
    assert(result == expected)
  }
}
