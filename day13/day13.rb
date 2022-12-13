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
  puts "Comparing #{left} with #{right} (number)"
  if left.is_a?(Numeric) && right.is_a?(Numeric)
    return left <=> right
  elsif left.nil?
    return -1
  elsif right.nil?
    return 1
  elsif left.is_a?(Array) && right.is_a?(Array)
    left.map.with_index do |l, index| 
      result = comparision(l, right[index]) 
      return result if result != 0
    end
    return left <=> right
  else
    comparision(mixed_type_converter(left), mixed_type_converter(right))
  end
end


input.each_with_index do |pair, pair_number|

    left = eval(pair.split("\n")[0])
    right = eval(pair.split("\n")[1])

    result = comparision(left, right)
    map[pair_number + 1] = result
end

pp map.select { |k, v| v == -1  }.keys.sum

