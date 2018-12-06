package io.github.isuzuki.sample

import java.io.File

import kantan.csv._
import kantan.csv.generic._
import kantan.csv.ops._

object NestedRead {
  case class CsvRecord (
    item: Item,
    spec: Spec,
    specs: List[Spec]
  )

  case class Item (
    id: Int,
    name: String
  )

  object Item {
    implicit lazy val rowDecoder: RowDecoder[Item] = RowDecoder.decoder(0, 1)(Item.apply)
  }

  case class Spec (
    name: String,
    value: Int
  )

  object Spec {
    implicit lazy val rowDecoder: RowDecoder[Spec] = RowDecoder.decoder(0, 1)(Spec.apply)
  }

  def main(args: Array[String]): Unit = {
    implicit lazy val headerDecoder: HeaderDecoder[CsvRecord] = HeaderDecoder.defaultHeaderDecoder[CsvRecord]

    implicit lazy val lsRowDecoder: RowDecoder[List[Spec]] = RowDecoder.from { row =>
      for {
        spec1 <- Spec.rowDecoder.decode(row.slice(0, 2))
        spec2 <- Spec.rowDecoder.decode(row.slice(2, 4))
      } yield List(spec1, spec2)
    }

    implicit lazy val cRowDecoder: RowDecoder[CsvRecord] = RowDecoder.from { row =>
      for {
        item <- Item.rowDecoder.decode(row.slice(0, 2))
        spec <- Spec.rowDecoder.decode(row.slice(2, 4))
        specs <- lsRowDecoder.decode(row.slice(2, 6))
      } yield CsvRecord(item, spec, specs)
    }

    val file = new File(getClass.getResource("/nested_items.csv").getPath)
    file.asCsvReader[CsvRecord](rfc.withHeader).foreach(println)
  }
}
