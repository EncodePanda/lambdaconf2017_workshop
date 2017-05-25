package pfp.state

import scalaz._, Scalaz._, concurrent.Task

object InMemory {

  import Exercise.{User, S}

  var internal: S = Map[Int, User]()

  def fetchStore: Task[S] = Task.delay {
    InMemory.internal
  }

  def saveStore: S => Task[Unit] = (s: S) => Task.delay {
    InMemory.internal = s
  }
}

object Exercise {

  type S = Map[Int, User]

  case class User(firstName: String, lastName: String)

  // inserts users, returns key associated with the user
  def insert(user: User): State[S, Int] = ???

  // returns user from the store for given id
  def findById(id: Int): State[S, Option[User]] = ???

  // deletes user frrom the store
  def delete(id: Int): State[S, Unit] = ???
  
}
