module Tree

    class BinaryTreeNode

        attr_accessor :value,:left,:right

        def initialize value
            self.value = value
        end

        def to_s
            value
        end
    end

end
