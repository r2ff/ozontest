
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.random.Random

data class TestData(val phones: ConcurrentLinkedQueue<Long> = ConcurrentLinkedQueue())

fun TestData.add() {
    phones.add(randPhone())
}

suspend fun generate(n: Int, td: TestData) = coroutineScope {
    repeat(n) {
        launch(Dispatchers.Default) {
            td.add()
        }
    }
}

fun randPhone(): Long {
    return 89000000000 + Random.nextInt(900000000)
}

fun main() = runBlocking {
    val td = TestData()
    generate(100, td)
    println(td.phones.size)
}