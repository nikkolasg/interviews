#!/usr/bin/env ruby
module Tree

    module BinaryTree

        class Node

            attr_accessor :value,:left,:right

            def initialize value
                @value = value
                @left = nil
                @right = nil
            end

            def to_s
                @value
            end

            def add node
                if node.value <= @value
                    (@left = node; return) unless @left
                    @left.add node
                else
                    (@right = node; return) unless @right
                    @right.add node
                end
            end

            def bfs
                queue = [self]
                arr = []
                while !queue.empty?
                    n = queue.shift
                    if block_given?
                        yield n
                    else
                        arr << n
                    end
                    queue << n.left if n.left
                    queue << n.right if n.right
                end
                arr
            end

            def bst?
                def check_binary_node node,min,max
                    return false if min <= node.value && node.value > max
                    if node.left
                        maxLeft = [max,node.value].min
                        unless check_binary_node node.left,min,maxLeft
                            return false
                        end
                    end
                    if node.right
                        minRight = [min,node.value].max
                        unless check_binary_node node.right,minRight,max
                            return false
                        end
                    end
                    return true
                end

                max = 2**32 - 1
                min = -max - 1
                check_binary_node self,min,max
            end
            
            def to_linkedlist
                require_relative 'linked'
                arr = []
                queue = [[self,0]]
                while !queue.empty?
                    node,ind = queue.shift
                    if !arr[ind]
                        arr[ind] =  LinkedList::Node.new(node.value)
                    else
                        lnode = arr[ind]
                        lnode = lnode.next while lnode.next
                        lnode.next = LinkedList::Node.new(node.value)
                    end
                    queue << [node.left,ind+1] if node.left
                    queue << [node.right,ind+1] if node.right
                end
                arr
            end



        end        
    end

end

include Tree

def test_1
    n1 = BinaryTree::Node.new 5
    n2 = BinaryTree::Node.new 10
    n3 = BinaryTree::Node.new 1
    n4 = BinaryTree::Node.new 4
    n1.add n2
    n1.add n3
    n1.add n4
    n1.add BinaryTree::Node.new 8
    n1.add BinaryTree::Node.new 6
    puts "[+] Tree (bfs) : " + n1.bfs.map(&:to_s).join(" - ") 
    puts "[+] is bst? : #{n1.bst?}"
    n4.add BinaryTree::Node.new 11
    puts "[+] Tree (bfs) : " + n1.bfs.map(&:to_s).join(" - ")
    puts "[+] is bst? : #{n1.bst?}"
    arr = n1.to_linkedlist
    puts "[+] Tree to linkedlist : "
    node = nil
    arr.each do |lnode|
        node = lnode
        while node
            print node.to_s + " - "
            node = node.next
        end
        print "\n"
    end
end

puts "[+] Tree in ruby"
test_1
