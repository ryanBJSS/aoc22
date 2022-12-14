input = File.read("input.txt")

input = input.split("\n\n")

map = {}

def mixed_type_converter(item)
  if(item.is_a? Numeric) 
    return [item]
  else
    return item
  end
end

def comparision(left, right)
  # puts "Comparing #{left} with #{right}"
  if left.is_a?(Numeric) && right.is_a?(Numeric)
    return left <=> right
  elsif left.nil?
    return -1
  elsif right.nil?
    return 1
  elsif left.is_a?(Array) && right.is_a?(Array)
    left.each_with_index do |l, index| 
      result = comparision(l, right[index]) 
      return result if result != 0
    end
    return left.size <=> right.size
  else
    return comparision(mixed_type_converter(left), mixed_type_converter(right))
  end
end


input.each_with_index do |pair, pair_number|

    left = eval(pair.split("\n")[0])
    right = eval(pair.split("\n")[1])

    result = comparision(left, right)
    
    if result != -1 && result != 1
      puts "****"
      puts raise
      puts "****"
    end
    map[pair_number + 1] = result
end

pp map.select { |k, v| v == -1  }.keys.sum

input = File.read("input.txt").split("\n")
input = input.reject(&:empty?)
input = input.map { |line| eval line } 
input << [[2]]
input << [[6]]

input = input.sort do |a, b| 
  comparision(a, b)
end
puts (input.find_index([[2]]) + 1) * (input.find_index([[6]]) + 1)

