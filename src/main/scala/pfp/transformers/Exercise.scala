package pfp.transformers

import scalaz._, Scalaz._, concurrent._

object Exercise {

  // 1. return a user using userErr, given the argument from fetchId

  case class User(id: Int)

  def userErr(id: Int): String \/ User = new User(id).right

  val fetchId: Task[Int] = Task.delay {
    10
  }

  



}
