package com.github.j5ik2o.intervals

import org.scalatest.funsuite.AnyFunSuite

/** `IntervalMap`のテストクラス。
  */
class IntervalMapTest extends AnyFunSuite {

  // [[IntervalMap]]に対する参照メソッドのテスト。
  test("test01_Lookup") {

    var map = new IntervalMap[Int, String]
    map += (Interval.closed(Limit(1), Limit(3)) -> "one-three")
    map += (Interval.closed(Limit(5), Limit(9)) -> "five-nine")
    map += (Interval.open(Limit(9), Limit(12))  -> "ten-eleven")

    assert(!map.contains(Limit(0)))
    assert(map.contains(Limit(1)))
    assert(map.contains(Limit(2)))
    assert(map.contains(Limit(3)))
    assert(!map.contains(Limit(4)))
    assert(map.contains(Limit(5)))
    assert(map.contains(Limit(9)))
    assert(map.contains(Limit(11)))
    assert(!map.contains(Limit(12)))
    assert(!map.contains(Limit(13)))
    assert(!map.contains(Limitless[Int]()))

    assert(map.get(Limit(0)).isEmpty)
    assert(map.get(Limit(1)).contains("one-three"))
    assert(map.get(Limit(2)).contains("one-three"))
    assert(map.get(Limit(3)).contains("one-three"))
    assert(map.get(Limit(4)).isEmpty)
    assert(map.get(Limit(5)).contains("five-nine"))
    assert(map.get(Limit(9)).contains("five-nine"))
    assert(map.get(Limit(10)).contains("ten-eleven"))
    assert(map.get(Limit(11)).contains("ten-eleven"))
    assert(map.get(Limit(12)).isEmpty)
    assert(map.get(Limit(13)).isEmpty)
    assert(map.get(Limitless[Int]()).isEmpty)
  }

  // [[IntervalMap#remove(Interval)]]のテスト。
  test("test02_Remove") {
    var map = new IntervalMap[Int, String]()
    map += (Interval.closed(Limit(1), Limit(10)) -> "one-ten")
    map -= (Interval.closed(Limit(3), Limit(5)))
    assert(map.get(Limit(2)).contains("one-ten"))
    assert(map.get(Limit(3)).isEmpty)
    assert(map.get(Limit(4)).isEmpty)
    assert(map.get(Limit(5)).isEmpty)
    assert(map.get(Limit(6)).contains("one-ten"))
  }

  // [[IntervalMap#put(Interval, Object)]]で割り当て区間が重複した場合、後勝ちになることを確認するテスト。
  test("test03_ConstructionOverwriteOverlap") {
    var map = new IntervalMap[Int, String]()
    map += (Interval.closed(Limit(1), Limit(3)) -> "one-three")
    map += (Interval.closed(Limit(5), Limit(9)) -> "five-nine")
    map += (Interval.open(Limit(9), Limit(12))  -> "ten-eleven")
    assert(map.get(Limit(10)).contains("ten-eleven"))
    assert(map.get(Limit(11)).contains("ten-eleven"))
    assert(map.get(Limit(12)).isEmpty)

    val eleven_thirteen = Interval.closed(Limit(11), Limit(13))
    assert(map.containsIntersectingKey(eleven_thirteen))

    map += (eleven_thirteen -> "eleven-thirteen")
    assert(map.get(Limit(10)).contains("ten-eleven"))
    assert(map.get(Limit(11)).contains("eleven-thirteen"))
    assert(map.get(Limit(12)).contains("eleven-thirteen"))
  }

  // [[IntervalMap#put(Interval, Object)]]で割り当て区間が重複した場合、後勝ちになることを確認するテスト。
  test("test04_ConstructionOverwriteMiddle") {
    var map = new IntervalMap[Int, String]()
    map += (Interval.closed(Limit(1), Limit(3)) -> "one-three")
    map += (Interval.closed(Limit(5), Limit(9)) -> "five-nine")
    map += (Interval.open(Limit(9), Limit(12))  -> "ten-eleven")
    assert(map.get(Limit(6)).contains("five-nine"))
    assert(map.get(Limit(7)).contains("five-nine"))
    assert(map.get(Limit(8)).contains("five-nine"))
    assert(map.get(Limit(9)).contains("five-nine"))

    val seven_eight = Interval.closed(Limit(7), Limit(8))
    assert(map.containsIntersectingKey(seven_eight))
    map += (seven_eight -> "seven-eight")
    assert(map.get(Limit(6)).contains("five-nine"))
    assert(map.get(Limit(7)).contains("seven-eight"))
    assert(map.get(Limit(8)).contains("seven-eight"))
    assert(map.get(Limit(9)).contains("five-nine"))
  }

  // [[IntervalMap#put(Interval, Object)]]で割り当て区間が重複した場合、後勝ちになることを確認するテスト。
  test("test05_ConstructionOverwriteMultiple") {
    var map = new IntervalMap[Int, String]()
    map += (Interval.closed(Limit(1), Limit(2)) -> "one-two")
    map += (Interval.closed(Limit(3), Limit(4)) -> "three-four")
    map += (Interval.closed(Limit(5), Limit(6)) -> "five-six")
    map += (Interval.closed(Limit(8), Limit(9)) -> "eight-nine")
    map += (Interval.closed(Limit(3), Limit(8)) -> "three-eight")
    assert(map.get(Limit(2)).contains("one-two"))
    assert(map.get(Limit(3)).contains("three-eight"))
    assert(map.get(Limit(4)).contains("three-eight"))
    assert(map.get(Limit(5)).contains("three-eight"))
    assert(map.get(Limit(6)).contains("three-eight"))
    assert(map.get(Limit(7)).contains("three-eight"))
    assert(map.get(Limit(8)).contains("three-eight"))
    assert(map.get(Limit(9)).contains("eight-nine"))
  }

}
