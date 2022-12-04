class ElfOverlapper(var line: String) {
    private val elfOne : JobAllocation;
    private val elfTwo: JobAllocation

    init {
        val elfPairs = line.split(",")
        elfOne = JobAllocation(elfPairs[0].split("-")[0].toInt(), elfPairs[0].split("-")[1].toInt())
        elfTwo = JobAllocation(elfPairs[1].split("-")[0].toInt(), elfPairs[1].split("-")[1].toInt())
    }

    fun isThereOverlap() : Boolean {
        val elfOneList = elfOne.asList();
        val elfTwoList = elfTwo.asList()
        return elfOneList.containsAll(elfTwoList) || elfTwoList.containsAll(elfOneList)
    }

    fun isThereOverlapAtAll() : Boolean {
        val elfOneList = elfOne.asList();
        val elfTwoList = elfTwo.asList();
        return elfOneList.intersect(elfTwoList).isNotEmpty();
    }
}