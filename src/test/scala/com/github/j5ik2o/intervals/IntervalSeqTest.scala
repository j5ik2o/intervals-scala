package com.github.j5ik2o.intervals

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ArrayBuffer

/** `IntervalSeq`のテストクラス。
  */
class IntervalSeqTest extends AnyFunSuite {

  private val c5_10c = Interval.closed(Limit(5), Limit(10))

  private val o10_12c = Interval.over(Limit(10), lowerIncluded = false, Limit(12), upperIncluded = true)

  private val o11_20c = Interval.over(Limit(11), lowerIncluded = false, Limit(20), upperIncluded = true)

  private val o12_20o = Interval.open(Limit(12), Limit(20))

  private val c20_25c = Interval.closed(Limit(20), Limit(25))

  private val o25_30c = Interval.over(Limit(25), lowerIncluded = false, Limit(30), upperIncluded = true)

  private val o30_35o = Interval.open(Limit(30), Limit(35))

  private val o11_12c = Interval.over(Limit(11), lowerIncluded = false, Limit(12), upperIncluded = true)

  private val c20_20c = Interval.closed(Limit(20), Limit(20))

  private val _o18 = Interval.under(Limit(18))

  private val empty = Interval.closed(Limit(0), Limit(0))

  private val all = Interval.open(Limitless[Int](), Limitless[Int]())

  // [[IntervalSeq#iterator]]のテスト。
  test("test01_Iterate") {
    var intervalSequence = new IntervalSeq[Int]
    assert(intervalSequence.isEmpty)
    intervalSequence :+= empty
    intervalSequence :+= c5_10c
    intervalSequence :+= o10_12c
    val it = intervalSequence.iterator
    assert(it.hasNext)
    assert(it.next() == empty)
    assert(it.hasNext)
    assert(it.next() == c5_10c)
    assert(it.hasNext)
    assert(it.next() == o10_12c)
    assert(!it.hasNext)
    try {
      it.next()
      fail("Should throw NoSuchElementException")
    } catch {
      case _: NoSuchElementException =>
      // success
      case _: Throwable => fail()
    }
  }

  // [[IntervalSeq#add(Interval)]]が順不同で行われた場合の[[IntervalSequence]]のテスト。
  test("test02_InsertedOutOfOrder") {
    var intervalSequence = new IntervalSeq[Int]
    intervalSequence :+= o10_12c
    intervalSequence :+= c5_10c
    // Iterator behavior should be the same regardless of order of insertion.
    val it = intervalSequence.iterator
    assert(it.hasNext)
    assert(it.next() == c5_10c)
    assert(it.hasNext)
    assert(it.next() == o10_12c)
    assert(!it.hasNext)
    try {
      it.next()
      fail("Should throw NoSuchElementException")
    } catch {
      case _: NoSuchElementException => // success
      case _: Throwable              => fail()
    }
  }

  // 重なる区間を含んだ[[IntervalSeq]]のテスト。
  test("test03_Overlapping") {
    var intervalSequence = new IntervalSeq[Int]
    intervalSequence :+= o10_12c
    intervalSequence :+= o11_20c
    val it = intervalSequence.iterator
    assert(it.hasNext)
    assert(it.next() == o10_12c)
    assert(it.hasNext)
    assert(it.next() == o11_20c)
    assert(!it.hasNext)
    try {
      it.next()
      fail("Should throw NoSuchElementException")
    } catch {
      case _: NoSuchElementException => // success
      case _: Throwable              => fail()
    }
  }

  // [[IntervalSeq#intersections]]のテスト。
  test("test04_Intersections") {
    var intervalSequence = IntervalSeq[Int]()
    intervalSequence :+= o10_12c
    intervalSequence :+= o11_20c
    intervalSequence :+= c20_25c

    val it = intervalSequence.intersections.iterator
    assert(it.hasNext)
    assert(it.next() == o11_12c)
    assert(it.hasNext)
    assert(it.next() == c20_20c)
    assert(!it.hasNext)
    try {
      it.next()
      fail("Should throw NoSuchElementException")
    } catch {
      case _: NoSuchElementException =>
      case _: Throwable              => fail()
    }
  }

  // [[IntervalSeq#gaps]]のテスト。
  test("test05_Gaps") {
    var intervalSeq = IntervalSeq[Int]()
    intervalSeq :+= c5_10c
    intervalSeq :+= o10_12c
    intervalSeq :+= c20_25c
    intervalSeq :+= o30_35o

    val it = intervalSeq.gaps.iterator
    assert(it.hasNext)
    assert(it.next() == o12_20o)
    assert(it.hasNext)
    assert(it.next() == o25_30c)
    assert(!it.hasNext)
    try {
      it.next()
      fail("Should throw NoSuchElementException")
    } catch {
      case _: NoSuchElementException =>
      // success
      case _: Throwable => fail()
    }
  }

  // [[IntervalSeq#extent]]のテスト。
  test("test06_Extent") {
    val intervals = ArrayBuffer.empty[Interval[Int]]
    intervals += c5_10c
    intervals += o10_12c
    intervals += c20_25c

    val intervalSequence1 = IntervalSeq(intervals.toVector)
    assert(intervalSequence1.extent.contains(Interval.closed(Limit(5), Limit(25))))

    intervals += _o18
    val intervalSequence2 = IntervalSeq(intervals.toVector)
    assert(intervalSequence2.extent.contains(Interval.closed(Limitless[Int](), Limit(25))))

    intervals += all
    val intervalSequence3 = IntervalSeq(intervals.toVector)
    assert(intervalSequence3.extent.contains(all))

    //		for (seq <- variousSequences()) {
    //			seq.add(c5_10c);
    //			seq.add(o10_12c);
    //			seq.add(c20_25c);
    //			assertThat(seq.extent(), is(Interval.isClosed(5, 25)));
    //
    //			seq.add(_o18);
    //			assertThat(seq.extent(), is(Interval.isClosed(null, 25)));
    //
    //			seq.add(all);
    //			assertThat(seq.extent(), is(all));
    //		}

  }

}
