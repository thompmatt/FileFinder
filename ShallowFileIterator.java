//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Shallow File Iterator
// Files: ShallowFileIterator.java, DeepFileIterator.java,
// FilteredFileIterator.java, P07Tester.java
// Course: CS 300, Spring 2020
//
// Author: Matt Thompson
// Email: mathompson23@wisc.edu
// Lecturer's Name: Hobbes LeGault
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understood the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: NONE
// Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class creates a ShallowFileIterator, implementing Iterator<File>, which goes through a
 * specified directory and returns the files and sub-directories within that directory, without
 * going into the sub-directories. Contains the following methods:
 * 
 * - ShallowFileIterator constructor
 *
 * - hasNext()
 * 
 * - next()
 * 
 * @author Matt Thompson
 */

import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class ShallowFileIterator implements Iterator<File> {
  private File[] folderContents; // File array with the contents of specific directory
  private int nextIndex; // Tracks nextIndex in the folderContents array

  /**
   * Constructs a ShallowFileIterator, which splits the specified file's contents into the
   * folderContents array, and sorts it. Then, sets nextIndex to 0.
   * 
   * @param file The specific file/directory to iterate through
   * @throws FileNotFoundException if file/directory does not exist
   */
  public ShallowFileIterator(File file) throws FileNotFoundException {
    if (!file.exists()) { // If file does not exist, throws FileNotFoundException
      throw new FileNotFoundException("The specified file/directory does not exist.");
    }

    this.folderContents = file.listFiles();
    Arrays.sort(folderContents);
    this.nextIndex = 0;
  }

  /**
   * Checks if there is a next element in the folderContents array by comparing nextIndex to the
   * length of the folderContents array.
   * 
   * @return true if there is a next element, false otherwise
   */
  @Override
  public boolean hasNext() {
    if (this.nextIndex < this.folderContents.length) {
      return true;
    }

    return false;
  }

  /**
   * Checks if hasNext returns false, meaning there is no next index. If there is a next, returns
   * the file at the current index, and increments nextIndex.
   * 
   * @return the file at current index
   */
  @Override
  public File next() {
    if (!hasNext()) { // If there is no next element, throws NoSuchElementException
      throw new NoSuchElementException("There is no next element.");
    }

    File file = this.folderContents[nextIndex]; // File to be returned
    this.nextIndex++;
    return file;
  }
}
