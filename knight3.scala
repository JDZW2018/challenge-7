// Part 3 about finding a single tour using the Warnsdorf Rule
//=============================================================

// copy any function you need from files knight1.scala and
// knight2.scala

type Pos = (Int, Int)    // a position on a chessboard 
type Path = List[Pos]    // a path...a list of positions

//(3a) Complete the function that calculates a list of onward
// moves like in (1b) but orders them according to the Warnsdorf’s 
// rule. That means moves with the fewest legal onward moves 
// should come first.

def is_legal(dim: Int, path: Path)(x: Pos): Boolean = {
  val isInside = (x._1 >= 0) && (x._1 < dim) && (x._2 >= 0) && (x._2 < dim)
  val isInPath = path.find(y => y == x).isDefined
  isInside && !isInPath
}

def smaller_than(x1: Pos, x2: Pos, dim: Int, path: Path): Boolean =
  legal_moves(dim, path, x1).size < legal_moves(dim, path, x2).size


def legal_moves(dim: Int, path: Path, x: Pos): List[Pos] = {
  val one = (x._1 + 1, x._2 + 2)
  val two = (x._1 + 2, x._2 + 1)
  val three = (x._1 + 2, x._2 - 1)
  val four = (x._1 + 1, x._2 - 2)
  val five = (x._1 - 1, x._2 - 2)
  val six = (x._1 - 2, x._2 - 1)
  val seven = (x._1 - 2, x._2 + 1)
  val eight = (x._1 - 1, x._2 + 2)

  List(one, two, three, four, five, six, seven, eight)
    .filter(move => is_legal(dim, path)(move))
}

def ordered_moves(dim: Int, path: Path, x: Pos): List[Pos] = {
  val legalMoves = legal_moves(dim, path, x)
  legalMoves.sortWith(smaller_than(_, _, dim, path))
}

ordered_moves(6, List((0,0)), (2, 3))

//(3b) Complete the function that searches for a single *closed* 
// tour using the ordered moves function.

def first_closed_tour_heuristic(dim: Int, path: Path): Option[Path] = ...

//(3c) Same as (3b) but searches for *open* tours.

def first_tour_heuristic(dim: Int, path: Path): Option[Path] = ...
