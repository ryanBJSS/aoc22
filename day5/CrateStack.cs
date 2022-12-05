using System.Collections.Generic;

namespace day5
{
    public class CrateStack
    {
        private readonly List<string> _contents = new();


        public void push(string crate)
        {
            _contents.Add(crate);
        }

        public void AddInitialState(string crate)
        {
            _contents.Insert(0, crate);
        }
        
        public string Pop()
        {
            var index = _contents.Count - 1;
            var removed = _contents[index];
            _contents.RemoveAt(index);
            return removed;
        }
        
        public char Top()
        {
            var index = _contents.Count - 1;
            return _contents[index].ToCharArray()[1];
        }
    }
}