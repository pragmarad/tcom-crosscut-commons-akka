package tech.pragmarad.tcom.commons

import org.scalatest.flatspec.AnyFlatSpec

/**
 * Tests for String utility
 *
 * @author pragmarad
 * @since 2020-03-09
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
