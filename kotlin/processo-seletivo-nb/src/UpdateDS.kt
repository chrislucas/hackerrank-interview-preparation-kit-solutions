data class Employee(val name: String
                    , val nick: String
                    , val birthDate: String
                    , val phoneNumber: String
                    , val email: String?) {
    override fun toString(): String {
        return "$name, $nick, ${email ?: "null"}"
    }

    override fun equals(other: Any?): Boolean {
        val that = other as Employee
        return that.name == name
                && that.name == name
                && that.birthDate == birthDate
                && that.phoneNumber == phoneNumber
                && (that.email?.equals(email ?: "") ?: false)
    }

    fun createAnEmail() = "$nick@nubank.com.br"
}



fun setupNewHires(employees: List<Employee>): List<Employee> {
    val newList = mutableListOf<Employee>()
    employees.forEach {
        newList.add(Employee(it.name, it.nick, it.birthDate, it.phoneNumber, it.createAnEmail()))
    }
    return  newList.toSet().toList()
}

fun main(args: Array<String>) {
    val list = listOf(
            Employee("c", "nomequalquer", "10", "1", null)
            ,Employee("b", "teste123", "10", "1", null)
            ,Employee("b", "cansado", "10", "1", null)
            ,Employee("b", "cansado", "10", "1", null)
            ,Employee("b", "agora.vai", "10", "1", null)
            ,Employee("b", "agora.vai2", "10", "1", null)
            ,Employee("b", "agora.vai.3", "10", "1", null)
            ,Employee("b", "agora.vai.3", "10", "1", null)
            ,Employee("b", "teste123", "10", "1", null)
            ,Employee("b", "master.of.the.universe", "10", "1", null)
    )

    setupNewHires(list).forEach { println(it) }
}