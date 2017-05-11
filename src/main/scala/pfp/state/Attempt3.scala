package pfp.state

import scalaz._, Scalaz._

object Attempt3 {

  case class User(id: Int, firstName: String, lastName: String)

  case class Cache(map: Map[Int, User] = Map(), hits: Int = 0, misses: Int = 0)

  object Cache {

    def insert(user: User): State[Cache, Unit] = 
      State[Cache, Unit]((c: Cache) => (c.copy(map = c.map + (user.id -> user)), ()))

    def get(id: Int): State[Cache, Option[User]] = State[Cache, Option[User]] {
      (c: Cache) => {
        val m = c.map
        val nc = if(m.contains(id)) c.copy(hits = c.hits + 1) else c.copy(misses = c.misses + 1)
        (nc, m.get(id))
      }
    }
  }

  object Repository {
    def find(id: Int): State[Cache, String \/ User] = State[Cache, String \/ User] {
      (c: Cache) => {
        val u = User(id, s"John$id", "Smith")
        val (nc, _) = Cache.insert(u)(c)
        (nc, u.right)
      }
    }
  }

  object UserService {
    def findById(id: Int): State[Cache, String \/ User] = for {
      mu <- Cache.get(id)
      uerr <- mu.cata(u => u.right.point[State[Cache, ?]], Repository.find(id))
    } yield uerr
  }

  class UserService_2(fetch: Int => State[Cache, String \/ User]) {
    def findById(id: Int): State[Cache, String \/ User] = for {
      mu <- Cache.get(id)
      uerr <- mu.cata(u => u.right.point[State[Cache, ?]], fetch(id))
    } yield uerr
  }

  // on production
  val us = new UserService_2(Repository.find(_))
}
