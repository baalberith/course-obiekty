class Fixnum
    def prime?
        return false if self < 2
        for d in 2...self
            return false if self % d == 0 
        end
        return true
    end
    
    def ack(m)
        return m + 1 if self == 0
        return (self-1).ack(1) if m == 0
        return (self-1).ack(self.ack(m-1))
    end
    
    def perfect?
        sum = 0
        for d in 1...self
            if self % d == 0 then
                sum += d
            end
        end
        return sum == self
    end
    
    Words = %w{ zero jeden dwa trzy cztery piec szesc siedem osiem dziewiec }
    
    def toWord
        return "" if self == 0
        q = self / 10
        r = self % 10
        return q.toWord + Words[r] + " "
    end
end

puts "prime numbers 1..10: "
for n in 1..10
    puts n if n.prime?
end

puts "ackermann(2, 1): "
puts 2.ack(1)

puts "perfect numbers 1..30: "
for n in 1..30
    puts n if n.perfect?
end

puts "1230321 to word: "
puts 1230321.toWord
