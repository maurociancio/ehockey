#!/bin/bash

if [ $# -ne 1 ]
then
	echo 'usage:' $0 ticket-number
	exit 1
fi


./search_tickets_impl.sh $1 | sort | uniq
