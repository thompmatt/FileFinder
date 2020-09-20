# File Finder
A utility app that searches your system for files using their relative path name. Implemented using File I/O and iterators. The program has three types of iterators: ShallowFileIterator, DeepFileIterator, and FilteredFileIterator.

## Shallow File Iterator
The Shallow File Iterator simply searches and retrieves all the files in a specified directory.

## Deep File Iterator
The Deep File Iterator has the same properties of a Shallow File Iterator, but will search items in directories within the specified directory. (If the specified folder has two folders, all the items within the two folders will also be retrieved)

## Filtered File Iterator
As the name implies, the Filtered File Iterator retrieves all the files in a specified directory. Has the same properties as a Deep File Iterator.

This was a project for the CS 300: Programming II course at the University of Wisconsin-Madison, taught by Prof. Laura Legault.
