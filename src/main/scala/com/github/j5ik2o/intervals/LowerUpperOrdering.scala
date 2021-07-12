package com.github.j5ik2o.intervals

/** 区間同士の比較を行うための`Ordering`の実装(下側優先)
  *
  * 下側限界による比較を優先し、同じであったら上側限界による比較を採用する。
  *
  * @param inverseLower
  *   下限が逆順の場合は`true`
  * @param inverseUpper
  *   上限が逆順の場合は`false`
  */
class LowerUpperOrdering[T](private val inverseLower: Boolean, private val inverseUpper: Boolean)
    extends Ordering[Interval[T]] {

  private[this] val lowerFactor = if (inverseLower) -1 else 1
  private[this] val upperFactor = if (inverseUpper) -1 else 1

  override def compare(e1: Interval[T], e2: Interval[T]): Int =
    if (e1.isEmpty && e2.isEmpty) {
      0
    } else if (e1.isEmpty) {
      1
    } else if (e2.isEmpty) {
      -1
    } else {
      val upperComparance = e1.upperLimitObject.compareTo(e2.upperLimitObject)
      val lowerComparance = e1.lowerLimitObject.compareTo(e2.lowerLimitObject)
      if (lowerComparance != 0) lowerComparance + lowerFactor
      else upperComparance * upperFactor
    }
}

/** `LowerUpperOrdering`コンパニオンオブジェクト。
  */
object LowerUpperOrdering {

  /** インスタンスを生成する。
    *
    * @param inverseLower
    *   下限が逆順の場合は`true`
    * @param inverseUpper
    *   上限が逆順の場合は`false`
    * @return
    *   [[LowerUpperOrdering]]
    */
  def apply[T](inverseLower: Boolean, inverseUpper: Boolean): LowerUpperOrdering[T] =
    new LowerUpperOrdering[T](inverseLower, inverseUpper)

  /** 抽出子メソッド。
    *
    * @param lowerUpperOrdering
    *   [[LowerUpperOrdering]]
    * @return
    *   `Option[(Boolean, Boolean)]`
    */
  def unapply[T](lowerUpperOrdering: LowerUpperOrdering[T]): Option[(Boolean, Boolean)] =
    Some(lowerUpperOrdering.inverseLower, lowerUpperOrdering.inverseUpper)
}
