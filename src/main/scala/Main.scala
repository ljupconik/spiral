import CalcUtils.manhattan_distance

object Main {

  val usage =
    """
    Usage: sbt "run <NUMBER>"

        where <NUMBER> must be an Integer Argument greater than 0
      """

  def main(args: Array[String]): Unit = {

    if (args.length == 0) {
      println(usage)
      throw new IllegalArgumentException("You have to specify one Integer Argument")
    }

    if (args.length > 1) {
      println(usage)
      throw new IllegalArgumentException("You can not specify more than one Integer Argument")
    }

    val arglist = args.toList

    try {
      arglist.head.toInt
    }
    catch {
      case _: java.lang.NumberFormatException => { println(usage)
                              throw new java.lang.NumberFormatException("The specified Argument '" + arglist.head + "' must be an Integer")
                                                  }
    }

    val number = arglist.head.toInt

    val result:Int = manhattan_distance(number)

    println(s"The Manhattan distance between $number and 1 = $result")
  }
}
