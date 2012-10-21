import scalaz._
import scalaz.Scalaz._

object Scalaz {
  def main(args: Array[String]) {
    println("### Equals")
    assert(1 === 1, true)
    //1 === "1" ;compilation error
    assert(1.some =/= 2.some, true)

    println("### Ordering")
    assert(1 > 2.0, false)
    //1 gt 2.0 ;compilation error
    assert((1.0 ?|? 2.0) === LT, true)
    assert((1.0 max 2.0) === 2.0, true)
  }
}
