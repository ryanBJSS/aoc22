input = File.read("input.txt").chomp.chars

(0..input.size).each do |i|
  packet_marker = input[i..i+3]
  if packet_marker.uniq.size == 4
    puts i + 4 
    break
  end
end

(0..input.size).each do |i|
  message_marker = input[i..i+13]
  if message_marker.uniq.size == 14
    puts i + 14 
    break
  end
end

