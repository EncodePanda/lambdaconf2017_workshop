package pfp.intro

import scalaz._, Scalaz._, concurrent.Task

object Misc {

  /** algebraic data types [ADT] */

  sealed trait Figure
  case class Rectangle(width: Int, height: Int) extends Figure
  case class Cicrle(radius: Int) extends Figure

  sealed trait Expression
  case class Sum(exp1: Expression, exp2: Expression) extends Expression
  case class Multiply(exp1: Expression, exp2: Expression) extends Expression
  case class IntValue(v: Int) extends Expression

  /** Option */
  def calculateRisk(input: String): Option[Int] = input match {
    case "blah" => None
    case a => Some(a.length)
  }

  /** Either (Disjunction) */
  def calculateRisk2(input: String): String \/ Int = input match {
    case "blah" => "Wel... blah happend...".left
    case a => a.length.right
  }

  /** Task */
  case class User(id: Int, login: String)

  def fetchUser(id: Int): Task[User] = Task.delay {
    // real jdbc calls here
    User(id, s"login$id")
  }

}
