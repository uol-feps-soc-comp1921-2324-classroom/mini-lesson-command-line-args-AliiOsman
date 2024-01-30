#include <stdio.h>

/**
 * Prints out a simple greeting to the 2 names entered
 */
int main(int argc, char *argv[])
{
    if (argc != 3)
    {
        printf("Usage: %s <name1> <name2>\n", argv[0]);
        return 0;
    }

    printf("Hello, %s and %s!\n", argv[1], argv[2]);

    return 0;
}
