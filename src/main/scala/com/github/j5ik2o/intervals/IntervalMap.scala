package com.github.j5ik2o.intervals

import scala.collection.Iterator
import scala.collection.immutable.Map

/** 区間に対して値をマッピングする抽象クラス。
  *
  * @tparam A
  *   キーとなる区間の型
  * @tparam B
  *   値の型
  */
class IntervalMap[A, B](val values: Map[Interval[A], B]) {

  /** 要素が空のインスタンスを生成する。
    */
  def this() = this(Map.empty[Interval[A], B])

  override def toString: String = values.toString

  private def directPut(
      source: Map[Interval[A], B],
      intervalSequence: Seq[Interval[A]],
      value: B
  ): Map[Interval[A], B] = {
    val keyValues = collection.mutable.Map.empty[Interval[A], B]
    keyValues ++= source
    intervalSequence.foreach { e =>
      keyValues += (e -> value)
    }
    keyValues.toMap
  }

  def contains(key: LimitValue[A]): Boolean =
    findKeyIntervalContaining(key).isDefined

  def containsIntersectingKey(otherInterval: Interval[A]): Boolean =
    intersectingKeys(otherInterval).nonEmpty

  private def findKeyIntervalContaining(key: LimitValue[A]): Option[Interval[A]] =
    values.keys.find(_.includes(key))

  /** この写像が保持するキーとしての区間のうち、指定した区間 `otherInterval`と共通部分を持つ 区間の列を取得する。
    *
    * 戻り値の列は、区間の自然順にソートされている。
    *
    * @param otherInterval
    *   対象区間
    * @return
    *   指定した区間と共通部分を持つ区間の列
    */
  private def intersectingKeys(otherInterval: Interval[A]): Seq[Interval[A]] =
    values.keys.flatMap {
      case e if e.intersects(otherInterval) => Some(e)
      case _                                => None
    }.toSeq

  def put[B1 >: B](kv: (Interval[A], B1)): IntervalMap[A, B1] = {
    val removed = remove(kv._1)
    val result  = removed.values.+(kv)
    new IntervalMap(result)
  }

  def +[B1 >: B](kv: (Interval[A], B1)): IntervalMap[A, B1] = put(kv)

  def get(key: Interval[A]): Option[B] = values.get(key)

  def get(key: LimitValue[A]): Option[B] =
    findKeyIntervalContaining(key) match {
      case Some(key) => values.get(key)
      case None      => None
    }

  def remove(key: Interval[A]): IntervalMap[A, B] = {
    val intervalSeq = intersectingKeys(key)
    var currentMap  = values
    intervalSeq.foreach { oldInterval =>
      val oldValue = currentMap(oldInterval)
      currentMap -= oldInterval
      val complementIntervalSeq = key.complementRelativeTo(oldInterval)
      currentMap = directPut(currentMap, complementIntervalSeq, oldValue)
    }
    new IntervalMap(currentMap)
  }

  def -(key: Interval[A]): IntervalMap[A, B] = remove(key)

  def iterator: Iterator[(Interval[A], B)] = values.iterator

  def toMap: Map[Interval[A], B] = values

}

/** `IntervalMap`コンパニオンオブジェクト。
  */
object IntervalMap {

  /** インスタンスを生成する。
    *
    * @tparam A
    *   キーの型
    * @tparam B
    *   値の型
    * @return
    *   [[IntervalMap]]
    */
  def apply[A, B]: IntervalMap[A, B] =
    new IntervalMap()

  /** ファクトリメソッド。
    *
    * @tparam A
    *   キーの型
    * @tparam B
    *   値の型
    * @return
    *   [[IntervalMap]]
    */
  def apply[A, B](values: Map[Interval[A], B]): IntervalMap[A, B] =
    new IntervalMap(values)

  /** 抽出子メソッド。
    *
    * @tparam A
    *   キーの型
    * @tparam B
    *   値の型
    * @return
    *   [[LinearIntervalMap]]
    */
  def unapply[A, B](self: IntervalMap[A, B]): Option[Map[Interval[A], B]] =
    Some(self.values)

}
