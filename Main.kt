package machine

import kotlin.system.exitProcess

enum class States {
    CHOOSING_ACTION, BUYING, TAKING, REMAINING, REFILLING, CHOOSING_COFFEE
}

enum class Ingredients(val water: Int, val milk: Int, val beans: Int, val price: Int) {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6)
}

class CoffeeMachine(var state: States = States.CHOOSING_ACTION, var water: Int = 400, var milk: Int = 540, var beans: Int = 120, var cups: Int = 9, var money: Int = 550) {
    fun readInput() {
        when (state) {
            States.CHOOSING_ACTION -> chooseAction()
            States.BUYING -> buy()
            States.TAKING -> take()
            States.REMAINING -> printStatus()
            States.REFILLING -> fill()
            States.CHOOSING_COFFEE -> chooseCoffee()
        }
    }
    fun chooseAction() {
        print("Write action (buy, fill, take, remaining, exit): ")
        val userInput = readln()
        when (userInput) {
            "buy" -> {
                state = States.BUYING
                readInput()
            }
            "fill" -> {
                state = States.REFILLING
                readInput()
            }
            "take" -> {
                state = States.TAKING
                readInput()
            }
            "remaining" -> {
                state = States.REMAINING
                readInput()
            }
            "exit" -> exitProcess(0)
        }
    }
    fun buy() {
        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        state = States.CHOOSING_COFFEE
        readInput()
    }
    fun chooseCoffee() {
        when (readln()) {
            "1" -> {
                if (water - Ingredients.ESPRESSO.water <= 0) println("Sorry, not enough water!")
                else if (beans - Ingredients.ESPRESSO.beans <= 0) println("Sorry, not enough coffee beans!")
                else if (cups <= 0) println("Sorry, not enough cups!")
                else {
                    water -= Ingredients.ESPRESSO.water
                    beans -= Ingredients.ESPRESSO.beans
                    cups--
                    money += Ingredients.ESPRESSO.price
                    println("I have enough resources, making you a coffee!")
                }
            }
            "2" -> {
                if (water - Ingredients.LATTE.water <= 0) println("Sorry, not enough water!")
                else if (milk - Ingredients.LATTE.milk <= 0) println("Sorry, not enough milk!")
                else if (beans - Ingredients.LATTE.beans <= 0) println("Sorry, not enough coffee beans!")
                else if (cups <= 0) println("Sorry, not enough cups!")
                else {
                    water -= Ingredients.LATTE.water
                    milk -= Ingredients.LATTE.milk
                    beans -= Ingredients.LATTE.beans
                    cups--
                    money += Ingredients.LATTE.price
                    println("I have enough resources, making you a coffee!")
                }
            }
            "3" -> {
                if (water - Ingredients.CAPPUCCINO.water <= 0) println("Sorry, not enough water!")
                else if (milk - Ingredients.CAPPUCCINO.milk <= 0) println("Sorry, not enough milk!")
                else if (beans - Ingredients.CAPPUCCINO.beans <= 0) println("Sorry, not enough coffee beans!")
                else if (cups <= 0) println("Sorry, not enough cups!")
                else {
                    water -= Ingredients.CAPPUCCINO.water
                    milk -= Ingredients.CAPPUCCINO.milk
                    beans -= Ingredients.CAPPUCCINO.beans
                    cups--
                    money += Ingredients.CAPPUCCINO.price
                    println("I have enough resources, making you a coffee!")
                }
            }
        }
        state = States.CHOOSING_ACTION
        readInput()
    }
    fun take() {
        println("I gave you $$money")
        money = 0
        state = States.CHOOSING_ACTION
        readInput()
    }
    fun printStatus() {
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$beans g of coffee beans")
        println("$cups disposable cups")
        println("$$money of money")
        state = States.CHOOSING_ACTION
        readInput()
    }
    fun fill() {
        print("Write how many ml of water do you want to add: ")
        water += readln().toInt()
        print("Write how many ml of milk do you want to add: ")
        milk += readln().toInt()
        print("Write how many g of coffee beans do you want to add: ")
        beans += readln().toInt()
        print("Write how many disposable cups do you want to add: ")
        cups += readln().toInt()
        state = States.CHOOSING_ACTION
        readInput()
    }
}





fun main() {
    val machine = CoffeeMachine()
    machine.readInput()
}
