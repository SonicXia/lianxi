package com.sonic.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

/**
 * 题目：请按照给出数据，找出同时满足以下条件的用户：
 * 		偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序，
 * 		只输出一个用户名字
 *
 * 流（Stream）是什么？
 * 是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。
 * 集合讲的是数据，流讲的是计算
 *
 * @author Sonic
 */
public class StreamDemo {
	public static void main(String[] args) {
		User u1 = new User(11, "a", 23);
		User u2 = new User(12, "b", 24);
		User u3 = new User(13, "c", 22);
		User u4 = new User(14, "d", 28);
		User u5 = new User(16, "e", 26);

		List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

		list.stream()
			.filter(u -> {
				return u.getId() % 2 == 0 && u.getAge() > 24;
			})
			.map(u -> u.getName().toUpperCase())
			.sorted((a, b) -> -a.compareTo(b))
			.limit(1)
			.forEach(System.out::println);
	}


}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
class User {
	private Integer id;
	private String name;
	private int age;

}
