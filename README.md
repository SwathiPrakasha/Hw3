Part1 : hw03a.py program clean up the dataset, make note of all the unigrams and bigrams in the file and stores in dictionary. 
Stores the file details such as word count, letter count, unigram count, bigram count into a pickle file. 

Part2 : hw03b.py program which implments kernighan's algorithm on following user mistakes
(1) del[x,y], the number of times that the characters xy (in the correct word) weretyped as x in the training set, 
(2) add[x,y], thenumber of times that x was typed as xy, 
(3) sub[x,y], the number of times that y was typed as x, 
(4) rev[x,y], the number of times that xy was typed as yx.
This program uses confusion matrix from kernighan's paper to calculate the probability each possible correct word. 

Part3 : hw3c.py contains code for Singleton Class Program

This program is tested for real world dataset of AP88 which contains around 35million words, 160000 unique words. 
