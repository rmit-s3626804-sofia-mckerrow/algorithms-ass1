#!/bin/bash

command="java -cp .:jopt-simple-5.0.2.jar:sample.jar  GraphTesterTimed"

scenarios=( "scenario1.txt" "scenario2.txt"  "scenario3.txt" )
dataSets=( "lowDense" "medDense" "highDense"  )

for scenario in "${scenarios[@]}"
do
	for set in "${dataSets[@]}"
	do
		echo "Running command: " "$command adjmat -f ../dataGenerator/$set/script.txt -s ../dataGenerator/$set/$scenario"
		echo
		$command adjmat -f ../dataGenerator/$set/script.txt -s ../dataGenerator/$set/$scenario
		echo
		echo


		echo "Running command: " "$command indmat -f ../dataGenerator/$set/script.txt -s ../dataGenerator/$set/$scenario"
		echo
		$command indmat -f ../dataGenerator/$set/script.txt -s ../dataGenerator/$set/$scenario
		echo
		echo
	done

done
