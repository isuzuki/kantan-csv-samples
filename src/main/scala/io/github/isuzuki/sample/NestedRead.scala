package io.github.isuzuki.sample

import java.io.File

import kantan.csv._
import kantan.csv.generic._
import kantan.csv.ops._

case class CsvRecord (
  item: Item,
)

object CsvRecord {
  implicit lazy val headerDecoder: HeaderDecoder[CsvRecord] = HeaderDecoder.defaultHeaderDecoder[CsvRecord]
}

case class Item (
  id: Int,
  name: String,
  specs: Item.Specs
)

object Item {
  implicit lazy val rowDecoder: RowDecoder[Specs] = RowDecoder.from { row =>
    val (row1, row2) = row.splitAt(2)
    for {
      spec1 <- Specs.rowDecoder.decode(row1)
      spec2 <- Specs.rowDecoder.decode(row2)
    } yield Specs(spec1, spec2)
  }

  case class Specs (
    spec1: Specs.Spec,
    spec2: Specs.Spec
  )

  object Specs {
    implicit lazy val rowDecoder: RowDecoder[Spec] = RowDecoder.decoder(0, 1)(Spec.apply)

    case class Spec(
      name: String,
      value: Int
    )
  }
}

object NestedRead extends App {
  val file = new File(getClass.getResource("/nested_items.csv").getPath)
  file.asCsvReader[CsvRecord](rfc.withHeader).foreach(println)
}
