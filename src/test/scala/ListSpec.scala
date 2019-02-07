
import org.scalatest.{FlatSpec, Matchers}

import scala.annotation.tailrec

class ListSpec extends FlatSpec with Matchers {

  def manhattan_distance_list(number:Int ): List[(Int,(PointWithState,Int))] = {
    require(number > 0, s"The number $number specified as an Argument must be an Integer > 0" )
    @tailrec
    def loop(p_s:PointWithState , counter:Int, lst: List[(Int,(PointWithState,Int))]): List[(Int,(PointWithState,Int))] =
      p_s match {
        case PointWithState(Point(x,y),z:PointState.State) if (counter == number) => lst
        case m:PointWithState =>
          val nextPointWithSatate = PointWithState.move(m)
          val next_counter = counter + 1
          val manhattan_distance = Math.abs(nextPointWithSatate.point.x) + Math.abs(nextPointWithSatate.point.y)
          loop(nextPointWithSatate, next_counter,(next_counter,(nextPointWithSatate, manhattan_distance)) :: lst)
      }
    loop(PointWithState(Point(0,0), PointState.LowerRightCorner) , 1, List((1,(PointWithState(Point(0,0), PointState.LowerRightCorner), 0))) )
  }

  "The value 28" should
    "produce a list with the following sequence" in {

    val gen_list = manhattan_distance_list(28).reverse

    gen_list shouldEqual List((1,(PointWithState(Point(0,0),PointState.LowerRightCorner),0)),
                              (2,(PointWithState(Point(1,0),PointState.RightEdge),1)),
                              (3,(PointWithState(Point(1,1),PointState.UpperRightCorner),2)),
                              (4,(PointWithState(Point(0,1),PointState.UpperEdge),1)),
                              (5,(PointWithState(Point(-1,1),PointState.UpperLeftCorner),2)),
                              (6,(PointWithState(Point(-1,0),PointState.LeftEdge),1)),
                              (7,(PointWithState(Point(-1,-1),PointState.LowerLeftCorner),2)),
                              (8,(PointWithState(Point(0,-1),PointState.LowerEdge),1)),
                              (9,(PointWithState(Point(1,-1),PointState.LowerRightCorner),2)),
                              (10,(PointWithState(Point(2,-1),PointState.RightEdge),3)),
                              (11,(PointWithState(Point(2,0),PointState.RightEdge),2)),
                              (12,(PointWithState(Point(2,1),PointState.RightEdge),3)),
                              (13,(PointWithState(Point(2,2),PointState.UpperRightCorner),4)),
                              (14,(PointWithState(Point(1,2),PointState.UpperEdge),3)),
                              (15,(PointWithState(Point(0,2),PointState.UpperEdge),2)),
                              (16,(PointWithState(Point(-1,2),PointState.UpperEdge),3)),
                              (17,(PointWithState(Point(-2,2),PointState.UpperLeftCorner),4)),
                              (18,(PointWithState(Point(-2,1),PointState.LeftEdge),3)),
                              (19,(PointWithState(Point(-2,0),PointState.LeftEdge),2)),
                              (20,(PointWithState(Point(-2,-1),PointState.LeftEdge),3)),
                              (21,(PointWithState(Point(-2,-2),PointState.LowerLeftCorner),4)),
                              (22,(PointWithState(Point(-1,-2),PointState.LowerEdge),3)),
                              (23,(PointWithState(Point(0,-2),PointState.LowerEdge),2)),
                              (24,(PointWithState(Point(1,-2),PointState.LowerEdge),3)),
                              (25,(PointWithState(Point(2,-2),PointState.LowerRightCorner),4)),
                              (26,(PointWithState(Point(3,-2),PointState.RightEdge),5)),
                              (27,(PointWithState(Point(3,-1),PointState.RightEdge),4)),
                              (28,(PointWithState(Point(3,0),PointState.RightEdge),3)))
  }

}