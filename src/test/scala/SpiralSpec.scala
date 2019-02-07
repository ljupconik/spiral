import CalcUtils.{manhattan_distance, manhattan_dist_2_points}
import org.scalatest.{FlatSpec, Matchers}

class SpiralSpec extends FlatSpec with Matchers {

  "The value 368078" should
    "have a distance of 371 between it and the cell 1" in {
    manhattan_distance(368078) shouldBe 371
  }

  "The value 1024" should
    "have a distance of 31 between it and the cell 1" in {
    manhattan_distance(1024) shouldBe 31
  }

  "The value 12" should
    "have a distance of 3 between it and the cell 1" in {
    manhattan_distance(12) shouldBe 3
  }

  "The point Point(1,-1)" should
    "have a distance of 2 between it and the center Point(0,0)" in {
    manhattan_dist_2_points(Point(1,-1)) shouldBe 2
  }

  "The point Point(1,-1)" should
    "have a distance of 2 between it and the Point(-1,0)" in {
    manhattan_dist_2_points(Point(1,-1), Point(-1,0)) shouldBe 3
  }


  "when calculating the manhattan-distance with a nonpositive Integer, it" should "fail" in {
    the [IllegalArgumentException] thrownBy {
      val number = 0
      manhattan_distance(number)
    } should have message "requirement failed: The number 0 specified as an Argument must be an Integer > 0"
  }
}