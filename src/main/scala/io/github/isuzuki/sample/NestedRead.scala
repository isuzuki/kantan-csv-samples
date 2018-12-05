package io.github.isuzuki.sample

import java.io.File

import kantan.csv._
import kantan.csv.generic._
import kantan.csv.ops._

case class Item (
  id: Int,
  name: String,
  spec1: Spec,
)

case class Spec (
  name: String,
  value: Int
)

object Item {
  implicit lazy val headerDecoder: HeaderDecoder[Item] = HeaderDecoder.defaultHeaderDecoder[Item]
}

object NestedRead extends App {
  val file = new File(getClass.getResource("/nested_items.csv").getPath)
  file.asCsvReader[Item](rfc.withHeader).foreach(println)
}
