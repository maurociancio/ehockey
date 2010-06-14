#!/bin/bash

echo $#
if [ $# -ne 4 ];
then
	echo usage: create_jugadores.sh club_id base_ficha upper_ficha nombre_club
	exit 1
fi

string=''

for ficha in $( seq $2 $3 );
do
	echo $ficha
	sql="insert into jugadores (ficha, apellido, nombre, tipodocumento, numerodocumento, fechanacimiento, activo, club_id, division_id, sector_id) values ($ficha, 'Jugador', '$4 ($ficha)', 'DNI', $1$ficha, '1900-01-01', TRUE, $1, 1, 1);"
	string=$string$sql
done

echo $string
psql --username ehockey -h localhost ehockey <<< $string
