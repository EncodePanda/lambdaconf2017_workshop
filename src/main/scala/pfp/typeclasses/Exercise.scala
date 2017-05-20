package pfp.typeclasses

object Exercise {
 
  /** Typeclasses */
  // 1. Implement Equalz[A] typeclass that has method def eq(a1: A, a2: A): Boolean

  // 2. Write EqualzOps & Equalz companion object with apply method

  // 3. Write instance for String and User
  case class User(login: String)

  // 4. if A & B have instance for Equalz, can u write generic instance for Tuple (A, B)
}
