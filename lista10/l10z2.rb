class Kolekcja
    Node = Struct.new(:obj, :prev, :next)

    def initialize
        @first = @last = Node.new

        @first.next = @last
        @last.prev = @first
        
        @curr = @first
        @n = -1
        @size = 0
    end
    
    def add(obj)
        node = Node.new(obj)
        
        curr = @first.next

        while curr != @last and curr.obj < obj
            curr = curr.next
        end
        
        node.next = curr
        node.prev = curr.prev
        
        curr.prev.next = node
        curr.prev = node
        
        @size += 1
    end
    
    def nth(n)
        if n >= @size or n < 0
            raise "obiekt o podanym indeksie nie mieści się w kolekcji"
        else
            while n < @n
                @curr = @curr.prev
                @n -= 1
            end
            
            while n > @n
                @curr = @curr.next
                @n += 1
            end
            
            @curr.obj
        end
    end

    def size
        return @size
    end

    def to_s
        str = ""
        curr = @first.next
        while curr != @last
            str += curr.obj.to_s + " "
            curr = curr.next
        end
        str
    end
end

class Wyszukiwanie
    def search1(kol, obj)
        binary_search(kol, 0, kol.size-1, obj)
    end    
    
    def binary_search(kol, lower, upper, obj)
        if lower > upper
            raise "brak szukanego obiektu w kolekcji"
        else
            mid = (lower + upper) / 2
            mid_obj = kol.nth(mid)
  
            if (mid_obj == obj)
                mid
            elsif (obj < mid_obj)
                binary_search(kol, lower, mid-1, obj)
            else
                binary_search(kol, mid+1, upper, obj)
            end
        end
    end
    
    def search2(kol, obj)
        interpolation_search(kol, 0, kol.size-1, obj)
    end
    
    def interpolation_search(kol, low, high, obj)
        low_obj = kol.nth(low)
        high_obj = kol.nth(high)
        
        while low_obj <= obj and high_obj >= obj
            mid = low + ((obj - low_obj) * (high - low)) / (high_obj - low_obj)
            mid_obj = kol.nth(mid)
            
            if (mid_obj < obj)
                low = mid + 1
                low_obj = kol.nth(low)
            elsif mid_obj > obj
                high = mid - 1
                high_obj = kol.nth(high)
            else
                return mid
            end
        end
        
        if low_obj == obj
            return low
        else
            raise "brak szukanego obiektu w kolekcji"
        end
    end
 
end

kolekcja = Kolekcja.new
10.times { kolekcja.add(rand(10)) }

puts "kolekcja: ", kolekcja

wyszukiwanie = Wyszukiwanie.new

n = rand(10)
puts "wyszukiwanie " + n.to_s + ": "

puts wyszukiwanie.search1(kolekcja, n)
puts wyszukiwanie.search2(kolekcja, n)
