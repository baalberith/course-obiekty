#include <stdlib.h>
#include <stdio.h>

#include "tree.h"
        
int main() {
    tree t = create();
    char c;
    
    while ((c = getchar()) != EOF) {
        if (c == 'i') {
            c = getchar();
            insert(t, c);
        }
        else if (c == 's') {
            c = getchar();
            int res = search(t, c);
            
            if (res == 1)
                printf("yes\n");
            else
                printf("no\n");
        }
    }
    
    return 0;
}
    