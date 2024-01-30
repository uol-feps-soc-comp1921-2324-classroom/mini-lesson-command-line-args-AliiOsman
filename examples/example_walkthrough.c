#include <stdio.h>
#include <stdlib.h>

/**
 * argv[1] - name
 * argv[2] - age
 */
int main(int argc, char *argv[])
{

    if (argc != 3)
    {
        printf("Usage: wlk <name>\n");
        return 0;
    }

    int age = atoi(argv[2]);
    if (age < 1)
    {
        printf("Error: invalid age\n");
        return 1;
    }

    printf("Hello, %s! You are %d years old :)\n", argv[1], age);

    return 0;
}
