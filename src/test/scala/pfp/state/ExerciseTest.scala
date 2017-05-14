package pfp.state

import org.scalatest.{FunSuite, Matchers}

import pfp.ski.κ
import scalaz._, Scalaz._

class ExerciseTest extends FunSuite with Matchers  {

  import Exercise._

  val emptyS = Map[Int, User]()

  test("inserted user is in store with generated key") {
    pending // remove when implemented
    // given
    val user = User("John", "Smith")
    // when
    val recipe = insert(user)
    // then
    val (k, store) = recipe.run(emptyS)
    store should be(Map(k -> user))
  }

  test("adding two users and removing them should return initial state") {
    pending // remove when implemented
    // given
    val user1 = User("John", "Smith")
    val user2 = User("Jane", "Smith")
    // when
    val recipe = for {
      k1 <- insert(user1)
      k2 <- insert(user2)
      _ <- delete(k1)
      _ <- delete(k2)
    } yield ()
    // then
    recipe.exec(emptyS) should be(emptyS)
  }

}
