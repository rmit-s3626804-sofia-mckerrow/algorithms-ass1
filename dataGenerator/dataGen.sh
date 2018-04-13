#!/bin/bash

command="python dataGenerator.py "


echo "Generating density set scripts..."

$command l > lowDense/script.txt
$command m > medDense/script.txt
$command h > highDense/script.txt

echo "Done."
echo "Generating scenario scripts..."

$command 1 2048 > medDense/scenario1.txt 
$command 2 2048 > medDense/scenario2.txt 
$command 3 2048 > medDense/scenario3.txt 

$command 1 682 > highDense/scenario1.txt 
$command 2 682 > highDense/scenario2.txt 
$command 3 682 > highDense/scenario3.txt 

$command 1 1364 > lowDense/scenario1.txt 
$command 2 1364 > lowDense/scenario2.txt 
$command 3 1364 > lowDense/scenario3.txt 

echo "Done."
