open System
open System.Collections.Generic
open System.IO

let text = File.ReadAllText("./input1.txt")

let decodeForStarOne = Dictionary<string, string>()
decodeForStarOne.Add("A", "Rock")
decodeForStarOne.Add("B","Paper")
decodeForStarOne.Add("C","Scissors")
decodeForStarOne.Add("X", "Rock")
decodeForStarOne.Add("Y","Paper")
decodeForStarOne.Add("Z","Scissors")

let decodeForStarTwo = Dictionary<string, string>()
decodeForStarTwo.Add("X", "LOSE")
decodeForStarTwo.Add("Y","DRAW")
decodeForStarTwo.Add("Z","WIN")

let scores = Dictionary<string, int32>()
scores.Add("Rock", 1)
scores.Add("Paper", 2)
scores.Add("Scissors",3)

let results = Dictionary<string, int32>()
results.Add("WIN", 6)
results.Add("LOSE", 0)
results.Add("DRAW", 3)

let rules = Map [
                   ("Rock",
                       Map [
                           ("Rock", "DRAW");
                           ("Paper", "WIN");
                           ("Scissors", "LOSE");
                           ]);
                   ("Paper",
                       Map [
                           ("Rock", "LOSE");
                           ("Paper", "DRAW");
                           ("Scissors", "WIN");
                           ]);
                   ("Scissors",
                       Map [
                           ("Rock", "WIN");
                           ("Paper", "LOSE");
                           ("Scissors", "DRAW");
                           ]);
                ]

let handFromResult = Map [
                   ("Rock",
                        Map [
                           ("DRAW", "Rock");
                           ("WIN", "Paper");
                           ("LOSE", "Scissors");
                           ]);
                   ("Paper",
                       Map [
                           ("LOSE", "Rock");
                           ("DRAW", "Paper");
                           ("WIN", "Scissors");
                           ]);
                   ("Scissors",
                       Map [
                           ("WIN", "Rock");
                           ("LOSE", "Paper");
                           ("DRAW", "Scissors");
                           ]);
                ]

let handToScore(hand: string) =
    scores.Item(hand)
    
let handsToResult(them: string, us: string) =
    rules.[them].[us]
    
let resultToScore(result: string) =
    results.[result]
    
let resultToHand(them: string, us: string) =
   handFromResult.[them].[us]

let roundToHand(round: string, who: string) =
    match who with
        | "them" -> round.Split(" ").[0]
        | "us" -> round.Split(" ").[1]
let parseScore (round: string) =
   
   resultToScore(handsToResult(decodeForStarOne.[roundToHand(round, "them")], decodeForStarOne.[roundToHand(round, "us")])) + handToScore(decodeForStarOne.[roundToHand(round, "us")])
   
let parseScore1 (round: string) =
   
   let myHand = resultToHand(decodeForStarOne.[roundToHand(round, "them")], decodeForStarTwo.[roundToHand(round, "us")])
   
   resultToScore(handsToResult(decodeForStarOne.[roundToHand(round, "them")], myHand)) + handToScore(myHand)
   
let starOne(allRounds: string) =
    allRounds.Split("\n")
    |> Array.map parseScore
    |> Array.sum
    
let starTwo(allRounds: string) =
    allRounds.Split("\n")
    |> Array.map parseScore1
    |> Array.sum

Console.WriteLine(starOne(text))
Console.WriteLine(starTwo(text))