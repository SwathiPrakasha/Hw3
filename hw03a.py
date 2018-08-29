from collections import defaultdict
from hw03c import oneOnly
import operator
import re
import time
import pickle

'''this function takes a filename as input, reads the contents in the file,
loads all the words, characters and bigrams to a dictionary along with their counts
and the dictionaries are sent to object of class in Hw03c.py and then the object is pickled. '''
def fileOpen(fileName):
	begin_time = time.perf_counter()
	begin_cpu  = time.process_time()
	inputFile = open(fileName)
	linecount =0
	unigram   = defaultdict(int)
	bigram    = defaultdict(int)
	wordcount = defaultdict(int)
	newwords =[]

	for line in inputFile:
		linecount += 1
		line = line.lower() 							# Conversion to lowercase
		line = re.sub(r'[^a-zA-Z]',' ', line)        	                        #Removal of Non-ASCII characters
		line = line.split(' ',1)[1]						#removal of 1st word of line, which is ID a non-word
		words = line.split()							#spliting the words by space
		words.sort()

		#loop to extract all the words, letters and unograms to a dictionary witht their occurance
		for word in words:
			word = '<'+word+'>'
			wordcount[word] += 1

			for letter in word:
				unigram[letter] +=1

			for i in range(len(word)-1):
				letters = word[i] + word[i+1]
				bigram[letters] += 1

	inputFile.close()
	end_time = time.perf_counter()
	end_cpu  = time.process_time()

	print('{:35}{:10.4}{:10}{:10.4}'.format("Start at elapsed time",float(begin_time), ", cpu time ", float(begin_cpu)))
	print('{:35}{:10.4}{:10}{:10.4}'.format("finish reading at elapsed time", float(end_time), ", cpu time ", float(end_cpu)))
	print('{:35}{:10.4}{:10}{:10.4}'.format("Total elapsed time",float(end_time-begin_time), ",	cpu time",float(end_cpu-begin_cpu)))
	print()

	print('{:35}{:15}'.format("Number of lines: ", linecount))
	print('{:35}{:15}'.format("Number of words: ", sum(wordcount.values())))
	print('{:35}{:15}'.format("Number of unique words: ",len (wordcount)))
	print()


	print("The 10 top frequency words are:")
	ascWords = sorted(wordcount.items() , key = operator.itemgetter(1), reverse = True)
	count = 0
	for word in ascWords:
		if count >= 10:
			break
		count +=1
		print('{:20}{:5}'.format(word[0], word[1]))

	ascLetter = sorted(unigram.keys())
	print("\nUnigram Counts: ")
	for letter in ascLetter:
		print('{:5}{:10}'.format(letter, unigram[letter]))

	print("\nBigram Counts: ")
	ascLetter2 = sorted(bigram.keys())
	for letter in ascLetter2:
		print('{:5}{:>10}'.format(letter, bigram[letter]))

	#creating the singleton object
	obj =oneOnly(wordcount, unigram, bigram)

	#serializing the object created above through pickle
	fileObject = open("C:/Users/swath/Untitled Folder/Hw3/pickled_data.dat", 'wb')
	pickle.dump(obj,fileObject)
	fileObject.close()

import sys
if len(sys.argv)<2:
    print("Enter a file name as input")
else:
    fileOpen(sys.argv[1])
