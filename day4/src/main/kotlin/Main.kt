import java.io.File

fun main(args: Array<String>) {
    println("Overlaps are: ${File("input.txt").readLines().map { ElfOverlapper(it).isThereOverlap() }.filter { it }.size}")
    println("Overlaps are: ${File("input.txt").readLines().map { ElfOverlapper(it).isThereOverlapAtAll() }.filter { it }.size}")
}
