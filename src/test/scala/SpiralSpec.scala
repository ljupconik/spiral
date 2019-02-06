import CalcUtils.manhattan_distance
import org.scalatest.{FlatSpec, Matchers}

class SpiralSpec extends FlatSpec with Matchers {

  "The value 1024" should
    "have a distance of 31 between it and the cell 1" in {
    manhattan_distance(1024) shouldBe 31
  }

  "when calculating the manhattan-distance with a nonpositive number, it" should "fail" in {
    the [IllegalArgumentException] thrownBy {
      val number = 0
      manhattan_distance(number)
    } should have message "requirement failed: The number 0 specified as an Argument must be an Integer > 0"
  }
}