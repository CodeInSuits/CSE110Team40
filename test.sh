#!/bin/bash
# run as "./test.sh PYTHON_SCRIPT"
# require "Test Cases.txt" file in the same directory
input=$1
if [[ ! -a $input ]]
then
    echo "usage: ./$0 PYTHON_SCRIPT"
    exit
fi
while read line
do
    if [[ -z $line || ${line:0:1} == "#" ]]
    then 
        continue
    else
		echo
        echo "Input:"
        echo " $line"
        echo "Output: "
		echo $line | python $input
    fi
done < "Test Cases.txt"
