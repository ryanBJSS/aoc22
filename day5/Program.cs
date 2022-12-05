using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;

namespace day5
{
    class Program
    {
        static void Main(string[] args)
        {
            var inputs = File.ReadAllText("/Users/ryan.bibby/projects/aoc/day5/input.txt").Split("\n\n").ToList();
            
            var instructionLines = inputs[1].Split("\n").ToList();
            var instructions = instructionLines.Select(Instruction.FromInputLine).ToList();

            var crateSetup = inputs[0].Split("\n").ToList();

            
            var ship = Ship.FromInputLines(crateSetup);
            instructions.ForEach(instruction => ship.CrateMover9000(instruction));
            
            var shipAlt = Ship.FromInputLines(inputs[0].Split("\n").ToList());
            instructions.ForEach(instruction => shipAlt.CrateMover9001(instruction));
            
            Console.WriteLine(ship.TopCrates());
            Console.WriteLine(shipAlt.TopCrates());


            // MQSHJMWNH
            // LLWJRBHVZ   
        }
    }
}