package com.sonic.stream;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Sonic
 */
public class FunctionInterfaceDemo {
	public static void main(String[] args) {
		/**
		 * 消费型接口 Consumer<T>
		 *   参数类型：T
		 *   返回类型：void
		 *   用途：对类型为T的对象应用操作，包含方法：accept(T t)
		 */
		/*Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String s) {

			}
		};*/
		Consumer<String> consumer = s -> {
			System.out.println(s);
		};
		consumer.accept("输入hello");

		/**
		 * 供给型接口 Supplier<T>
		 *   参数类型：无
		 *   返回类型：T
		 *   用途：返回类型为T的对象，包含方法：T get()
		 */
		/*Supplier<String> supplier = new Supplier<String>() {
			@Override
			public String get() {
				return null;
			}
		};*/
		Supplier<String> supplier = () -> {
			return "什么都没有";
		};
		System.out.println(supplier.get());

		/**
		 * 函数型接口 Function<T, R>
		 *   参数类型：T
		 *   返回类型：R
		 *   用途：对类型为T的对象应用操作，并返回结果。结果是R类型的对象。包含方法：R apply(T t)
		 */
		/*Function<String, Integer> function = new Function<String, Integer>() {
			@Override
			public Integer apply(String s) {
				return null;
			}
		};*/
		Function<String, Integer> function = s -> {
			return 1;
		};
		System.out.println(function.apply("sss"));

		/**
		 * 断定型接口 Predicate<T>
		 *   参数类型：T
		 *   返回类型：boolean
		 *   用途：确定类型为T的对象是否满足约束，并返回boolean值。包含方法： boolean test(T t)
		 */
		/*Predicate<String> predicate = new Predicate<String>() {
			@Override
			public boolean test(String s) {
				return false;
			}
		};*/
		Predicate<String> predicate = s -> {
			return s.equals("123");
		};
		System.out.println(predicate.test("111"));
	}
}
