1. 
Функция main является точкой входа в программу. Создается структура testData, 
генерируется 100 телефонов и выводится количество сгенерированных номеров

2. 
Самое простое решение - это не использовать горутины и вместо go td.add() использовать td.add()
Другое решение с использованием мьютексов в main.go

3. 
Тесты написаны и находятся src/test/kotlin/GeneratorTest.kt 
Для тестов используется JUnit5
Основная прошрамме написана на котлине и находится в src/main/kotlin/Main.kt