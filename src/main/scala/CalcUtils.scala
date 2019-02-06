import scala.annotation.tailrec

object CalcUtils {

  def manhattan_distance(number:Int ): Int = {
    require(number > 0, s"The number $number specified as an Argument must be an Integer > 0" )
    @tailrec
    def loop(p_s:PointWithState , counter:Int):Int =
      p_s match {
        case PointWithState(Point(x,y),z:PointState.State) if (counter == number) => Math.abs(x) + Math.abs(y)
        case m:PointWithState => loop(PointWithState.move(m), counter + 1)
      }
    loop(PointWithState(Point(0,0), PointState.LowerRightCorner) , 1)
  }

}
