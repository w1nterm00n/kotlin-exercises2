// Создать приложение, в котором пользователь дает на вход два числовых массива, числа в массиве могут повторяться. 
// На выходе приложение выдает пересечение этих массивов. 
// Например, на входе [1, 2, 3, 2, 0] и [5, 1, 2, 7, 3, 2, 2]. 
// На выходе должны получить [1, 2, 2, 3]. 
// То есть учитывается количество повторений чисел в массиве. 
// Число 2 в одном массиве повторялось два раза, в другом - три. 
// Значит в итоговом массиве число два должно быть два раза


fun main() {
    //получаю массивы
    println("введите сколько элементов в 1 массиве ")
    var n1 = readLine()?.toInt() ?: 0
    var arr1 = IntArray(n1)
    println("введите 1 массив: ")
    for (i in 0..(n1-1)){
        arr1[i] = readLine()?.toInt() ?: 0
    }

    println("введите сколько элементов в 2 массиве ")
    var n2 = readLine()?.toInt() ?: 0
    var arr2 = IntArray(n2)
    println("введите 2 массив: ")
    for (i in 0..(n2-1)){
        arr2[i] = readLine()?.toInt() ?: 0
    }

    //создаю мап из каждого массива
    var map1 = mutableMapOf<Int, Int>()
    for (i in 0..arr1.size-1) {
        var currentValue = map1[arr1[i]]
        if (currentValue != null) {
            map1[arr1[i]] = currentValue + 1
        } else {
            map1[arr1[i]] = 1
        }
    }

    var map2 = mutableMapOf<Int, Int>()

    for (i in 0..arr2.size-1) {
        var currentValue = map2[arr2[i]]
        if (currentValue != null) {
            map2[arr2[i]] = currentValue + 1
        } else {
            map2[arr2[i]] = 1
        }
    }



    //нахожу какие именно числа повторяются
    var map3 = mutableMapOf<Int, Int>()

    for (key1 in map1.keys) {
        for (key2 in map2.keys) {
            if (key1 == key2) {
                map3[key1] = 0
            }
        }
    }

    //нахожу сколько раз эти числа повторяются
    for (key1 in map1.keys) {
        for (key2 in map2.keys) {
            if (key1 == key2) {
                val minValue = minOf(map1[key1] ?: 0, map2[key2] ?: 0)
                map3[key1] = minValue
            }
        }
    }
    println("пересечение массивов: ")
    println(map3)
}