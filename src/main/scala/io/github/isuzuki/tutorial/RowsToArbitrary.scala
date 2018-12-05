package io.github.isuzuki.tutorial

import java.io.File

import kantan.csv._
import kantan.csv.ops._

object RowsToArbitrary extends App {
  val file = new File(getClass.getResource("/items.csv").getPath)
  file.asCsvReader[Item](rfc.withHeader).foreach(println)
  file.readCsv[List, Item](rfc.withHeader).foreach(println)

  case class Item(
    id: Int,
    name: String,
    category: String,
    subCategory: Option[String],
    price: Int
  )

  object Item {
    implicit val itemDecoder: RowDecoder[Item] = RowDecoder.ordered { (i: Int, n: String, c: String, sc: Option[String], p: Int) => Item(i, n, c, sc, p) }
  }
}
