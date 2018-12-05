package io.github.isuzuki.sample

import java.io.File

import kantan.csv._
import kantan.csv.generic._
import kantan.csv.ops._

case class Item (
  id: Int,
  name: String,
  specs: Item.Specs
)

object Item {
  implicit lazy val headerDecoder: HeaderDecoder[Item] = HeaderDecoder.defaultHeaderDecoder[Item]
  implicit lazy val rowDecoder: RowDecoder[Specs] = RowDecoder.ordered { (n1: String, v1: Int, n2: String, v2: Int) =>
    Specs(Specs.Spec(n1, v1), Specs.Spec(n2, v2))
  }

  case class Specs (
    spec1: Specs.Spec,
    spec2: Specs.Spec
  )

  object Specs {
    case class Spec(
      name: String,
      value: Int
    )
  }
}

object NestedRead extends App {
  val file = new File(getClass.getResource("/nested_items.csv").getPath)
  file.asCsvReader[Item](rfc.withHeader).foreach(println)
}
