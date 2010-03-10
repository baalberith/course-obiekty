#include <stdlib.h>

#include "tree.h"

tree create() {
  tree t = (tree) malloc(sizeof(struct tree_t));
  t->root = NULL;
  
  return t;
}

node ins(node t, node n) {
    if (t == NULL)
        return n;
    else {
        if (n->s <= t->s)
            t->l = ins(t->l, n);
        else
            t->r = ins(t->r, n);
        
        return t;
    }
}

void insert(tree t, str s) {
    node n = (node) malloc(sizeof(struct node_t));
    n->s = s;
    n->l = n->r = NULL;
    
    t->root = ins(t->root, n);
}

int srh(node t, str s) {
    if (t == NULL)
        return 0;
    else {
        if (s == t->s)
            return 1;
        else if (s < t->s)
            return srh(t->l, s);
        else
            return srh(t->r, s);
    }
}

int search(tree t, str s) {
    return srh(t->root, s);
}
