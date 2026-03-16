# Java Basic Education Project

This project serves as a collection of educational Java basic examples and sample applications. It's designed to help users learn and understand fundamental Java programming concepts through various practical implementations.

# udemy education program
「元Microsoftエンジニア直伝｜AI時代に通用するJavaの本質とプロの設計思考~入門編~」
https://www.udemy.com/course/og-java-basic/

## Features

*   Collection of Java basic programming examples.
*   Built with Java and Gradle.
*   Includes various sample applications for different concepts (e.g., chapters, tutorials).

## Requirements

*   Java 17 or higher

## Installation and Build

1.  Clone the repository:
    ```sh
    git clone <repository-url>
    cd java-basic
    ```

2.  Build the project:
    ```sh
    ./gradlew build
    ```

## How to Run Specific Examples

To run a specific example, use the `./gradlew run` command with the `-PmainClass` argument, specifying the fully qualified name of the `App.java` class you wish to execute. For example:

```sh
./gradlew run -PmainClass=basic.chapter_0.App
```

Replace `basic.chapter_0.App` with the path to the `App.java` file you want to run (e.g., `basic.chapter_1.App`, `basic.tutorials.diamond.DiamondStep1`).

## License

This project is licensed under the Mozilla Public License Version 2.0.
*   US: https://www.mozilla.org/en-US/MPL/2.0/
*   JP: https://www.mozilla.jp/documents/mpl/2.0/

