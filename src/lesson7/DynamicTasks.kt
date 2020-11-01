@file:Suppress("UNUSED_PARAMETER")

package lesson7

import lesson1.sort
import java.util.Collections.reverse
import kotlin.math.max


/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * производительность O(nm)
 */
fun longestCommonSubSequence(first: String, second: String): String {
    val num = Array(first.length + 1) { IntArray(second.length + 1) }

    for (i in (first.length - 1) downTo 0) {
        for (j in (second.length - 1) downTo 0) {
            if (first[i] == second[j]) {
                num[i][j] = 1 + num[i + 1][j + 1]
            } else {
                num[i][j] = max(num[i + 1][j], num[i][j + 1])
            }
        }
    }

    val res = StringBuilder()
    var i = 0
    var j = 0
    while (num[i][j] != 0 && i < first.length && j < second.length)
        if (first[i] == second[j]) {
            res.append(first[i])
            i += 1
            j += 1
        } else {
            if (num[i][j] == num[i + 1][j]) {
                i += 1
            } else {
                j += 1
            }
        }
    return res.toString()

}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    val n = list.size
    if (n == 0) return emptyList()
    val prev = IntArray(n)
    val d = IntArray(n)

    for (i in 0 until n) {
        d[i] = 1
        prev[i] = -1
        for (j in 0 until i) {
            if (list[j] < list[i] && d[j] + 1 > d[i]) {
                d[i] = d[j] + 1
                prev[i] = j
            }
        }
    }
    var pos = 0
    var length = d[0]
    for (i in 0 until n) {
        if (d[i] > length) {
            pos = i
            length = d[i]
        }
    }
    var answer = mutableListOf<Int>()
    while (pos != -1) {
        answer.add(list[pos])
        pos = prev[pos]
    }
    answer = sort(answer.toIntArray()).toMutableList()
    return answer
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5