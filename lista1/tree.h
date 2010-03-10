#ifndef _tree_h_
#define _tree_h_

typedef char str;

typedef struct node_t {
    str s;
    struct node_t *l, *r;
} *node;

typedef struct tree_t {
    node root;
} *tree;

tree create();
void insert(tree t, str s);
int search(tree t, str s);

#endif
