import org.scalatest.{FlatSpec, Matchers}

class MainSpec extends FlatSpec with Matchers{

  "when no Argument is specified, it" should "fail" in {
    the [IllegalArgumentException] thrownBy {

      Main.main(Array.empty)

    } should have message "You have to specify one Integer Argument"
  }

  "when more than 1 Argument is specified, it" should "fail" in {
    the [IllegalArgumentException] thrownBy {

      Main.main(Array("5","10"))

    } should have message "You can not specify more than one Integer Argument"
  }

  "when the Argument specified is not an integer, it" should "fail" in {
    the [java.lang.NumberFormatException] thrownBy {

      Main.main(Array("5.5"))

    } should have message "The specified Argument '5.5' must be an Integer"
  }

}
