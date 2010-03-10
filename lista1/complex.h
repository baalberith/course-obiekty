#ifndef _complex_h_
#define _complex_h_

typedef struct complex {
    double re, im;
} complex;

complex plus(complex x, complex y);
complex minus(complex x, complex y);
complex mul(complex x, complex y);
complex divide(complex x, complex y);

void plus2(complex x, complex y, complex *z);
void minus2(complex x, complex y, complex *z);
void mul2(complex x, complex y, complex *z);
void divide2(complex x, complex y, complex *z);

#endif
