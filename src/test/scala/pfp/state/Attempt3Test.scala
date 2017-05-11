package pfp.state

import org.scalatest.{FunSuite, Matchers}

import pfp.ski.κ
import scalaz._, Scalaz._

class Attempt3Test extends FunSuite with Matchers  {

  import Attempt3._

  test("user not in cache but in repo") {
    // given
    val user  = User(1, "first", "last")
    val fetch = user.right[String].point[State[Cache, ?]]
    val us = new UserService_2(κ(fetch))
    val init = Cache()
    // when
    val (cache, userErr) = us.findById(1).run(init)
    // then
    cache should be(Cache(map = Map(), hits = 0, misses = 1))
    userErr should be(user.right[String])
  }

  test("user in cache and in repo") {
    // given
    val user  = User(1, "first", "last")
    val fetch = user.right[String].point[State[Cache, ?]]
    val us = new UserService_2(κ(fetch))
    val init = Cache(map = Map(1 -> user), hits = 0, misses = 0)
    // when
    val (cache, userErr) = us.findById(1).run(init)
    // then
    cache should be(Cache(map = Map(1 -> user), hits = 1, misses = 0))
    userErr should be(user.right[String])
  }

  test("user neihter in cache nor in repository") {
    // given
    val msg = "User not found"
    val fetch = msg.left[User].point[State[Cache, ?]]
    val us = new UserService_2(κ(fetch))
    val init = Cache()
    // when
    val (cache, userErr) = us.findById(1).run(init)
    // then
    cache should be(Cache(map = Map(), hits = 0, misses = 1))
    userErr should be(msg.left[User])
  }

}
