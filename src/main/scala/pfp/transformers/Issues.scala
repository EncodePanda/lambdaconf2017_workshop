package pfp.transformers

import scalaz._, Scalaz._, concurrent.Task

object Issues1 {

  case class User(id: Int)

  val maybeId: Option[Int] = 1.some

  def fetch(id: Int): Task[User] = Task.delay {
    User(id)
  }

  // val user = for {
    // id <- maybeId
    // u <- fetch(id)
  // } yield u

}

object Issues2 {

  case class User(id: Int)

  val fetch: Task[Option[User]] = Task.delay {
    User(10).some
  }

  // val id: Task[Option[Int]] = for {
    // u <- fetch
  // } yield u.id

  val id: Task[Option[Int]] = for {
    mu <- fetch
  } yield for {
    u <- mu
  } yield u.id

  val id2: Task[Option[Int]] = (for {
    u <- OptionT[Task, User](fetch)
  } yield u.id).run

}

object Issue1Back {

  case class User(id: Int)

  val maybeId: Option[Int] = 1.some

  def fetch(id: Int): Task[User] = Task.delay {
    User(id)
  }

  val user: OptionT[Task, User] = for {
    id <- OptionT[Task, Int](maybeId.point[Task])
    u <- OptionT[Task, User](fetch(id).map(_.some))
  } yield u

}
