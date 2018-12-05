package io.github.isuzuki.sample

import java.io.File

import kantan.csv._
import kantan.csv.generic._
import kantan.csv.ops._

case class Item (
  id: Int,
  name: String,
  spec1: Item.Spec,
)

object Item {
  implicit lazy val headerDecoder: HeaderDecoder[Item] = HeaderDecoder.defaultHeaderDecoder[Item]
  implicit lazy val rowDecoder: RowDecoder[Spec] = RowDecoder.decoder(0, 1)(Spec.apply)

  case class Spec (
    name: String,
    value: Int
  )
}

object NestedRead extends App {
  val file = new File(getClass.getResource("/nested_items.csv").getPath)
  file.asCsvReader[Item](rfc.withHeader).foreach(println)
}
