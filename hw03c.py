
#import singletonClass
class oneOnly:
	singleton = None
	def __new__(cls,*args,**kwargs):
		if not cls.singleton:
			cls.singleton = object.__new__(oneOnly)
		return cls.singleton
	def __init__(self,WC,UC,BC):
		self.words = WC
		self.unigram=UC
		self.bigram = BC
