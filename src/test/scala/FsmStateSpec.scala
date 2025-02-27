import CalcUtils.manhattan_dist_2_points
import org.scalatest.{FlatSpec, Matchers}

import scala.annotation.tailrec

class FsmStateSpec extends FlatSpec with Matchers {

  def manhattan_distance_seq(number:Int , print_round: Boolean = false): Seq[(Int,(PointWithState,Int))] = {

    require(number > 0, s"The number $number specified as an Argument must be an Integer > 0" )

    // Find the number that will end the materialization of the sequence if we want to print the grid
    val number_to_use =
      if (print_round)
      {
        val ceil_sqrt_N = Math.ceil(Math.sqrt(number)).toInt

        (if (Math.sqrt(number).isValidInt) number
        else if (ceil_sqrt_N % 2 == 0) Math.pow(ceil_sqrt_N + 1 , 2)
        else Math.pow(ceil_sqrt_N , 2)).toInt
      }
      else number

    @tailrec
    def loop(p_s:PointWithState , counter:Int, seq: Seq[(Int,(PointWithState,Int))]): Seq[(Int,(PointWithState,Int))] =
      p_s match {
        case PointWithState(Point(x,y),z:PointState.State) if (counter == number_to_use) => seq
        case m:PointWithState =>
          val nextPointWithState = PointWithState.move(m)
          val next_counter = counter + 1
          val manhattan_distance_from_center = manhattan_dist_2_points(nextPointWithState.point)
          loop(nextPointWithState, next_counter, seq :+ (next_counter,(nextPointWithState,manhattan_distance_from_center)))
      }
    loop(PointWithState(Point(0,0), PointState.LowerRightCorner) , 1, Seq((1,(PointWithState(Point(0,0), PointState.LowerRightCorner), 0))) )
  }

  "The value 28" should
    "produce a list with the following sequence with elements (number,(PointWithState, mahattan-distance))" in {

    val gen_seq = manhattan_distance_seq(28)

    gen_seq  shouldEqual  Seq((1,(PointWithState(Point(0,0),PointState.LowerRightCorner),0)),
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

  println()
  println("-------------------")
  println("Printing the grid :")
  println("-------------------")

  val grid_map : Map[Point,Int] = manhattan_distance_seq(28 , print_round = true).map(elem => (elem._2._1.point , elem._1)).toMap
  val max_number = grid_map.size
  val pad_to = max_number.toString.length + 3
  val N = Math.sqrt(max_number).toInt
  val max_coordiate_value =  N / 2

  val list_points: List[Point] =
    {
      for {
      j <- max_coordiate_value to -max_coordiate_value by -1
      i <- -max_coordiate_value to max_coordiate_value
          }
      yield Point(i,j)
    }.toList

  @tailrec
  private def print_grid(list_points:List[Point] , counter:Int):List[Point] =
    list_points match {
      case Nil => Nil

      case head::tail if counter == N =>
        println(grid_map(head).toString)
        print_grid(tail , 1)

      case head::tail  =>
        print(grid_map(head).toString.padTo(pad_to, ' '))
        print_grid(tail , counter + 1)
    }

  println()
  print_grid(list_points,1)
  println()

}