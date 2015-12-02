#!/bin/bash

echo Dropping database...
mysql -uroot -pPizza1 -e "DROP DATABASE Crisis"
echo Recreating blank database...
mysql -uroot -pPizza1 -e "CREATE DATABASE Crisis"
echo Done.
