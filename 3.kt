// Имеется массив из символов русского алфавита (все 33 символа, могут быть не по порядку). 
// Символы алфавита нумеруются от 1 до 33. Каждое число используется только один раз.  
// Сообщение шифруется с помощью ключевого слова, задаваемого пользователем. 
// Номер символа ключевого слова показывает, на сколько нужно сдвинуться по массиву из символов русского алфавита.
//  Составить программу шифровки и дешифровки строкового выражения (то есть программа спрашивает - зашифровать или расшифровать текст и ключевое слово). 
// Первый массив считать закольцованным. Регистр букв не имеет значения. 

fun main() {
    val alphabet = listOf(
        'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М',
        'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь',
        'Ы', 'Ъ', 'Э', 'Ю', 'Я'
    )

    println("введите ключевое слово ")
    var keyWord = readln().toUpperCase()
    var keyWordCode = mutableListOf<Int>()
    //шифрую ключевое слово
    for (i in 0..(keyWord.length -1)){
        var letter = keyWord[i]
        for (j in 0..(alphabet.size-1)){
            if (alphabet[j] == letter) {
                keyWordCode.add(j)
            }
        }
    }

    println("введите 1 чтобы зашифровать, или 2 чтобы расшифровать")
    var mode = readLine()?.toInt() ?: 0
    if (mode == 1) {                        //ЗАШИФРОВЫВАНИЕ

        println("введите исходный текст")
        var text = readln().toUpperCase()
        var textDefaultCode = mutableListOf<Int>()
        //шифрую текст первый раз
        for (i in 0..(text.length-1)){
            for (j in 0..(alphabet.size-1)){
                if (alphabet[j] == text[i]) {
                    textDefaultCode.add(j)
                }
                if (text[i] == ' '){
                    textDefaultCode.add(34)     //34 значит пробел
                }
            }
        }
        
        //сдвигаю индексы и записываю новые значения в textChangedCode
        var textChangedCode = mutableListOf<Int>()
        var keyWordIndex = 0
        for (i in 0..(textDefaultCode.size-1)){
            if (textDefaultCode[i] != 34) {
                if (keyWordIndex == keyWordCode.size) {
                    keyWordIndex = 0
                }
                var num = textDefaultCode[i] + keyWordCode[keyWordIndex]
                if (num > 33){     //чтобы он был типа закольцованный
                    num = num - 33
                }
                textChangedCode.add(num)
                keyWordIndex++
            } else {
                textChangedCode.add(34)
            }
        }
    
        //расшифровываю в буквы
        for (i in 0..(textChangedCode.size-1)){
            if (textChangedCode[i] == 34){
                print (" ")
                continue
            }
            
            for (j in 0..(alphabet.size-1)){
                if (textChangedCode[i] == j) {
                    print (alphabet[j])
                }
            }
        }    
    } else if (mode == 2) {                     //РАЗШИФРОВЫВАНИЕ
        println("введите зашифрованный текст: ")
        var text = readln().toUpperCase()
        var textDefaultCode = mutableListOf<Int>()
        //перевожу шифр в цифры
        for (i in 0..(text.length-1)){
            for (j in 0..(alphabet.size-1)){
                if (alphabet[j] == text[i]) {
                    textDefaultCode.add(j)
                }
                if (text[i] == ' '){
                    textDefaultCode.add(34)
                }
            }
        }

        //сдвигаю индексы и записываю новые значения в textChangedCode
        var textChangedCode = mutableListOf<Int>()
        var keyWordIndex = 0
        for (i in 0..(textDefaultCode.size-1)){
            if (textDefaultCode[i] != 34) {
                if (keyWordIndex == keyWordCode.size) {
                    keyWordIndex = 0
                }
                var num = textDefaultCode[i] - keyWordCode[keyWordIndex]
                if (num < 0){     //чтобы он был типа закольцованный
                    num = num + 33
                }
                textChangedCode.add(num)
                keyWordIndex++
            } else {
                textChangedCode.add(34)
            }
        }

        //расшифровываю в буквы
        for (i in 0..(textChangedCode.size-1)){
            if (textChangedCode[i] == 34){
                print (" ")
                continue
            }
            
            for (j in 0..(alphabet.size-1)){
                if (textChangedCode[i] == j) {
                    print (alphabet[j])
                }
            }
        } 
    } else {
        println("ошибка")
    }

    println()
}

//сделать не чувствительным к регистру +
//разобраться с отступами когда делаю шифровку