package com.github.j5ik2o.intervals

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ListBuffer
import scala.util.Random

/** `IntervalLimit`のテストクラス。
  */
class IntervalLimitTest extends AnyFunSuite {

  test("test01_Equals") {

    assert(IntervalLimit.lower(closed = false, Limit(10)) == IntervalLimit.lower(closed = false, Limit(10)))
    assert(IntervalLimit.lower(closed = true, Limit(10)) != IntervalLimit.lower(closed = false, Limit(10)))
    assert(IntervalLimit.lower(closed = false, Limit(10)) != IntervalLimit.lower(closed = true, Limit(10)))
    assert(IntervalLimit.lower(closed = true, Limit(10)) == IntervalLimit.lower(closed = true, Limit(10)))

    assert(IntervalLimit.upper(closed = false, Limit(10)) == IntervalLimit.upper(closed = false, Limit(10)))
    assert(IntervalLimit.upper(closed = true, Limit(10)) != IntervalLimit.upper(closed = false, Limit(10)))
    assert(IntervalLimit.upper(closed = false, Limit(10)) != IntervalLimit.upper(closed = true, Limit(10)))
    assert(IntervalLimit.upper(closed = true, Limit(10)) == IntervalLimit.upper(closed = true, Limit(10)))

    assert(IntervalLimit.lower(closed = false, Limit(10)) != IntervalLimit.upper(closed = false, Limit(10)))
    assert(IntervalLimit.lower(closed = true, Limit(10)) != IntervalLimit.upper(closed = false, Limit(10)))
    assert(IntervalLimit.lower(closed = false, Limit(10)) != IntervalLimit.upper(closed = true, Limit(10)))
    assert(IntervalLimit.lower(closed = true, Limit(10)) != IntervalLimit.upper(closed = true, Limit(10)))

    assert(IntervalLimit.upper(closed = false, Limit(10)) != IntervalLimit.lower(closed = false, Limit(10)))
    assert(IntervalLimit.upper(closed = true, Limit(10)) != IntervalLimit.lower(closed = false, Limit(10)))
    assert(IntervalLimit.upper(closed = false, Limit(10)) != IntervalLimit.lower(closed = true, Limit(10)))
    assert(IntervalLimit.upper(closed = true, Limit(10)) != IntervalLimit.lower(closed = true, Limit(10)))

    assert(
      IntervalLimit(closed = false, lower = false, Limit(1)) == IntervalLimit(closed = false, lower = false, Limit(1))
    )

    assert(IntervalLimit.lower(closed = false, Limit(10)) == IntervalLimit.lower(closed = false, Limit(10)))
    assert(IntervalLimit(closed = false, lower = true, Limit(10)) == IntervalLimit.lower(closed = false, Limit(10)))
    // assert(new IntervalLimit(false, true, Limit(10)){ } != IntervalLimit.isLower(false, Limit(10)))
  }

  test("test02_compareTo") {
    val lowerInf    = IntervalLimit.lower(closed = false, Limitless[Int]())
    val upperInf    = IntervalLimit.upper(closed = false, Limitless[Int]())
    val lowerOpen2  = IntervalLimit.lower(closed = false, Limit(2))
    val lowerClose2 = IntervalLimit.lower(closed = true, Limit(2))
    val lowerOpen3  = IntervalLimit.lower(closed = false, Limit(3))
    val lowerClose3 = IntervalLimit.lower(closed = true, Limit(3))
    val upperOpen5  = IntervalLimit.upper(closed = false, Limit(5))
    val upperClose5 = IntervalLimit.upper(closed = true, Limit(5))
    val upperOpen6  = IntervalLimit.upper(closed = false, Limit(6))
    val upperClose6 = IntervalLimit.upper(closed = true, Limit(6))

    // 無限同士比較
    assert(lowerInf < upperInf)
    assert(upperInf > lowerInf)

    // 無限有限比較
    assert(lowerInf < lowerOpen3)
    assert(lowerInf < lowerClose3)
    assert(lowerInf < upperOpen5)
    assert(lowerInf < upperClose5)
    assert(upperInf > lowerOpen3)
    assert(upperInf > lowerClose3)
    assert(upperInf > upperOpen5)
    assert(upperInf > upperClose5)

    // 有限無限比較（上記セクションとの対象性検証）
    assert(lowerOpen3 > lowerInf)
    assert(lowerClose3 > lowerInf)
    assert(upperOpen5 > lowerInf)
    assert(upperClose5 > lowerInf)
    assert(lowerOpen3 < upperInf)
    assert(lowerClose3 < upperInf)
    assert(upperOpen5 < upperInf)
    assert(upperClose5 < upperInf)

    // 有限比較
    assert(lowerClose2 == lowerClose2)
    assert(lowerClose2 < lowerOpen2)
    assert(lowerClose2 < lowerClose3)
    assert(lowerClose2 < lowerOpen3)
    assert(lowerClose2 < upperClose5)
    assert(lowerClose2 < upperOpen5)
    assert(lowerClose2 < upperClose6)
    assert(lowerClose2 < upperOpen6)

    assert(lowerOpen2 > lowerClose2)
    assert(lowerOpen2 == lowerOpen2)
    assert(lowerOpen2 < lowerClose3)
    assert(lowerOpen2 < lowerOpen3)
    assert(lowerOpen2 < upperClose5)
    assert(lowerOpen2 < upperOpen5)
    assert(lowerOpen2 < upperClose6)
    assert(lowerOpen2 < upperOpen6)

    assert(lowerClose3 > lowerClose2)
    assert(lowerClose3 > lowerOpen2)
    assert(lowerClose3 == lowerClose3)
    assert(lowerClose3 < lowerOpen3)
    assert(lowerClose3 < upperClose5)
    assert(lowerClose3 < upperOpen5)
    assert(lowerClose3 < upperClose6)
    assert(lowerClose3 < upperOpen6)

    assert(lowerOpen3 > lowerClose2)
    assert(lowerOpen3 > lowerOpen2)
    assert(lowerOpen3 > lowerClose3)
    assert(lowerOpen3 == lowerOpen3)
    assert(lowerOpen3 < upperClose5)
    assert(lowerOpen3 < upperOpen5)
    assert(lowerOpen3 < upperClose6)
    assert(lowerOpen3 < upperOpen6)

    assert(upperClose5 > lowerClose2)
    assert(upperClose5 > lowerOpen2)
    assert(upperClose5 > lowerClose3)
    assert(upperClose5 > lowerOpen3)
    assert(upperClose5 == upperClose5)
    assert(upperClose5 > upperOpen5)
    assert(upperClose5 < upperClose6)
    assert(upperClose5 < upperOpen6)

    assert(upperOpen5 > lowerClose2)
    assert(upperOpen5 > lowerOpen2)
    assert(upperOpen5 > lowerClose3)
    assert(upperOpen5 > lowerOpen3)
    assert(upperOpen5 < upperClose5)
    assert(upperOpen5 == upperOpen5)
    assert(upperOpen5 < upperClose6)
    assert(upperOpen5 < upperOpen6)

    assert(upperClose6 > lowerClose2)
    assert(upperClose6 > lowerOpen2)
    assert(upperClose6 > lowerClose3)
    assert(upperClose6 > lowerOpen3)
    assert(upperClose6 > upperClose5)
    assert(upperClose6 > upperOpen5)
    assert(upperClose6 == upperClose6)
    assert(upperClose6 > upperOpen6)

    assert(upperOpen6 > lowerClose2)
    assert(upperOpen6 > lowerOpen2)
    assert(upperOpen6 > lowerClose3)
    assert(upperOpen6 > lowerOpen3)
    assert(upperOpen6 > upperClose5)
    assert(upperOpen6 > upperOpen5)
    assert(upperOpen6 < upperClose6)
    assert(upperOpen6 == upperOpen6)

  }

  test("test03_sort") {
    val list = ListBuffer[IntervalLimit[Int]]()

    list += IntervalLimit.upper(closed = false, Limitless[Int]())
    list += IntervalLimit.upper(closed = true, Limitless[Int]())
    list += IntervalLimit.lower(closed = false, Limitless[Int]())
    list += IntervalLimit.lower(closed = true, Limitless[Int]())
    list += IntervalLimit.lower(closed = true, Limit(1))
    list += IntervalLimit.lower(closed = false, Limit(1))
    list += IntervalLimit.lower(closed = true, Limit(5))
    list += IntervalLimit.lower(closed = false, Limit(5))
    list += IntervalLimit.upper(closed = true, Limit(1))
    list += IntervalLimit.upper(closed = false, Limit(1))
    list += IntervalLimit.upper(closed = true, Limit(5))
    list += IntervalLimit.upper(closed = false, Limit(5))

    val list2 = Random.shuffle(list)
    //    list2.foreach(println)
    //    println("---")
    val sortedList = list2.sorted
    //    for(i <- 0 until sortedList.size){
    //      println("%d:%s".format(i, sortedList(i)))
    //    }

    assert(sortedList(0) == IntervalLimit.lower(closed = false, Limitless[Int]()))
    assert(sortedList(1) == IntervalLimit.lower(closed = false, Limitless[Int]()))

    assert(sortedList(2) == IntervalLimit.lower(closed = true, Limit(1)))
    assert(sortedList(3) == IntervalLimit.lower(closed = false, Limit(1)))
    assert(sortedList(4) == IntervalLimit.upper(closed = false, Limit(1)))
    assert(sortedList(5) == IntervalLimit.upper(closed = true, Limit(1)))
    assert(sortedList(6) == IntervalLimit.lower(closed = true, Limit(5)))
    assert(sortedList(7) == IntervalLimit.lower(closed = false, Limit(5)))
    assert(sortedList(8) == IntervalLimit.upper(closed = false, Limit(5)))
    assert(sortedList(9) == IntervalLimit.upper(closed = true, Limit(5)))

    assert(sortedList(10) == IntervalLimit.upper(closed = false, Limitless[Int]()))
    assert(sortedList(11) == IntervalLimit.upper(closed = false, Limitless[Int]()))

  }
}
