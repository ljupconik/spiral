import scala.annotation.tailrec

object CalcUtils {

  def manhattan_dist_2_points(p1:Point, p2:Point = Point(0,0)) = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y)

  def manhattan_distance(number:Int ): Int = {
    require(number > 0, s"The number $number specified as an Argument must be an Integer > 0" )
    @tailrec
    def loop(p_s:PointWithState , counter:Int):Int =
      p_s match {
        case PointWithState(p:Point,_:PointState.State) if (counter == number) => manhattan_dist_2_points(p)
        case m:PointWithState => loop(PointWithState.move(m), counter + 1)
      }
    loop(PointWithState(Point(0,0), PointState.LowerRightCorner) , 1)
  }

}
