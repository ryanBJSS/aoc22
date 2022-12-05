using System;
using System.Collections.Generic;
using System.Linq;

namespace day5
{
    public class Ship
    {

        private readonly Dictionary<int, CrateStack> _crateStacks;

        private Ship(Dictionary<int, CrateStack> crateStacks, List<string> input)
        {
            _crateStacks = crateStacks;
            
            // Init crates
            input.ForEach(line =>
            {

                var lineOfCrates = new List<string>();

                for (int i = 0; i < line.Length; i += 3)
                {
                    lineOfCrates.Add(line.Substring(i, 3));
                    if (line.Length > i + 1)
                    {
                        line = line.Remove(i + 1, 1);
                    }

                }
                
                for (int i = 0; i < lineOfCrates.Count; i++)
                {
                    if (!lineOfCrates[i].Equals("   "))
                    {
                        crateStacks[i+1].AddInitialState(lineOfCrates[i]);
                    }
                }
            });

        }
        
        public void CrateMover9000(Instruction instruction)
        {
            Enumerable.Range(1, instruction.NumberOfCrates).ToList().ForEach(movement =>
            {

                var toMove = _crateStacks[instruction.FromStackNumber].Pop();
                _crateStacks[instruction.ToStackNumber].push(toMove);
            } );
        }
        
        public void CrateMover9001(Instruction instruction)
        {
            var stack = new CrateStack();
            Enumerable.Range(1, instruction.NumberOfCrates).ToList().ForEach(movement =>
            {

                var toMove = _crateStacks[instruction.FromStackNumber].Pop();
                stack.push(toMove);
            } );
            
            Enumerable.Range(1, instruction.NumberOfCrates).ToList().ForEach(movement =>
            {

                var toMove = stack.Pop();
                _crateStacks[instruction.ToStackNumber].push(toMove);
            } );
        }


        public static Ship FromInputLines(List<string> input)
        {
            return new Ship(InitialiseCrates(input), input);
        }

        public string TopCrates()
        {
            string output = "";
            foreach (var keyValuePair in _crateStacks)
            {
                output += keyValuePair.Value.Top();
            }

            return output;
        }

        private static Dictionary<int, CrateStack> InitialiseCrates(IList<string> input)
        {
            var numberOfStacks = int.Parse(input.Last().Trim().Last().ToString());
            input.RemoveAt(input.Count - 1);
            var stacks = new Dictionary<int, CrateStack>();
            Enumerable.Range(1, numberOfStacks).ToList().ForEach( number => stacks[number] = new CrateStack());
            return stacks;
        }
        
        
    }
}