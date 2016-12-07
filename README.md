# WordsInFiles

Duke University: Java Programming: Arrays, Lists, and Structured Data

Auther: rray89

Date Uploaded: Dec 7, 2016

Week 2: GladLibs: Stories from Templates

Description: Write a program to determine which words occur in the greatest number of files, and for each word, which files they occur in.

For example, consider you are given the four files: brief1.txt, brief2.txt, brief3.txt, and brief4.txt.

brief1.txt is:

cats are funny and cute

brief2.txt is:

dogs are silly

brief3.txt is:

love animals cats and dogs

brief4.txt is:

love birds and cats

The greatest number of files a word appears in is three, and there are two such words: “cats” and “and”.

“cats” appears in the files: brief1.txt, brief3.txt, brief4.txt

“and” appears in the files: brief1.txt, brief3.txt, brief4.txt

To solve this problem, you will create a map of words to the names of files they are in. That is, you will map a String to an ArrayList of Strings. Then you can determine which ArrayList value is the largest (has the most filenames) and its key is thus, a word that is in the most number of files.

Classes: 
  - WordsInFile
