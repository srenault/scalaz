import scalaz._
import scalaz.Scalaz._
import scala.collection.immutable.NumericRange

object Scalaz {
  def main(args: Array[String]) {
    println("### Equals")
    1 === 1
    //1 === "1" ;compilation error
    1.some =/= 2.some

    println("### Ordering")
    (1 > 2.0) === false
    //1 gt 2.0 ;compilation error
    (1.0 ?|? 2.0) === LT
    (1.0 max 2.0) == 2.0

    println("### Show")
    3.shows == "3"

    println("### Enum")
    ('a' to 'e').toList == ('a' |-> 'e')
    ('b'.succ == 'c') == true

    println("### Typeclass")
  }
}

/**
 * Typed constructor : How I can convert a to boolean
 */
trait CanTruthy[A] { self =>
  def truthys(a: A): Boolean
}

object CanTruthy {
  def apply[A](implicit ev: CanTruthy[A]): CanTruthy[A] = ev
  def truthys[A](f: A => Boolean): CanTruthy[A] = new CanTruthy[A] {
    def truthys(a: A) = f(a)
  }
}

trait CanTruthyOps[A] {
  def self: A
  implicit def F: CanTruthy[A]
  final def truthy: Boolean = F.truthys(self)
}

object ToCanIsTruthyOps {
  implicit def toCanIsTruthyOps[A](v: A)(implicit ev: CanTruthy[A]) = new CanTruthyOps[A] {
    def self = v
    implicit def F: CanTruthy[A] = ev
  }
}
