package pfp.intro

object Exercise {

  /** Functions */
  // 1. write a function weirdo that takes Int and String as paramteres and adds Int to length of the String
  // val werido: ??? = ???

  // 2. implement above but as a method
  // def weirdo(???): ??? = ???

  // 3. bar = all elements in foo whose length > 4
  val foo = List("hello", "world", "!")
  val bar = ???

  // 4. write a method tupled that
  // - takes a function f as arguemnt
  // - function f takes two parameters (a: A, b: B) and returns type C
  // - method tupled should return a new function that takes tuple t: (A, B) as paramter and returns  value of type C
  // def tupled[A, B, C](???): ??? = ???

  // 5. implement andThen method
  // usage example
  // val len: String => Int = _.length
  // val plus10: Int => Double = _.toDouble + 10
  // val result: String => Double = andThen(len, plus10)

  /** Pattern matching & Scalaz types */
  // 1. write method extract that returns third element of the list or error if does not exist
  // def extract(list: List[Int]): ??? = ???


}
