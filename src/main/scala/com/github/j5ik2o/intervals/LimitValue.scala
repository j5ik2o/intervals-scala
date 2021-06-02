package com.github.j5ik2o.intervals

/** 限界値を表すトレイト。
  *
  * @tparam T 限界値の型
  */
trait LimitValue[T] extends Ordered[LimitValue[T]] {

  /** 限界値を返す。
    *
    * @return 限界値
    * @throws NoSuchElementException 無限の場合
    */
  def toValue: T = toValueOrElse(throw new NoSuchElementException)

  /** 限界値を返す。
    *
    * @param default 無限の場合の式
    * @return 限界値。無限の場合は`default`を返す。
    */
  def toValueOrElse(default: => T): T = this match {
    case Limit(value)    => value
    case _: Limitless[T] => default
  }

  override def equals(obj: Any): Boolean = obj match {
    case that: LimitValue[T] => (this compare that) == 0
    case that =>
      this match {
        case Limit(value)    => value == that
        case _: Limitless[T] => false
      }
  }

}

/** `LimitValue`コンパニオンオブジェクト。
  */
object LimitValue {

  /** `LimitValue` を [Limit]]の限界値に変換する。
    *
    * @param limitValue [[LimitValue]]
    * @return [[Limit]]
    * @throws IllegalArgumentException limitValueがLimitless[T]の場合
    */
  implicit def toValue[T](
      limitValue: LimitValue[T]
  ): T =
    limitValue match {
      case Limit(value) => value
      case _: Limitless[T] =>
        throw new IllegalArgumentException(
          "implicit conversion from Limitless[T] can't do."
        )
    }

  /** 値を[[LimitValue]]に変換する。
    *
    * @param value 値
    * @return [[Limit]]
    */
  def toLimitValue[T](
      value: Option[T]
  )(implicit ev: T => Ordered[T]): LimitValue[T] =
    value match {
      case None    => Limitless[T]()
      case Some(v) => Limit(v)
    }

  implicit def toLimit[T](value: T)(implicit ev: T => Ordered[T]): Limit[T] =
    Limit(value)

}

/** 有限の限界値を表すクラス。
  *
  * @tparam T 限界値の型
  * @param value 限界値
  */
case class Limit[T](value: T)(implicit ev: T => Ordered[T]) extends LimitValue[T] {

  def compare(that: LimitValue[T]): Int = that match {
    case that: Limit[T] => value.compare(that.value)
    case _              => 1
  }

}

/** 無限の限界値を表すクラス。
  *
  * @tparam T 限界値の型
  */
case class Limitless[T]() extends LimitValue[T] {

  def compare(that: LimitValue[T]): Int = that match {
    case _: Limitless[T] => 0
    case _               => -1
  }

}
