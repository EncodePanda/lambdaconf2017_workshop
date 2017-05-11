package pfp

package object ski extends Serializable {
  // NB: Unicode has double-struck and bold versions of the letters, which might
  //     be more appropriate, but the code points are larger than 2 bytes, so
  //     Scala doesn't handle them.


  /** Probably not useful; implemented here mostly because it's amusing. */
  def σ[A, B, C](x: A => B => C, y: A => B, z: A): C = x(z)(y(z))

  /** A shorter name for the constant function of 1, 2, 3, or 6 args.
    * NB: the argument is eager here, so use `_ => ...` instead if you need it
    *     to be thunked.
    */
  def κ[A, B](x: B): A => B                                  = _ => x
  def κ2[A, B, C](x: C): (A, B) => C                         = (_, _) => x
  def κ3[A, B, C, D](x: D): (A, B, C) => D                   = (_, _, _) => x
  def κ6[A, B, C, D, E, F, G](x: G): (A, B, C, D, E, F) => G = (_, _, _, _, _, _) => x

  /** A shorter name for the identity function. */
  def ι[A]: A => A = x => x
}
