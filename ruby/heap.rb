class Array

    def swap i,j
        self[i],self[j] = self[j],self[i]
    end
end
## MAX HEAP
class ArrayHeap

    def initialize arr = nil
        @array = arr || [nil]
    end

    def length
        return @array.length - 1
    end
    alias :size :length

    def to_s
        return @array[1..-1].join(" - ")
    end

    def add value
        @array << value
        ArrayHeap.percolate_up @array,length
    end

    def max
        @array[1]
    end

    def delete_max
        @array[1] = @array[length]
        ArrayHeap.percolate_down @array,1
    end

    def self.percolate_up array, index
        while index/2 > 0 && array[index/2] < array[index]
            array[index/2],array[index] = array[index],array[index/2]
            index /= 2
            yield if block_given?
        end
    end

    #3 more beautiful versio at http://courses.csail.mit.edu/6.006/fall10/handouts/recitation10-8.pdf
    def self.percolate_down array,index,limit,&block
        n = limit
        left = index * 2
        right = left + 1
        largest = index
        largest = left if left <= n && array[largest] < array[left]  ## TAKE CARE 
        largest = right if right <= n && array[largest] < array[right] ## OF TAKING THE LARGEST  and not index
        unless largest == index
            array.swap(index,largest)
            yield array if block
            percolate_down array,largest,limit,&block
        end
    end

    ## default : swap elements with the first value found greater
    ## ie. the left value. if right value is also greater it will stay at the end
    def self.percolate_down_old array, index,length = nil,&block
        n = length || array.length # not -1 because we use strict < and >
        while index < n
            if index*2 < n &&  array[index] < array[index*2] 
                array.swap(index,index*2)
                index = index * 2
            elsif (index*2 + 1) < n && array[index] < array[index*2 + 1]
                array.swap(index,index*2 +1)
                index = index*2 + 1
            else
                break
            end
        end
        yield array if block
    end

    #http://www.brpreiss.com/books/opus4/html/page506.html
    def self.heapify arr,heap: false,&block
        arr = arr.first ? [nil] + arr : arr
        ((arr.length - 1)/2).downto(1) do |i|
            percolate_down arr,i,(arr.length - 1),&block
        end
        return (heap ? ArrayHeap.new(arr) : arr)
    end

    def self.heapsort array
        heapt = heapify array
        length = heapt.length - 1
        length.downto(2) do |i|
            heapt.swap(1,i)
            percolate_down heapt,1,i-1
            yield heapt if block_given?
        end
        return heapt
    end
end

puts "[+] Heap (max) in ruby"
heap = ArrayHeap.new
8.times do |i| 
    heap.add i 
    puts "[+] Heap (bst) : " + heap.to_s
end
arr = [10,14,3,8,1,20,34]
arr2 = arr.dup
puts "[+] Array : #{arr} => heapify :" 
ArrayHeap.heapify(arr,heap: true) do |arr|
    puts "[+] " + arr.join( " - ")
end
puts "[+] Array to sort : #{arr2} "
ArrayHeap.heapsort(arr2) do |arr|
    puts "[+] " + arr.join(" - ")
end
