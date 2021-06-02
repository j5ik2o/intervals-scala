package com.github.j5ik2o.intervals

/** 区間同士の比較を行うための`Ordering`の実装(上側優先)
  *
  * 上側限界による比較を優先し、同じであったら下側限界による比較を採用する。
  *
  * @param inverseLower 下限が逆順の場合は`true`
  * @param inverseUpper 上限が逆順の場合は`false`
  */
class UpperLowerOrdering[T](private val inverseLower: Boolean, private val inverseUpper: Boolean)
    extends Ordering[Interval[T]] {

  private[this] val lowerFactor = if (inverseLower) -1 else 1
  private[this] val upperFactor = if (inverseUpper) -1 else 1

  override def compare(e1: Interval[T], e2: Interval[T]): Int =
    if (e1.isEmpty && e2.isEmpty) {
      0
    } else if (e1.isEmpty) {
      -1
    } else if (e2.isEmpty) {
      1
    } else {
      val upperComparance = e1.upperLimitObject.compareTo(e2.upperLimitObject)
      val lowerComparance = e1.lowerLimitObject.compareTo(e2.lowerLimitObject)
      if (upperComparance != 0) upperComparance * upperFactor
      else lowerComparance * lowerFactor
    }
}

/** `UpperLowerOrdering`コンパニオンオブジェクト。
  */
object UpperLowerOrdering {

  /** インスタンスを生成する。
    *
    * @param inverseLower 下限が逆順の場合は`true`
    * @param inverseUpper 上限が逆順の場合は`false`
    * @return [[UpperLowerOrdering]]
    */
  def apply[T](inverseLower: Boolean, inverseUpper: Boolean): UpperLowerOrdering[T] =
    new UpperLowerOrdering[T](inverseLower, inverseUpper)

  /** 抽出子メソッド。
    *
    * @param upperLowerOrdering [[UpperLowerOrdering]]
    * @return `Option[(Boolean, Boolean)]`
    */
  def unapply[T](upperLowerOrdering: UpperLowerOrdering[T]): Option[(Boolean, Boolean)] =
    Some(upperLowerOrdering.inverseLower, upperLowerOrdering.inverseUpper)

}
