#!/bin/bash

for commit in $(git log --format=%H)
do
	git log -n1 "$commit" | grep "#$1\([ )].*\)\?$" &>/dev/null
	ret=$?
	if [ $ret -eq 0 ]
	then
		git log -n1 --oneline --name-only $commit | sed "1d"
	fi
#	echo "#$1\( .*\)\?$"
#	echo $commit
done
