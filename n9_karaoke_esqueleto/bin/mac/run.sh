#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogot� - Colombia)
# Departamento de Ingenier�a de Sistemas y Computaci�n
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n9_karaoke
# Autor: Equipo Cupi2  2018-2
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

#SET CLASSPATH=

# ---------------------------------------------------------
# Ejecucion del programa
# ---------------------------------------------------------

cd ../..
java -ea -classpath ./lib/karaoke.jar uniandes.cupi2.karaoke.interfaz.InterfazKaraoke
cd bin/mac

stty echo