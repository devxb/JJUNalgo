import java.io.BufferedReader
import java.io.InputStreamReader

lateinit var paramName: String

fun main() {
    read()
    if (paramName.first() == '_'
        || paramName.last() == '_'
        || paramName.first().isUpperCase()
        || paramName.isContinueUnderbar()
        || (paramName.contains("_") && paramName.containUpperCase())
    ) {
        println("Error!")
    } else if (paramName.contains("_")) {
        val newParamName = StringBuilder()
        for (char in paramName.withIndex()) {
            if (char.value == '_') {
                continue
            }
            if (char.index > 0 && paramName[char.index - 1] == '_') {
                newParamName.append(char.value.uppercase())
                continue
            }
            newParamName.append(char.value)
        }
        println(newParamName)
    } else {
        val newParamName = StringBuilder()
        for (char in paramName.withIndex()) {
            if (char.value.isUpperCase()) {
                newParamName.append("_").append(char.value.lowercase())
                continue
            }
            newParamName.append(char.value)
        }
        println(newParamName)
    }
}

private fun String.isContinueUnderbar(): Boolean {
    for (withIndex in this.withIndex()) {
        if (withIndex.index > 0 && this[withIndex.index] == '_' && this[withIndex.index - 1] == '_') {
            return true
        }
    }
    return false
}

private fun read() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            paramName = reader.readLine()
        }
}

private fun String.containUpperCase(): Boolean {
    this.forEach {
        if (it.isUpperCase()) {
            return true
        }
    }
    return false
}
