package pfp.scala

import scalaz._, Scalaz._

object Attempt2 {

  case class User(id: Int, firstName: String, lastName: String)

  case class Cache(map: Map[Int, User] = Map(), hits: Int = 0, misses: Int = 0)

  object Cache {

    def insert(user: User): Cache => (Cache, Unit) =
      (c: Cache) => (c.copy(map = c.map + (user.id -> user)), ())

    def get(id: Int): Cache => (Cache, Option[User]) = (c: Cache) => {
        val m = c.map
        val nc = if(m.contains(id)) c.copy(hits = c.hits + 1) else c.copy(misses = c.misses + 1)
        (nc, m.get(id))
    }
  }

  object Repository {
    def find(id: Int): Cache => (Cache, String \/ User) = (c: Cache) => {
      val u = User(id, s"John$id", "Smith")
      val (nc, _) = Cache.insert(u)(c)
      (nc, u.right)
    }
  }

  object UserService {
    def findById(id: Int): Cache => (Cache, String \/ User) = (c: Cache) => {
      val (nc1, mu) = Cache.get(id)(c)
      mu.cata(u => (nc1, u.right), Repository.find(id)(nc1))
    }
  }
}
