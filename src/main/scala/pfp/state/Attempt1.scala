package pfp.state

import scalaz._, Scalaz._

/** I've seen this far too many times in the past... */
object Attempt1 {

  case class User(id: Int, firstName: String, lastName: String)

  trait Cache {

    def hits: Int
    def misses: Int

    def insert(user: User): Unit
    def get(id: Int): Option[User]
  }

  class CacheService(var hits: Int, var misses: Int) extends Cache {
    var map = Map[Int, User]()

    def insert(user: User): Unit = {
      map = map + (user.id -> user)
    }

    def get(id: Int): Option[User] = {
      if(map.contains(id)) hits = hits + 1 else misses = misses + 1
      map.get(id)
    }
  }

  trait Repository {
    def find(id: Int): String \/ User
  }

  class RepositorySerivce extends Repository {

    def find(id: Int): String \/ User =
      // some real calls to storage, like JDBC etc.
      User(id, s"John$id", "Smith").right

  }

  class UserService(cache: Cache, repository: Repository) {

    def findById(id: Int): String \/ User = {
      val mu: Option[User] = cache.get(id)
      mu.cata(_.right, {
        val u = repository.find(id)
        u.map(cache.insert(_))
        u
      })
    }
  }

/**
class UserServiceTest {
  test("that can find user if in cache") {
     // given
     val id: Int = 10
     val user = User(id, "first", "last")
     val mockCache = mock[Cache]
     given(mockCache.get(id)).willReturn(user.some)
     // when
     val result = new UserService(mockCache, mock[Repository]).findById(id)
     // then
     result should be(user)
     // ... hits =?= hits + 1
  }

test("that can find user if in cache") {
     // given
     val id: Int = 10
     val user = User(id. "first", "last")
     val mockCache = mock[Cache]
     given(mockCache.get(id)).willReturn(None)
     val mockRepository = mock[Repository]
     given(mockRepository.findById(id)).willReturn(user.right)
     // when
     val result = new UserService(mockCache, mock[Repository]).findById(id)
     // then
     result should be(user)
     
     // ... hits =?= hits + 1
}

}


  */
}
