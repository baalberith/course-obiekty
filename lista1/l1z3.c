#include <stdlib.h>
#include <stdio.h>

#include "complex.h"

#define print(x,y,z) printf("%.2f+%.2fi + %.2f+%.2fi = %.2f+%.2fi\n", x.re, x.im, y.re, y.im, z.re, z.im)

int main() {
    complex x, y, z;
    x.re = 1.5;
    x.im = 2.5;
    y.re = 3.5;
    y.im = 0.5;
    
    z = plus(x, y);
    print(x, y, z);
    z = minus(x, y);
    print(x, y, z);
    z = mul(x, y);
    print(x, y, z);
    z = divide(x, y);
    print(x, y, z);
    
    plus2(x, y, &z);
    print(x, y, z);
    minus2(x, y, &z);
    print(x, y, z);
    mul2(x, y, &z);
    print(x, y, z);
    divide2(x, y, &z);
    print(x, y, z);
    
    return 0;
}
