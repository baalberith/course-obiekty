class Proc
    def wartosc(x)
        self.call(x)
    end
    
    def zerowe(a, b, e)
        x = (a + b) / 2.0
        wx = wartosc(x)
        if wx.abs <= e 
            x
        else
            wa = wartosc(a)
            wb = wartosc(b)
            if wa * wx < 0 
                zerowe(a, x, e)
            elsif wb * wx < 0 
                zerowe(x, b, e)
            else 
                nil
            end
        end
    end
    
    def pole(a, b)
        n = 100
        dx = (b - a) / n.to_f
        p = wartosc(a) / 2 + wartosc(a + n * dx) / 2
        for i in 1...n
            p += wartosc(a + i * dx)
        end
        p *= dx
    end
    
    def pochodna(x)
        h = 0.0001
        1 / h * (wartosc(x + h) - wartosc(x))
    end
    
    def rysuj(a, b, nazwa)
        file = File.open(nazwa, "w")
        file.puts "%!PS"
        
        x = 320
        y = 180
        
        file.puts "%%BoundingBox: 0 0 " + x.to_s + " " + y.to_s
        file.puts "%%DocumentPaperSizes: b3"
        
        file.puts "newpath"
        
        dx = (b - a) / x.to_f
        h = y / 2
        
        file.puts "0 " + (wartosc(a) + h).to_s + " moveto"
        for i in 1..x
            file.puts i.to_s + " " + (wartosc(a + i * dx) + h).to_s + " lineto"
        end
        
        file.puts "stroke"
        file.puts "showpage"
        
        file.close
    end
end

puts "f = x^2 * sin(x)"
f = proc { |x| x * x * Math.sin(x) }

puts "wartosc f w punkcie 1: ", f.wartosc(1)
puts "miejsce zerowe f na przedziale [-2, 3]: ", f.zerowe(-2, 3, 0.0001)

f.rysuj(-20, 20, "wykres_f.ps")


puts "g = -x^2 + x + 1"
g = proc { |x| - x * x + x + 1 }

puts "pole pod wykresem g na przedziale [-1, 1]: ", g.pole(-1, 1)
puts "pochodna g w punkcie 2: ", g.pochodna(2)

g.rysuj(-20, 20, "wykres_g.ps")

