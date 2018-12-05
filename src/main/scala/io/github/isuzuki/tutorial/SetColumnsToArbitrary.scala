package io.github.isuzuki.tutorial

import java.io.File

import kantan.csv._
import kantan.csv.generic._
import kantan.csv.ops._

object SetColumnsToArbitrary extends App {
  val file = new File(getClass.getResource("/set_items.csv").getPath)
  file.asCsvReader[SpecItem](rfc.withHeader).foreach(println)

  case class SpecItem(
    id: Int,
    name: String,
    spec: List[Spec],
    // 有効にするとエラーになる
    // option: String
  )

  case class Spec(
    name: Option[String],
    value: Option[Int]
  )

  object Spec {
    implicit val listSpecDecoder: RowDecoder[List[Spec]] = RowDecoder.ordered { (n: Option[String], v: Option[Int], n2: Option[String], v2: Option[Int]) => {
      List(Spec(n, v), Spec(n2, v2))
    }}
  }
}
