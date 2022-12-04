class JobAllocation(var startId: Number, var endId: Number) {

    fun asList(): List<Int> {
        return IntRange(startId.toInt(), endId.toInt()).toList()
    }
}