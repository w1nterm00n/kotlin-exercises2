// Создать приложение, в котором пользователь вводит массив из различных слов. 
// На выходе приложение должно показать слова сгруппированные по признаку "состоят из одинаковых букв". 
// Например, на входе ["eat", "tea", "tan", "ate", "nat", "bat"]. Получаем группы:
// "ate", "eat", "tea"
// "nat", "tan"
// "bat" 

fun main() {
    println("введите количество слов: ")
    var n = readln()?.toInt() ?: 0
    println("введите слова: ")

    val wordsArr = Array<String>(n) { "" }
    val set = mutableSetOf<Set<Char>>() //сет с сетами букв из которых состоят слова

    for (i in 0..(n-1)){
        wordsArr[i] = readln()
        //цикл который побуквенно записывает это слово в побуквенный сет
        val letterSet = mutableSetOf<Char>()
        for (j in 0..(wordsArr[i].length -1)) {
            letterSet.add(wordsArr[i][j])
        }
        set.add(letterSet)
    }
    
    val arr = Array(set.size) { mutableListOf<String>() }

    for (i in 0..(n-1)){
        val letterSet = mutableSetOf<Char>()
        for (j in 0..(wordsArr[i].length -1)) {
            letterSet.add(wordsArr[i][j])
        }
        for (j in 0..(set.size -1)){
            if (letterSet.size == set.elementAt(j).size && letterSet.containsAll(set.elementAt(j))){
                arr[j].add(wordsArr[i])
            }
        }
    }

    for (i in 0..(set.size-1)){
        println(arr[i])
    }
}