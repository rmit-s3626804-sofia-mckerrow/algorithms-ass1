#!/bin/python3

import math
import random


# Testing 3 different densities for each scenario:
# 1: 1/3 Edge, 2/3 Vert Low density
# 2: 1/2 Edge, 1/2 Vert Medium density
# 3: 2/3 Edge, 1/3 Vert High density

tCount = 512



# First generate high and low density graphs

def high_dense_gen():
	
	pass

def med_dense_gen():

	pass

def low_dense_gen():
	
	pass




node_count = 4038
random.seed();



# Vertex Addition
# Edge Addition
def scenario_one_gen():
	
	amount = 256
	
	# Assuming facebook combined is imported, nodes up till 4038 are imported
	# so we can add from there
	


	# Vert
	for i in range(amount):
		print("AV " + str(node_count+i))
	# Edge
	for i in range(amount):
		print("AE " + str(node_count+i) + " " + str(random.randint(0, node_count+amount-1)))



scenario_one_gen()

# Testing shortest path
def scenario_two_gen():

	pass


# Testing edge removal
# Testing vertex removal
def scenario_three_gen():

	amount = 256


	# Vert
	for i in range(amount):
		print("RV " + "X")
	# Edge
	for i in range(amount):
		print("RE " + "X" + "X")
	
