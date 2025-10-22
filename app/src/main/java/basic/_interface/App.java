/*
 * インターフェイス概念を学ぶためのメインクラス
 * <p>
 * このクラスは、Javaアプリケーションのエントリーポイントである{@code main}メソッドを含みます。
 * </p>
 * @author Takashi Ogisawa
 * @since 2025/09/01
 * Copyright(c) 2025 Takashi Ogisawa All rights reserved.
 */
package basic._interface;

public class App{
	public interface SampleInterface {
		default String get() {
			return "Base method call";
		}
	}

	public interface SampleInterfaceA extends SampleInterface{
		default String get() {
			return "A method call";
		}
		default String getBase() {
			return SampleInterface.super.get();
		}
	}

	public interface SampleInterfaceB extends SampleInterface{
		default String get() {
			return "B method call";
		}
	}

	public static class Sample implements SampleInterfaceA, SampleInterfaceB {
		public String get() {
			return SampleInterfaceA.super.get();
		}
		public String getB() {
			return SampleInterfaceB.super.get();
		}
	}

	public interface StaticMethodInterface {
		// static メソッド
		static String get() {
			return "My name is " + getMyName();
		}
		// private 付き static メソッド
		private static String getMyName() {
			return "StaticMethodInterface";
		}
	}

	public static void main(String[] args) {
		Sample sample = new Sample(); // インスタンス化
		System.out.println(sample.get()); // A method call
		System.out.println(sample.getB()); // B method call
		System.out.println(sample.getBase()); // Base method call

		System.out.println(StaticMethodInterface.get()); // My name is StaticMethodInterface
	}
}

