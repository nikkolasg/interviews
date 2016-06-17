def methA &block
    puts "Method A : entering ..."
    methB &block
    puts "Method B : leaving ..."
end

def methB &block
    puts "Method B : entering ..."
    puts " block type : " + block.type.name if block
    yield if block
    puts "Method B : leaving ..."
end

puts "Calling without block :"
methA
puts "Calling with block : "
methA { puts "This is the block" }
puts "wow"
