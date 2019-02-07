abstract class FsmState[T <: Enumeration](val state: T)


object PointState extends Enumeration{
  type State = Value
  val LowerRightCorner, RightEdge, UpperRightCorner, UpperEdge , UpperLeftCorner, LeftEdge, LowerLeftCorner, LowerEdge   = Value
}


case class Point(x:Int, y:Int)

case class PointWithState(point: Point, pointState: PointState.State) extends FsmState(state =  PointState)


object PointWithState{

  private def move_right(p: Point) = Point(p.x + 1 , p.y )

  private def move_up(p: Point) = Point(p.x , p.y + 1 )

  private def move_left(p: Point) = Point(p.x - 1 , p.y )

  private def move_down(p: Point) = Point(p.x , p.y - 1 )


  def move(ps: PointWithState) : PointWithState = {

    ps.pointState match {
      case PointState.LowerRightCorner =>
        PointWithState( move_right(ps.point), PointState.RightEdge)

      case PointState.RightEdge =>
        val new_point = move_up( ps.point)
        val new_state = if (new_point.x == new_point.y && new_point.x > 0 && new_point.y > 0) PointState.UpperRightCorner else PointState.RightEdge
        PointWithState( new_point, new_state)

      case PointState.UpperRightCorner =>
        PointWithState( move_left(ps.point), PointState.UpperEdge)

      case PointState.UpperEdge =>
        val new_point = move_left( ps.point )
        val new_state = if (-new_point.x == new_point.y && new_point.x < 0 && new_point.y > 0) PointState.UpperLeftCorner else PointState.UpperEdge
        PointWithState( new_point, new_state)

      case PointState.UpperLeftCorner =>
        PointWithState( move_down(ps.point), PointState.LeftEdge)

      case PointState.LeftEdge =>
        val new_point = move_down( ps.point )
        val new_state = if (new_point.x == new_point.y && new_point.x < 0 && new_point.y < 0) PointState.LowerLeftCorner else PointState.LeftEdge
        PointWithState( new_point, new_state)

      case PointState.LowerLeftCorner =>
        PointWithState( move_right(ps.point), PointState.LowerEdge)

      case PointState.LowerEdge =>
        val new_point = move_right( ps.point )
        val new_state = if (new_point.x == -new_point.y && new_point.x > 0 && new_point.y < 0) PointState.LowerRightCorner else PointState.LowerEdge
        PointWithState( new_point, new_state)
    }
  }

}