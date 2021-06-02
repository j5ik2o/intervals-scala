# intervals-scala

A Scala library for intervals.

[![CI](https://github.com/j5ik2o/intervals-scala/workflows/CI/badge.svg)](https://github.com/j5ik2o/intervals-scala/actions?query=workflow%3ACI)
[![Scala Steward badge](https://img.shields.io/badge/Scala_Steward-helping-blue.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=)](https://scala-steward.org)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.j5ik2o/intervals-scala_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.j5ik2o/intervals-scala_2.13)
[![Scaladoc](http://javadoc-badge.appspot.com/com.github.j5ik2o/intervals-scala_2.13.svg?label=scaladoc)](http://javadoc-badge.appspot.com/com.github.j5ik2o/intervals-scala_2.13/com/github/j5ik2o/akka/persistence/dynamodb/index.html?javadocio=true)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Installation

Add the following to your sbt build (2.12.x, 2.13.x, 3.0.x):

```scala
val version = "..."

libraryDependencies += Seq(
  "com.github.j5ik2o" %% "intervals-scala" % version,
)
```

## Usage

- open interval

```rust
val range = Interval.over(Limit(BigDecimal(-5.5)), lowerIncluded = false, Limit(BigDecimal(6.6)), upperIncluded = true)
assert(range.includes(Limit(BigDecimal(5.0))))
assert(!range.includes(Limit(BigDecimal(-5.5))))
assert(range.includes(Limit(BigDecimal(-5.4999))))
assert(range.includes(Limit(BigDecimal(6.6))))
assert(!range.includes(Limit(BigDecimal(6.601))))
assert(!range.includes(Limit(BigDecimal(-5.501))))
```

- closed interval

```rust
val range = Interval.closed(Limit(BigDecimal(-5.5)), Limit(BigDecimal(6.6)))
assert(range.includes(Limit(BigDecimal(5.0))))
assert(range.includes(Limit(BigDecimal(-5.5))))
assert(range.includes(Limit(BigDecimal(-5.4999))))
assert(range.includes(Limit(BigDecimal(6.6))))
assert(!range.includes(Limit(BigDecimal(6.601))))
assert(!range.includes(Limit(BigDecimal(-5.501))))
```



## License

MIT license ([LICENSE-MIT](LICENSE-MIT) or https://opensource.org/licenses/MIT)
