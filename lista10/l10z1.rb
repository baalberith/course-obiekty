class Kolekcja
    def initialize(a)
        @arr = a
    end
    
    def length
        @arr.length
    end
    
    def swap(i, j)
        @arr[i], @arr[j] = @arr[j], @arr[i]
    end
    
    def get(i)
        @arr[i]
    end
    
    def to_s
        str = ""
        for n in @arr
            str += n.to_s + " "
        end
        str
    end
end

class Sortowanie
    def sort1(kol)
        quick_sort(kol, 0, kol.length-1)
    end
    
    def quick_sort(kol, p, r)
        if p < r then
            q = partition(kol, p, r)
            quick_sort(kol, p, q-1)
            quick_sort(kol, q+1, r)
        end
    end
    
    def partition(kol, p, r)
        pivot = kol.get(r)
        i = p - 1
        
        p.upto(r-1) do |j|
            if kol.get(j) <= pivot
                i = i + 1
                kol.swap(i, j)
            end
        end
        
        kol.swap(i+1, r)
        return i + 1
    end

    def sort2(kol)
        selection_sort(kol, kol.length-1)
    end
    
    def selection_sort(kol, n)
        for j in 0..n-1
            m = j
            for i in j+1..n
                if kol.get(i) < kol.get(m)
                    m = i
                end
            end
            kol.swap(j, m)
        end
    end
    
end

arr = 30.times.map{ rand(20) } 
kolekcja1 = Kolekcja.new(arr.dup)
kolekcja2 = Kolekcja.new(arr.dup)

puts "kolekcja przed sortowaniem: ", kolekcja1

sortowanie = Sortowanie.new

sortowanie.sort1(kolekcja1)
puts "kolekcja po sortowaniu 1: ", kolekcja1

sortowanie.sort2(kolekcja2)
puts "kolekcja po sortowaniu 2: ", kolekcja2
