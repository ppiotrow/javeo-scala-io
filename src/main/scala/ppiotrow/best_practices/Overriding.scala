package ppiotrow.best_practices

/**
 * https://github.com/alexandru/scala-best-practices/blob/master/sections/2-language-rules.md#26-should-not-declare-abstract-val-or-var-or-lazy-val-members
 */
object Overriding extends App {
    /**
      * val can only be overridden with a val
      * a var can only be overridden with a var
     */
    trait Foo {
      val value: String
    }
    /**
     * choose on inheritance is to use def for abstract members
     */
    trait Foo2 {
      def value: String
    }

    trait Bar extends Foo { val uppercase = value.toUpperCase }

    trait MyValue extends Foo { val value = "hello" }

    // this triggers a NullPointerException
    new Bar with MyValue

    // this works
    new MyValue with Bar
}
