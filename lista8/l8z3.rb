class Jawna
    def initialize(n)
        @napis = n
    end
    
    def zaszyfruj(k)
        z_napis = ""
        @napis.each_char do |s| 
            z_napis += (s[0] + k).chr 
        end
        return Zaszyfrowana.new(z_napis)
    end
    
    def to_s
        @napis
    end
end

class Zaszyfrowana
    def initialize(n)
        @napis = n
    end
    
    def odszyfruj(k)
        j_napis = ""
        @napis.each_char do |s| 
            j_napis += (s[0] - k).chr 
        end
        return Jawna.new(j_napis)
    end
    
    def to_s
        @napis
    end
end

jawna = Jawna.new("Ala")
puts jawna
zaszyfrowana = jawna.zaszyfruj(3)
puts zaszyfrowana
puts zaszyfrowana.odszyfruj(3)
