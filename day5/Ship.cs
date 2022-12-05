using System;
using System.Collections.Generic;
using System.Linq;

namespace day5
{
    public class Ship
    {
        private readonly Dictionary<int, CrateStack> _crateStacks;
        private const int NumberOfCharsRepresentingCrate = 3;

        private Ship(Dictionary<int, CrateStack> crateStacks, List<string> input)
        {
            _crateStacks = crateStacks;
            InitialiseCrates(crateStacks, input);
        }

        public static Ship FromInputLines(List<string> input)
        {
            return new Ship(InitialiseCrates(input), input);
        }

        public void CrateMover9000(Instruction instruction)
        {
            Enumerable.Range(1, instruction.NumberOfCrates).ToList().ForEach(movement =>
            {
                var toMove = _crateStacks[instruction.FromStackNumber].Pop();
                _crateStacks[instruction.ToStackNumber].push(toMove);
            });
        }

        public void CrateMover9001(Instruction instruction)
        {
            var stack = new CrateStack();
            Enumerable.Range(1, instruction.NumberOfCrates).ToList().ForEach(movement =>
            {
                var toMove = _crateStacks[instruction.FromStackNumber].Pop();
                stack.push(toMove);
            });

            Enumerable.Range(1, instruction.NumberOfCrates).ToList().ForEach(movement =>
            {
                var toMove = stack.Pop();
                _crateStacks[instruction.ToStackNumber].push(toMove);
            });
        }

        public string TopCrates()
        {
            return string.Join("",_crateStacks.Select(pair => pair.Value.Top()).ToList());
        }

        private static void InitialiseCrates(Dictionary<int, CrateStack> crateStacks, List<string> input)
        {
            input.ForEach(line =>
            {
                for (var i = 0; i < line.Length; i += NumberOfCharsRepresentingCrate)
                {
                    var crate = line.Substring(i, NumberOfCharsRepresentingCrate);
                    if (!crate.Equals("   "))
                    {
                        var crateNumber = (i + NumberOfCharsRepresentingCrate) / NumberOfCharsRepresentingCrate;
                        crateStacks[crateNumber].AddInitialState(crate);
                    }

                    if (line.Length > i + 1)
                    {
                        line = line.Remove(i + 1, 1);
                    }
                }
            });
        }
        private static Dictionary<int, CrateStack> InitialiseCrates(IList<string> input)
        {
            var numberOfStacks = int.Parse(input.Last().Trim().Last().ToString());
            input.RemoveAt(input.Count - 1);
            var stacks = new Dictionary<int, CrateStack>();
            Enumerable.Range(1, numberOfStacks).ToList().ForEach(number => stacks[number] = new CrateStack());
            return stacks;
        }
    }
}