interface Person {
    val name: String
    val age: Int
    val phoneNumber: String?

    fun withAgeIncrementedBy(ageIncrement: Int): Person
}

class PersonImpl(override val name: String, override val age: Int
                 , override val phoneNumber: String?) : Person {
    override fun withAgeIncrementedBy(ageIncrement: Int): Person {
        return PersonImpl(name, age + ageIncrement, phoneNumber)
    }

    override fun equals(other: Any?): Boolean {
        val person = other as Person
        return if (phoneNumber == null && person.phoneNumber == null) {
            person.name == name && person.age == age
        }
        else {
            phoneNumber != null && person.phoneNumber != null &&
                    person.name == name && person.age == age

        }
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        result = 31 * result + (phoneNumber?.hashCode() ?: 0)
        return result
    }
}

class CreditCard(val number: String) {
    override fun toString(): String ="Numero: $number"
}

fun createPerson(name: String, age: Int, phoneNumber: String?): Person = PersonImpl(name, age, phoneNumber)



fun main(args: Array<String>) {
    val map = mutableMapOf<Person, CreditCard>()
    val person1 = createPerson("Mary", 20, null)
    map[person1] = CreditCard("10101010")

    println(map[person1])

    println(map[person1.withAgeIncrementedBy(10)])
}