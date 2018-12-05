package io.github.isuzuki.tutorial

import java.io.File

import kantan.csv._
import kantan.csv.generic._
import kantan.csv.ops._

object ToCaseClass extends App {
  val file = new File(getClass.getResource("/items.csv").getPath)
  file.asCsvReader[Item](rfc.withHeader).foreach(println)

  case class Item(
    id: Int,
    name: String,
    category: String,
    subCategory: Option[String],
    price: Int
  )
}
