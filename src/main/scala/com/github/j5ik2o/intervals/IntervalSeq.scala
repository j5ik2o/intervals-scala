package com.github.j5ik2o.intervals

import scala.collection._

/** 区間列（複数の [[Interval]] の列）を表すクラス。
  *
  * @tparam T
  *   [[Interval]] の型
  * @param values
  *   [[Interval]] の列
  * @param ordering
  *   [[Ordering]]
  */
class IntervalSeq[T](val values: Vector[Interval[T]], val ordering: Ordering[Interval[T]]) {

  def append(value: Interval[T]): IntervalSeq[T] = new IntervalSeq(values :+ value)

  def :+(value: Interval[T]): IntervalSeq[T] = append(value)

  def isEmpty: Boolean = values.isEmpty

  def nonEmpty: Boolean = !isEmpty

  /** インスタンスを生成する。
    *
    * `intervals`は空を利用し、`ordering`は`UpperLowerOrdering[T](true, false)`を利用する。
    */
  def this() =
    this(Vector.empty, UpperLowerOrdering[T](inverseLower = true, inverseUpper = false))

  /** インスタンスを生成する。
    *
    * `ordering`は`UpperLowerOrdering[T](true, false)`を利用する。
    *
    * @param values
    *   [[Interval]] の列
    */
  def this(values: Vector[Interval[T]]) =
    this(values, UpperLowerOrdering[T](inverseLower = true, inverseUpper = false))

  /** 全ての要素区間を内包する、最小の区間を返す。
    *
    * @return
    *   全ての要素区間を内包する、最小の区間
    * @throws
    *   IllegalStateException 要素が1つもない場合
    */
  lazy val extent: Option[Interval[T]] = {
    values.toList match {
      case Nil     => None
      case List(e) => Some(e)
      case firstInterval :: _ =>
        val lower = values.map(_.lowerLimitObject).min
        val upper = values.map(_.upperLimitObject).max
        Some(firstInterval.newOfSameType(lower.value, lower.closed, upper.value, upper.closed))
    }
  }

  /** ソート済みの区間で、隣り合った区間同士に挟まれる区間を区間列として返す。
    *
    * 結果の区間列の `java.util.Comparator` は、この区間列の `java.util.Comparator` を流用する。
    *
    * 区間数が2つ未満の場合は、空の区間列を返す。また、区間同士が重なっていたり接していた場合は、 その区間は結果の要素に含まない。全てが重なっている場合は、空の区間列を返す。
    *
    * @return
    *   ギャップ区間列
    */
  lazy val gaps: IntervalSeq[T] = {
    if (values.size < 2) {
      new IntervalSeq()
    } else {
      val values = (1 until this.values.size).flatMap { i =>
        val left  = this.values(i - 1)
        val right = this.values(i)
        val gap   = left.gap(right)
        if (gap.isEmpty) {
          None
        } else {
          Some(gap)
        }
      }
      IntervalSeq(values.toVector)
    }
  }

  /** ソート済みの区間で、隣り合った区間同士が重なっている区間を区間列として返す。
    *
    * 結果の区間列の [[java.util.Comparator]] は、この区間列の [[java.util.Comparator]] を流用する。
    *
    * 区間数が2つ未満の場合は、空の区間列を返す。また、区間同士が重ならなかったり接していた場合は、 その区間は結果の要素に含まない。全てが重ならない場合は、空の区間列を返す。
    *
    * @return
    *   共通区間列
    */
  lazy val intersections: IntervalSeq[T] = {
    if (values.size < 2) {
      new IntervalSeq()
    } else {
      val values = (1 until this.values.size).flatMap { i =>
        val left  = this.values(i - 1)
        val right = this.values(i)
        val gap   = left.intersect(right)
        if (gap.isEmpty) None
        else Some(gap)
      }
      IntervalSeq(values.toVector)
    }
  }

  def iterator: Iterator[Interval[T]] = values.sorted(ordering).iterator

  def length: Int = values.length

  def apply(idx: Int): Interval[T] = values(idx)

  def toSeq: Seq[Interval[T]] = values

}

/** `IntervalSeq`コンパニオンオブジェクト
  */
object IntervalSeq {

  /** インスタンスを生成する。
    *
    * @tparam T
    *   限界値の型
    * @param intervals
    *   [[Interval]] の列
    * @return
    *   [[IntervalSeq]]
    */
  def apply[T](intervals: Vector[Interval[T]]): IntervalSeq[T] =
    new IntervalSeq(intervals)

  /** インスタンスを生成する。
    *
    * @tparam T
    *   限界値の型
    * @return
    *   [[IntervalSeq]]
    */
  def apply[T](): IntervalSeq[T] = new IntervalSeq[T]()

  /** 抽出子メソッド。
    *
    * @tparam T
    *   限界値の型
    * @return
    *   構成要素
    */
  def unapply[T](intervalSeq: IntervalSeq[T]): Option[(Seq[Interval[T]], Ordering[Interval[T]])] =
    Some(intervalSeq.values, intervalSeq.ordering)

}
