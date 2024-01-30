# Using Command Line Arguments

Using command line arguments in C is very easy - you need to change one line:

```c
int main()

// becomes...

int main(int argc, char *argv[])
```

This captures the arguments entered on the command line when the program is run, and allows you to access them. `argc` is the argument count, while `argv` is an array of strings which holds the arguments. `argv[0]` is always the executable name, which is also captured. All arguments are stored as strings, so you might need to convert them!

## The Examples

There are three examples included showing simple programs which use command line arguments to record user inputs.

You don't need to understand all the code (particularly the string handling in example_2.c) but make sure that you have had a look!

To run these, you need to compile them - if you have forgotten:

```bash
gcc <filename> -o <objectname>

# for example:
gcc example_1.c -o ex1
```

## Your task

Inside the tasks/ folder, you will find task.c.

This program asks the user for 2 integers, adds them together, and prints the result. At the moment, it uses a console interface.

- Adapt this program to use command line arguments
- Add appropriate validation 
- test your program to make sure it works.

This should take around 20-30 minutes.
