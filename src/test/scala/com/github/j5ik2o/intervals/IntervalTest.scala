package com.github.j5ik2o.intervals

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/** `Interval`のテストクラス。
  */
class IntervalTest extends AnyFunSuite {

  val c5_10c: Interval[BigDecimal] = Interval.closed(Limit(BigDecimal(5)), Limit(BigDecimal(10)))

  val c1_10c: Interval[BigDecimal] = Interval.closed(Limit(BigDecimal(1)), Limit(BigDecimal(10)))

  val c4_6c: Interval[BigDecimal] = Interval.closed(Limit(BigDecimal(4)), Limit(BigDecimal(6)))

  val c5_15c: Interval[BigDecimal] = Interval.closed(Limit(BigDecimal(5)), Limit(BigDecimal(15)))

  val c12_16c: Interval[BigDecimal] = Interval.closed(Limit(BigDecimal(12)), Limit(BigDecimal(16)))

  val o10_12c: Interval[BigDecimal] =
    Interval.over(Limit(BigDecimal(10)), lowerIncluded = false, Limit(BigDecimal(12)), upperIncluded = true)

  val o1_1c: Interval[BigDecimal] =
    Interval.over(Limit(BigDecimal(1)), lowerIncluded = false, Limit(BigDecimal(1)), upperIncluded = true)

  val c1_1o: Interval[BigDecimal] =
    Interval.over(Limit(BigDecimal(1)), lowerIncluded = true, Limit(BigDecimal(1)), upperIncluded = false)

  val c1_1c: Interval[BigDecimal] =
    Interval.over(Limit(BigDecimal(1)), lowerIncluded = true, Limit(BigDecimal(1)), upperIncluded = true)

  val o1_1o: Interval[BigDecimal] =
    Interval.over(Limit(BigDecimal(1)), lowerIncluded = false, Limit(BigDecimal(1)), upperIncluded = false)

  val _2o: Interval[BigDecimal] =
    Interval.over(Limitless[BigDecimal](), lowerIncluded = true, Limit(BigDecimal(2)), upperIncluded = false)

  val o9_ : Interval[BigDecimal] =
    Interval.over(Limit(BigDecimal(9)), lowerIncluded = false, Limitless[BigDecimal](), upperIncluded = true)

  val empty: Interval[BigDecimal] = Interval.open(Limit(BigDecimal(1)), Limit(BigDecimal(1)))

  val all: Interval[BigDecimal] = Interval.closed(Limitless[BigDecimal](), Limitless[BigDecimal]())

  def newIntegerIntervalList: ArrayBuffer[Interval[Int]] = {
    val list = ArrayBuffer.empty[Interval[Int]]

    // 開区間
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(25), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(50), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(75), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(100), upperIncluded = false)
    list += Interval.over(Limit(25), lowerIncluded = false, Limit(50), upperIncluded = false)
    list += Interval.over(Limit(25), lowerIncluded = false, Limit(75), upperIncluded = false)
    list += Interval.over(Limit(25), lowerIncluded = false, Limit(100), upperIncluded = false)
    list += Interval.over(Limit(50), lowerIncluded = false, Limit(75), upperIncluded = false)
    list += Interval.over(Limit(50), lowerIncluded = false, Limit(100), upperIncluded = false)
    list += Interval.over(Limit(75), lowerIncluded = false, Limit(100), upperIncluded = false)

    // 半開区間
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(25), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(50), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(75), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(100), upperIncluded = false)
    list += Interval.over(Limit(25), lowerIncluded = true, Limit(50), upperIncluded = false)
    list += Interval.over(Limit(25), lowerIncluded = true, Limit(75), upperIncluded = false)
    list += Interval.over(Limit(25), lowerIncluded = true, Limit(100), upperIncluded = false)
    list += Interval.over(Limit(50), lowerIncluded = true, Limit(75), upperIncluded = false)
    list += Interval.over(Limit(50), lowerIncluded = true, Limit(100), upperIncluded = false)
    list += Interval.over(Limit(75), lowerIncluded = true, Limit(100), upperIncluded = false)

    list += Interval.over(Limit(0), lowerIncluded = false, Limit(25), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(50), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(75), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(100), upperIncluded = true)
    list += Interval.over(Limit(25), lowerIncluded = false, Limit(50), upperIncluded = true)
    list += Interval.over(Limit(25), lowerIncluded = false, Limit(75), upperIncluded = true)
    list += Interval.over(Limit(25), lowerIncluded = false, Limit(100), upperIncluded = true)
    list += Interval.over(Limit(50), lowerIncluded = false, Limit(75), upperIncluded = true)
    list += Interval.over(Limit(50), lowerIncluded = false, Limit(100), upperIncluded = true)
    list += Interval.over(Limit(75), lowerIncluded = false, Limit(100), upperIncluded = true)

    // 閉区間
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(25), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(50), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(75), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(100), upperIncluded = true)
    list += Interval.over(Limit(25), lowerIncluded = true, Limit(50), upperIncluded = true)
    list += Interval.over(Limit(25), lowerIncluded = true, Limit(75), upperIncluded = true)
    list += Interval.over(Limit(25), lowerIncluded = true, Limit(100), upperIncluded = true)
    list += Interval.over(Limit(50), lowerIncluded = true, Limit(75), upperIncluded = true)
    list += Interval.over(Limit(50), lowerIncluded = true, Limit(100), upperIncluded = true)
    list += Interval.over(Limit(75), lowerIncluded = true, Limit(100), upperIncluded = true)

    // single point
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(0), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(0), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(0), upperIncluded = true)
    list += Interval.over(Limit(25), lowerIncluded = true, Limit(25), upperIncluded = false)
    list += Interval.over(Limit(25), lowerIncluded = false, Limit(25), upperIncluded = true)
    list += Interval.over(Limit(25), lowerIncluded = true, Limit(25), upperIncluded = true)
    list += Interval.over(Limit(50), lowerIncluded = true, Limit(50), upperIncluded = false)
    list += Interval.over(Limit(50), lowerIncluded = false, Limit(50), upperIncluded = true)
    list += Interval.over(Limit(50), lowerIncluded = true, Limit(50), upperIncluded = true)
    list += Interval.over(Limit(75), lowerIncluded = true, Limit(75), upperIncluded = false)
    list += Interval.over(Limit(75), lowerIncluded = false, Limit(75), upperIncluded = true)
    list += Interval.over(Limit(75), lowerIncluded = true, Limit(75), upperIncluded = true)
    list += Interval.over(Limit(100), lowerIncluded = true, Limit(100), upperIncluded = false)
    list += Interval.over(Limit(100), lowerIncluded = false, Limit(100), upperIncluded = true)
    list += Interval.over(Limit(100), lowerIncluded = true, Limit(100), upperIncluded = true)

    // isEmpty
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(0), upperIncluded = false)
    list += Interval.over(Limit(25), lowerIncluded = false, Limit(25), upperIncluded = false)
    list += Interval.over(Limit(50), lowerIncluded = false, Limit(50), upperIncluded = false)
    list += Interval.over(Limit(75), lowerIncluded = false, Limit(75), upperIncluded = false)
    list += Interval.over(Limit(100), lowerIncluded = false, Limit(100), upperIncluded = false)

    // 下側限界のみ区間
    list += Interval.over(Limit(0), lowerIncluded = false, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = true, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(25), lowerIncluded = false, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(25), lowerIncluded = true, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(50), lowerIncluded = false, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(50), lowerIncluded = true, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(75), lowerIncluded = false, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(75), lowerIncluded = true, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(100), lowerIncluded = false, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(100), lowerIncluded = true, Limitless[Int](), upperIncluded = false)

    // 上側限界のみ区間
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(0), upperIncluded = false)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(0), upperIncluded = true)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(25), upperIncluded = false)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(25), upperIncluded = true)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(50), upperIncluded = false)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(50), upperIncluded = true)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(75), upperIncluded = false)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(75), upperIncluded = true)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(100), upperIncluded = false)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(100), upperIncluded = true)

    // freedom
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limitless[Int](), upperIncluded = false)

    Random.shuffle(list)
  }

  def newIntegerIntervalList2: ArrayBuffer[Interval[Int]] = {
    val list = ArrayBuffer.empty[Interval[Int]]

    // 開区間
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(5), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(10), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(15), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(20), upperIncluded = false)
    list += Interval.over(Limit(5), lowerIncluded = false, Limit(10), upperIncluded = false)
    list += Interval.over(Limit(5), lowerIncluded = false, Limit(15), upperIncluded = false)
    list += Interval.over(Limit(5), lowerIncluded = false, Limit(20), upperIncluded = false)
    list += Interval.over(Limit(10), lowerIncluded = false, Limit(15), upperIncluded = false)
    list += Interval.over(Limit(10), lowerIncluded = false, Limit(20), upperIncluded = false)
    list += Interval.over(Limit(15), lowerIncluded = false, Limit(20), upperIncluded = false)

    // 半開区間
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(5), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(10), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(15), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(20), upperIncluded = false)
    list += Interval.over(Limit(5), lowerIncluded = true, Limit(10), upperIncluded = false)
    list += Interval.over(Limit(5), lowerIncluded = true, Limit(15), upperIncluded = false)
    list += Interval.over(Limit(5), lowerIncluded = true, Limit(20), upperIncluded = false)
    list += Interval.over(Limit(10), lowerIncluded = true, Limit(15), upperIncluded = false)
    list += Interval.over(Limit(10), lowerIncluded = true, Limit(20), upperIncluded = false)
    list += Interval.over(Limit(15), lowerIncluded = true, Limit(20), upperIncluded = false)

    list += Interval.over(Limit(0), lowerIncluded = false, Limit(5), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(10), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(15), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(20), upperIncluded = true)
    list += Interval.over(Limit(5), lowerIncluded = false, Limit(10), upperIncluded = true)
    list += Interval.over(Limit(5), lowerIncluded = false, Limit(15), upperIncluded = true)
    list += Interval.over(Limit(5), lowerIncluded = false, Limit(20), upperIncluded = true)
    list += Interval.over(Limit(10), lowerIncluded = false, Limit(15), upperIncluded = true)
    list += Interval.over(Limit(10), lowerIncluded = false, Limit(20), upperIncluded = true)
    list += Interval.over(Limit(15), lowerIncluded = false, Limit(20), upperIncluded = true)

    // 閉区間
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(5), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(10), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(15), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(20), upperIncluded = true)
    list += Interval.over(Limit(5), lowerIncluded = true, Limit(10), upperIncluded = true)
    list += Interval.over(Limit(5), lowerIncluded = true, Limit(15), upperIncluded = true)
    list += Interval.over(Limit(5), lowerIncluded = true, Limit(20), upperIncluded = true)
    list += Interval.over(Limit(10), lowerIncluded = true, Limit(15), upperIncluded = true)
    list += Interval.over(Limit(10), lowerIncluded = true, Limit(20), upperIncluded = true)
    list += Interval.over(Limit(15), lowerIncluded = true, Limit(20), upperIncluded = true)

    // single point
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(0), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(0), upperIncluded = true)
    list += Interval.over(Limit(0), lowerIncluded = true, Limit(0), upperIncluded = true)
    list += Interval.over(Limit(5), lowerIncluded = true, Limit(5), upperIncluded = false)
    list += Interval.over(Limit(5), lowerIncluded = false, Limit(5), upperIncluded = true)
    list += Interval.over(Limit(5), lowerIncluded = true, Limit(5), upperIncluded = true)
    list += Interval.over(Limit(10), lowerIncluded = true, Limit(10), upperIncluded = false)
    list += Interval.over(Limit(10), lowerIncluded = false, Limit(10), upperIncluded = true)
    list += Interval.over(Limit(10), lowerIncluded = true, Limit(10), upperIncluded = true)
    list += Interval.over(Limit(15), lowerIncluded = true, Limit(15), upperIncluded = false)
    list += Interval.over(Limit(15), lowerIncluded = false, Limit(15), upperIncluded = true)
    list += Interval.over(Limit(15), lowerIncluded = true, Limit(15), upperIncluded = true)
    list += Interval.over(Limit(20), lowerIncluded = true, Limit(20), upperIncluded = false)
    list += Interval.over(Limit(20), lowerIncluded = false, Limit(20), upperIncluded = true)
    list += Interval.over(Limit(20), lowerIncluded = true, Limit(20), upperIncluded = true)

    // isEmpty
    list += Interval.over(Limit(0), lowerIncluded = false, Limit(0), upperIncluded = false)
    list += Interval.over(Limit(5), lowerIncluded = false, Limit(5), upperIncluded = false)
    list += Interval.over(Limit(10), lowerIncluded = false, Limit(10), upperIncluded = false)
    list += Interval.over(Limit(15), lowerIncluded = false, Limit(15), upperIncluded = false)
    list += Interval.over(Limit(20), lowerIncluded = false, Limit(20), upperIncluded = false)

    // 下側限界のみ区間
    list += Interval.over(Limit(0), lowerIncluded = false, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(0), lowerIncluded = true, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(5), lowerIncluded = false, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(5), lowerIncluded = true, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(10), lowerIncluded = false, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(10), lowerIncluded = true, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(15), lowerIncluded = false, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(15), lowerIncluded = true, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(20), lowerIncluded = false, Limitless[Int](), upperIncluded = false)
    list += Interval.over(Limit(20), lowerIncluded = true, Limitless[Int](), upperIncluded = false)

    // 上側限界のみ区間
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(0), upperIncluded = false)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(0), upperIncluded = true)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(5), upperIncluded = false)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(5), upperIncluded = true)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(10), upperIncluded = false)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(10), upperIncluded = true)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(15), upperIncluded = false)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(15), upperIncluded = true)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(20), upperIncluded = false)
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limit(20), upperIncluded = true)

    // freedom
    list += Interval.over(Limitless[Int](), lowerIncluded = false, Limitless[Int](), upperIncluded = false)

    Random.shuffle(list)
  }

  // [[Interval]]のインスタンス生成テスト。
  test("test03_Assertions") {
    // Redundant, maybe, but with all the compiler default
    // confusion at the moment, I decided to throw this in.
    try {
      Interval.closed(Limit(BigDecimal(2.0)), Limit(BigDecimal(1.0)))
      fail("Lower bound mustn't be isAbove isUpper bound.")
    } catch {
      case _: IllegalArgumentException => ()
      // success
    }
  }

  // [[Interval#upTo(Comparable)]]のテスト。
  test("test04_UpTo") {
    val range = Interval.upTo(Limit(5.5d))
    assert(range.includes(Limit(5.5d)))
    assert(range.includes(Limit(-5.5d)))
    assert(range.includes(Limit(Double.NegativeInfinity)))
    assert(!range.includes(Limit(5.5001d)))
  }

  // [[Interval#andMore(Comparable)]]のテスト。
  test("test05_AndMore") {
    val range = Interval.andMore(Limit(5.5d))
    assert(range.includes(Limit(5.5d)))
    assert(!range.includes(Limit(5.4999d)))
    assert(!range.includes(Limit(-5.5d)))
    assert(range.includes(Limit(Double.PositiveInfinity)))
    assert(range.includes(Limit(5.5001d)))
  }

  // [[Interval#newOfSameType(Comparable,boolean,Comparable,boolean)]]のテスト。
  test("test06_AbstractCreation") {
    val concrete    = new Interval(Limit(1), isLowerClosed = true, upper = Limit(3), isUpperClosed = true)
    val newInterval = concrete.newOfSameType(Limit(1), lowerClosed = false, Limit(4), upperClosed = false)
    val expected    = new Interval(Limit(1), isLowerClosed = false, upper = Limit(4), isUpperClosed = false)
    assert(newInterval == expected)
  }

  // [[Interval#isBelow(Comparable)]]のテスト。
  test("test07_Below") {
    val range = Interval.closed(Limit(BigDecimal(-5.5)), Limit(BigDecimal(6.6)))
    assert(!range.isBelow(Limit(BigDecimal(5.0))))
    assert(!range.isBelow(Limit(BigDecimal(-5.5))))
    assert(!range.isBelow(Limit(BigDecimal(-5.4999))))
    assert(!range.isBelow(Limit(BigDecimal(6.6))))
    assert(range.isBelow(Limit(BigDecimal(6.601))))
    assert(!range.isBelow(Limit(BigDecimal(-5.501))))
  }

  // [[Interval#includes(Comparable)]]のテスト。
  test("test08_Includes") {
    val range = Interval.closed(Limit(BigDecimal(-5.5)), Limit(BigDecimal(6.6)))
    assert(range.includes(Limit(BigDecimal(5.0))))
    assert(range.includes(Limit(BigDecimal(-5.5))))
    assert(range.includes(Limit(BigDecimal(-5.4999))))
    assert(range.includes(Limit(BigDecimal(6.6))))
    assert(!range.includes(Limit(BigDecimal(6.601))))
    assert(!range.includes(Limit(BigDecimal(-5.501))))
  }

  // [[Interval]]の開閉の境界挙動テスト。
  test("test09_OpenInterval") {
    val exRange =
      Interval.over(Limit(BigDecimal(-5.5)), lowerIncluded = false, Limit(BigDecimal(6.6)), upperIncluded = true)
    assert(exRange.includes(Limit(BigDecimal(5.0))))
    assert(!exRange.includes(Limit(BigDecimal(-5.5))))
    assert(exRange.includes(Limit(BigDecimal(-5.4999))))
    assert(exRange.includes(Limit(BigDecimal(6.6))))
    assert(!exRange.includes(Limit(BigDecimal(6.601))))
    assert(!exRange.includes(Limit(BigDecimal(-5.501))))
  }

  // [[Interval#isEmpty()]]のテスト。
  test("test10_IsEmpty") {
    assert(!Interval.closed(Limit(5), Limit(6)).isEmpty)
    assert(!Interval.closed(Limit(6), Limit(6)).isEmpty)
    assert(Interval.open(Limit(6), Limit(6)).isEmpty)
    assert(c1_10c.emptyOfSameType.isEmpty)
  }

  // [[Interval#intersects(Interval)]]のテスト。
  test("test11_Intersects") {
    assert(c5_10c.intersects(c1_10c))
    assert(c1_10c.intersects(c5_10c))
    assert(c4_6c.intersects(c1_10c))
    assert(c1_10c.intersects(c4_6c))
    assert(c5_10c.intersects(c5_15c))
    assert(c5_15c.intersects(c1_10c))
    assert(c1_10c.intersects(c5_15c))
    assert(!c1_10c.intersects(c12_16c))
    assert(!c12_16c.intersects(c1_10c))
    assert(c5_10c.intersects(c5_10c))
    assert(!c1_10c.intersects(o10_12c))
    assert(!o10_12c.intersects(c1_10c))

    assert(c5_10c.intersects(c5_10c))
    assert(c5_10c.intersects(c1_10c))
    assert(c5_10c.intersects(c4_6c))
    assert(c5_10c.intersects(c5_15c))
    assert(!c5_10c.intersects(c12_16c))
    assert(!c5_10c.intersects(o10_12c))
    assert(!c5_10c.intersects(o1_1c))
    assert(!c5_10c.intersects(c1_1o))
    assert(!c5_10c.intersects(c1_1c))
    assert(!c5_10c.intersects(o1_1o))
    assert(!c5_10c.intersects(_2o))
    assert(c5_10c.intersects(o9_))
    assert(!c5_10c.intersects(empty))
    assert(c5_10c.intersects(all))

    assert(c1_10c.intersects(c5_10c))
    assert(c1_10c.intersects(c1_10c))
    assert(c1_10c.intersects(c4_6c))
    assert(c1_10c.intersects(c5_15c))
    assert(!c1_10c.intersects(c12_16c))
    assert(!c1_10c.intersects(o10_12c))
    assert(c1_10c.intersects(o1_1c))
    assert(c1_10c.intersects(c1_1o))
    assert(c1_10c.intersects(c1_1c))
    assert(!c1_10c.intersects(o1_1o))
    assert(c1_10c.intersects(_2o))
    assert(c1_10c.intersects(o9_))
    assert(!c1_10c.intersects(empty))
    assert(c1_10c.intersects(all))

    assert(c4_6c.intersects(c5_10c))
    assert(c4_6c.intersects(c1_10c))
    assert(c4_6c.intersects(c4_6c))
    assert(c4_6c.intersects(c5_15c))
    assert(!c4_6c.intersects(c12_16c))
    assert(!c4_6c.intersects(o10_12c))
    assert(!c4_6c.intersects(o1_1c))
    assert(!c4_6c.intersects(c1_1o))
    assert(!c4_6c.intersects(c1_1c))
    assert(!c4_6c.intersects(o1_1o))
    assert(!c4_6c.intersects(_2o))
    assert(!c4_6c.intersects(o9_))
    assert(!c4_6c.intersects(empty))
    assert(c4_6c.intersects(all))

    assert(c5_15c.intersects(c5_10c))
    assert(c5_15c.intersects(c1_10c))
    assert(c5_15c.intersects(c4_6c))
    assert(c5_15c.intersects(c5_15c))
    assert(c5_15c.intersects(c12_16c))
    assert(c5_15c.intersects(o10_12c))
    assert(!c5_15c.intersects(o1_1c))
    assert(!c5_15c.intersects(c1_1o))
    assert(!c5_15c.intersects(c1_1c))
    assert(!c5_15c.intersects(o1_1o))
    assert(!c5_15c.intersects(_2o))
    assert(c5_15c.intersects(o9_))
    assert(!c5_15c.intersects(empty))
    assert(c5_15c.intersects(all))

    assert(!c12_16c.intersects(c1_10c))
    assert(!o10_12c.intersects(c1_10c))
    assert(!o1_1c.intersects(c4_6c))
    assert(!c1_1o.intersects(c5_15c))
    assert(!c1_1c.intersects(c5_15c))
    assert(!o1_1o.intersects(c12_16c))
    assert(!empty.intersects(o10_12c))
    assert(all.intersects(o10_12c))
  }

  // [[Interval#intersect(Interval)]]のテスト。
  test("test12_Intersection") {
    assert(c5_10c.intersect(c1_10c) == c5_10c)
    assert(c1_10c.intersect(c5_10c) == c5_10c)
    assert(c4_6c.intersect(c1_10c) == c4_6c)
    assert(c1_10c.intersect(c4_6c) == c4_6c)
    assert(c5_10c.intersect(c5_15c) == c5_10c)
    assert(c5_15c.intersect(c1_10c) == c5_10c)
    assert(c1_10c.intersect(c5_15c) == c5_10c)
    assert(c1_10c.intersect(c12_16c).isEmpty)
    assert(c1_10c.intersect(c12_16c) == empty)
    assert(c12_16c.intersect(c1_10c) == empty)
    assert(c5_10c.intersect(c5_10c) == c5_10c)
    assert(c1_10c.intersect(o10_12c) == empty)
    assert(o10_12c.intersect(c1_10c) == empty)
  }

  // [[Interval#greaterOfLowerLimits(Interval)]]のテスト。（内部API）
  test("test13_GreaterOfLowerLimits") {
    assert(c5_10c.greaterOfLowerLimits(c1_10c) == Limit(BigDecimal(5)))
    assert(c1_10c.greaterOfLowerLimits(c5_10c) == Limit(BigDecimal(5)))
    assert(c1_10c.greaterOfLowerLimits(c12_16c) == Limit(BigDecimal(12)))
    assert(c12_16c.greaterOfLowerLimits(c1_10c) == Limit(BigDecimal(12)))
  }

  // [[Interval#lesserOfUpperLimits(Interval)]]のテスト。（内部API）
  test("test14_LesserOfUpperLimits") {
    assert(c5_10c.lesserOfUpperLimits(c1_10c) == Limit(BigDecimal(10)))
    assert(c1_10c.lesserOfUpperLimits(c5_10c) == Limit(BigDecimal(10)))
    assert(c4_6c.lesserOfUpperLimits(c12_16c) == Limit(BigDecimal(6)))
    assert(c12_16c.lesserOfUpperLimits(c4_6c) == Limit(BigDecimal(6)))
  }

  // [[Interval#covers(Interval)]]のテスト。
  test("test15_CoversInterval") {
    assert(!c5_10c.covers(c1_10c))
    assert(c1_10c.covers(c5_10c))
    assert(!c4_6c.covers(c1_10c))
    assert(c1_10c.covers(c4_6c))
    assert(c5_10c.covers(c5_10c))

    val o5_10c = Interval.over(Limit(BigDecimal(5)), lowerIncluded = false, Limit(BigDecimal(10)), upperIncluded = true)
    assert(c5_10c.covers(o5_10c), "isClosed incl left-isOpen")
    assert(o5_10c.covers(o5_10c), "left-isOpen incl left-isOpen")
    assert(!o5_10c.covers(c5_10c), "left-isOpen doesn't include isClosed")

    val o1_10o =
      Interval.over(Limit(BigDecimal(1)), lowerIncluded = false, Limit(BigDecimal(10)), upperIncluded = false)
    assert(!c5_10c.covers(o1_10o))
    assert(o1_10o.covers(o1_10o))
    assert(!o1_10o.covers(c5_10c))
  }

  // [[Interval#gap(Interval)]]のテスト。
  test("test16_Gap") {
    val c1_3c = Interval.closed(Limit(1), Limit(3))
    val c5_7c = Interval.closed(Limit(5), Limit(7))
    val o3_5o = Interval.open(Limit(3), Limit(5))
    val c2_3o = Interval.over(Limit(2), lowerIncluded = true, Limit(3), upperIncluded = false)

    assert(c1_3c.gap(c5_7c) == o3_5o)
    assert(c1_3c.gap(o3_5o).isEmpty)
    assert(c1_3c.gap(c2_3o).isEmpty)
    assert(c2_3o.gap(o3_5o).isSingleElement)
  }

  // [[Interval#complementRelativeTo(Interval)]]のテスト。
  test("test17_RelativeComplementDisjoint") {
    val c1_3c      = Interval.closed(Limit(1), Limit(3))
    val c5_7c      = Interval.closed(Limit(5), Limit(7))
    val complement = c1_3c.complementRelativeTo(c5_7c)
    assert(complement.size == 1)
    assert(complement.head == c5_7c)
  }

  // [[Interval#complementRelativeTo(Interval)]]のテスト。
  test("test18_RelativeComplementDisjointAdjacentOpen") {
    val c1_3o      = Interval.over(Limit(1), lowerIncluded = true, Limit(3), upperIncluded = false)
    val c3_7c      = Interval.closed(Limit(3), Limit(7))
    val complement = c1_3o.complementRelativeTo(c3_7c)
    assert(complement.size == 1)
    assert(complement.head == c3_7c)
  }

  // [[Interval#complementRelativeTo(Interval)]]のテスト。
  test("test19_RelativeComplementOverlapLeft") {
    val c1_5c      = Interval.closed(Limit(1), Limit(5))
    val c3_7c      = Interval.closed(Limit(3), Limit(7))
    val complement = c3_7c.complementRelativeTo(c1_5c)
    val c1_3o      = Interval.over(Limit(1), lowerIncluded = true, Limit(3), upperIncluded = false)
    assert(complement.size == 1)
    assert(complement.head == c1_3o)
  }

  // [[Interval#complementRelativeTo(Interval)]]のテスト。
  test("test20_RelativeComplementOverlapRight") {
    val c1_5c      = Interval.closed(Limit(1), Limit(5))
    val c3_7c      = Interval.closed(Limit(3), Limit(7))
    val complement = c1_5c.complementRelativeTo(c3_7c)
    val o5_7c      = Interval.over(Limit(5), lowerIncluded = false, Limit(7), upperIncluded = true)
    assert(complement.size == 1)
    assert(complement.head == o5_7c)
  }

  // [[Interval#complementRelativeTo(Interval)]]のテスト。
  test("test21_RelativeComplementAdjacentClosed") {
    val c1_3c      = Interval.closed(Limit(1), Limit(3))
    val c5_7c      = Interval.closed(Limit(5), Limit(7))
    val complement = c1_3c.complementRelativeTo(c5_7c)
    assert(complement.size == 1)
    assert(complement.head == c5_7c)
  }

  // [[Interval#complementRelativeTo(Interval)]]のテスト。
  test("test22_RelativeComplementEnclosing") {
    val c3_5c      = Interval.closed(Limit(3), Limit(5))
    val c1_7c      = Interval.closed(Limit(1), Limit(7))
    val complement = c1_7c.complementRelativeTo(c3_5c)
    assert(complement.isEmpty)
  }

  // [[Interval#complementRelativeTo(Interval)]]のテスト。
  test("test23_RelativeComplementEqual") {
    val c1_7c      = Interval.closed(Limit(1), Limit(7))
    val complement = c1_7c.complementRelativeTo(c1_7c)
    assert(complement.isEmpty)
  }

  // [[Interval#complementRelativeTo(Interval)]]のテスト。
  test("test24_RelativeComplementEnclosed") {
    val c3_5c      = Interval.closed(Limit(3), Limit(5))
    val c1_7c      = Interval.closed(Limit(1), Limit(7))
    val c1_3o      = Interval.over(Limit(1), lowerIncluded = true, Limit(3), upperIncluded = false)
    val o5_7c      = Interval.over(Limit(5), lowerIncluded = false, Limit(7), upperIncluded = true)
    val complement = c3_5c.complementRelativeTo(c1_7c)
    assert(complement.size == 2)
    assert(complement.head == c1_3o)
    assert(complement(1) == o5_7c)
  }

  // [[Interval#complementRelativeTo(Interval)]]のテスト。
  test("test25_RelativeComplementEnclosedEndPoint") {
    val o3_5o      = Interval.open(Limit(3), Limit(5))
    val c3_5c      = Interval.closed(Limit(3), Limit(5))
    val complement = o3_5o.complementRelativeTo(c3_5c)
    assert(complement.size == 2)
    assert(complement.head.includes(Limit(3)))
  }

  // [[Interval#isSingleElement()]]のテスト。
  test("test26_IsSingleElement") {
    assert(o1_1c.isSingleElement)
    assert(c1_1c.isSingleElement)
    assert(c1_1o.isSingleElement)
    assert(!c1_10c.isSingleElement)
    assert(!o1_1o.isSingleElement)
  }

  // [[Interval#equals(Interval)]]のテスト。
  test("test27_EqualsForOnePointIntervals") {
    assert(c1_1o == o1_1c)
    assert(c1_1c == o1_1c)
    assert(c1_1c == c1_1o)
    assert(o1_1c != o1_1o)
  }

  // [[Interval#emptyOfSameType()]]のテスト。
  test("test28_EqualsForEmptyIntervals") {
    assert(c4_6c.emptyOfSameType == c1_10c.emptyOfSameType)
  }

  // [[Interval#complementRelativeTo(Interval)]]のテスト。
  test("test29_RelativeComplementEnclosedOpen") {
    val o3_5o      = Interval.open(Limit(3), Limit(5))
    val c1_7c      = Interval.closed(Limit(1), Limit(7))
    val c1_3c      = Interval.closed(Limit(1), Limit(3))
    val c5_7c      = Interval.closed(Limit(5), Limit(7))
    val complement = o3_5o.complementRelativeTo(c1_7c)
    assert(complement.size == 2)
    assert(complement.head == c1_3c)
    assert(complement(1) == c5_7c)
  }

  // [[Interval#toString()]]のテスト。
  test("test30_ToString") {
    assert(c1_10c.toString == "[Limit(1), Limit(10)]")
    assert(o10_12c.toString == "(Limit(10), Limit(12)]")
    assert(empty.toString == "{}")
    assert(Interval.closed(Limit(10), Limit(10)).toString == "{Limit(10)}")
  }

  // [[Interval#complementRelativeTo(Interval)]]のテスト。
  test("test31_RelativeComplementOverlapRightOpen") {
    val c3_7o      = Interval.over(Limit(3), lowerIncluded = true, Limit(6), upperIncluded = false)
    val c1_5o      = Interval.over(Limit(1), lowerIncluded = true, Limit(5), upperIncluded = false)
    val complement = c3_7o.complementRelativeTo(c1_5o)
    val c1_3o      = Interval.over(Limit(1), lowerIncluded = true, Limit(3), upperIncluded = false)
    assert(complement.size == 1)
    assert(complement.head == c1_3o)
  }

  // [[Interval#complementRelativeTo(Interval)]]のテスト。
  test("test32_RelativeComplementOverlapLeftOpen") {
    val o1_5c      = Interval.over(Limit(1), lowerIncluded = false, Limit(5), upperIncluded = true)
    val o3_7c      = Interval.over(Limit(2), lowerIncluded = false, Limit(7), upperIncluded = true)
    val complement = o1_5c.complementRelativeTo(o3_7c)
    val o5_7c      = Interval.over(Limit(5), lowerIncluded = false, Limit(7), upperIncluded = true)
    assert(complement.size == 1)
    assert(complement.head == o5_7c)
  }

}
