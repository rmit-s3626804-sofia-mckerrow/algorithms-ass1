#!/bin/python3

import math
import random
import sys


# Testing 3 different densities for each scenario:
# 1: 1/3 Edge, 2/3 Vert Low density
# 2: 1/2 Edge, 1/2 Vert Medium density
# 3: 2/3 Edge, 1/3 Vert High density

tCount = 512



# First generate high and low density graphs


amount = 2048

def high_dense_gen():
	# Vert
	for i in range(amount/3 * 1):
		print("AV " + str(i))
	# Edge
	for i in range(amount/3 * 2):
		print("AE " + str(random.randint(0, amount/3)) + " " + str(random.randint(0, amount/3)))
	
	pass

def med_dense_gen():

	# Vert
	for i in range(amount):
		print("AV " + str(i))
	# Edge
	for i in range(amount):
		print("AE " + str(random.randint(0, amount)) + " " + str(random.randint(0, amount)))
	pass

def low_dense_gen():
	# Vert
	for i in range(amount/3 * 2):
		print("AV " + str(i))
	# Edge
	for i in range(amount/3 * 1):
		print("AE " + str(random.randint(0, amount/3 * 2)) + " " + str(random.randint(0, amount/3 * 2)))
	pass




node_count = -100
random.seed();



def get_node_count():
	global node_count
	if len(sys.argv) is not 3:
		print("Please specify a node count on the cli...")
		exit()
	node_count = int(sys.argv[2])

	pass


# Vertex Addition
# Edge Addition
def scenario_one_gen():
	

	get_node_count()
	


	# Vert
	for i in range(amount):
		print("AV " + str(node_count+i))
	# Edge
	for i in range(amount):
		print("AE " + str(random.randint(0, node_count+amount-1)) + " " + str(random.randint(0, node_count+amount-1)))




# Testing shortest path
def scenario_two_gen():

	get_node_count()
	
	for i in range(amount):
		# Neighbours
		print("N " +  str(random.randint(0, node_count-1)))
		# Shortest Distance
		print("S " + str(random.randint(0, node_count-1)) + " " + str(random.randint(0, node_count-1)))
	
	pass


# Testing edge removal
# Testing vertex removal
def scenario_three_gen():

	get_node_count()


	# Remove edges first in case we remove vertex then try remove an edge of it later

	# Edge
	for i in range(amount):
		print("RE " + str(random.randint(0, node_count-1)) + " " + str(random.randint(0, node_count-1)))
	# Vert
	for i in range(amount):
		print("RV " + str(node_count+i))



# Basic CLI input...
if len(sys.argv) < 2:
	print("Usage: " + sys.argv[0] + "[l, m, h, 1, 2, 3]")
	exit()

if sys.argv[1] is 'l':
	low_dense_gen()
elif sys.argv[1] is 'm':
	med_dense_gen()
elif sys.argv[1] is 'h':
	high_dense_gen()
elif sys.argv[1] is '1':
	scenario_one_gen()
elif sys.argv[1] is '2':
	scenario_two_gen()
elif sys.argv[1] is '3':
	scenario_three_gen()
else:
	print("Invalid input...")
