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
      System.exit(-1)
    }

    if (args.length > 1) {
      println("You must specify only one Integer Argument greater than 0, to calculate the MANHATTAN_DISTANCE between it and the starting central number 1 of the spiral")
      println(usage)
      System.exit(-1)
    }

    val arglist = args.toList

    try {
      arglist.head.toInt
    }
    catch {
      case e: java.lang.NumberFormatException => { println("The specified Argument '" + arglist.head + "' must be an Integer > 0 .")
                                                    println(usage)
                                                    System.exit(-1)
                                                  }
    }

    val number = arglist.head.toInt

    val result:Int = manhattan_distance(number)

    println(s"The Manhattan distance between $number and 1 = $result")
  }
}
