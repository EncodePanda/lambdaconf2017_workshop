package pfp.tools

object Exercise {
  // 1. Generalize intersect method to work with any Map[A, B]
  // def intersect[A, B](m1: Map[A, B], m2: Map[A, B]): Map[A, B] = {

  def intersect(m1: Map[String, Int], m2: Map[String, Int]): Map[String, Int] = {
    val keys = m1.keys ++ m2.keys
    val kvs = keys.map {
      k => (k -> (m1.get(k).getOrElse(0) + m2.get(k).getOrElse(0)))
    }
    Map[String, Int](kvs.toSeq: _*)
  }

  
  
}
