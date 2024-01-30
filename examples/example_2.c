#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Struct containing student info to be read.
 */
typedef struct _Student
{
    char name[100];
    char student_id[6];
    int grade;
} student;

/**
 * Opens a CSV file of students details and reads the provided number of rows
 */
int main(int argc, char *argv[])
{
    if (argc != 3)
    {
        printf("Usage: %s <filename> <number of students>\n", argv[0]);
        return 0;
    }

    FILE *input = fopen(argv[1], "r");
    if (!input)
    {
        printf("Error: Bad filename\n");
        return 1;
    }

    // remember: arguments are ALWAYS strings
    int num = atoi(argv[2]);
    if (num < 1)
    {
        printf("Error: Invalid number of students\n");
        return 2;
    }

    student *studentData = malloc(num * sizeof(student));

    char line[200];
    char *section;

    for (int i = 0; i < num; i++)
    {
        fgets(line, 200, input);

        if (feof(input))
        {
            printf("Error: too few lines in file\n");
            return 3;
        }

        section = strtok(line, ",");
        if (section != NULL) {
            strncpy(studentData[i].name, section, sizeof(studentData[i].name) - 1);
            studentData[i].name[sizeof(studentData[i].name) - 1] = '\0';
        }

        section = strtok(NULL, ",");
        if (section != NULL) {
            strncpy(studentData[i].student_id, section, sizeof(studentData[i].student_id) - 1);
            studentData[i].student_id[sizeof(studentData[i].student_id) - 1] = '\0';
        }

        section = strtok(NULL, ",");
        if (section != NULL) {
            studentData[i].grade = atoi(section);
        }
    }

    for (int i=0; i<num; i++){
        printf("%s\t%s\t%d\n", studentData[i].name, studentData[i].student_id, studentData[i].grade);
    }
}
