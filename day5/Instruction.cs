using System;
using System.Linq;
using System.Text.RegularExpressions;
using static System.Int32;

namespace day5
{
    public class Instruction
    {
        public int NumberOfCrates { get; }
        public int FromStackNumber{ get; }
        public int ToStackNumber{ get; }

        private Instruction(int numberOfCrates, int fromStackNumber, int toStackNumber)
        {
            NumberOfCrates = numberOfCrates;
            FromStackNumber = fromStackNumber;
            ToStackNumber = toStackNumber;
        }

        public static Instruction FromInputLine(string line)
        { 
            var rawTask = Regex.Split(line, @"move|from|to|\s").Where(s => !string.IsNullOrEmpty(s))
                .Select(Parse).ToList(); 
            return new Instruction(rawTask[0], rawTask[1], rawTask[2]);
        }

    }
}