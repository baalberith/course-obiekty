#include "complex.h"

complex plus(complex x, complex y) {
    complex z;
    z.re = x.re + y.re;
    z.im = x.im + y.im;
    
    return z;
}

complex minus(complex x, complex y) {
    complex z;
    z.re = x.re - y.re;
    z.im = x.im - y.im;
    
    return z;
}

complex mul(complex x, complex y) {
    complex z;
    z.re = x.re*y.re - x.im*y.im;
    z.im = x.im*y.re + x.re*y.im;
    
    return z;
}

complex divide(complex x, complex y) {
    complex z;
    z.re = (x.re*y.re + x.im*y.im) / (y.re * y.re + y.im * y.im);
    z.im = (x.im*y.re - x.re*y.im) / (y.re * y.re + y.im * y.im);
    
    return z;
}

void plus2(complex x, complex y, complex *z) {
    *z = plus(x, y);
}

void minus2(complex x, complex y, complex *z) {
    *z = minus(x, y);
}

void mul2(complex x, complex y, complex *z) {
    *z = mul(x, y);
}

void divide2(complex x, complex y, complex *z) {
    *z = divide(x, y);
}
