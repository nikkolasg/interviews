def first_nonrepeated string
    h = Hash.new { |h,k| h[k] = [] }
    string.split("").each_with_index  { |c,i| h[c] << i }
    fchar,index = nil,string.size
    h.each do |char,indexes|
        next if indexes.size != 1
        fchar,index = char,indexes.first if indexes.first < index
    end
    return fchar
end

puts "[+] String problems "
str = "tweeter"
puts "[+] first non repeated char in #{str} : #{first_nonrepeated(str)}"


