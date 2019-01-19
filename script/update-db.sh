#!/bin/bash
##############################################

PROPERTIES_FILE="project.properties"

##############################################

echo "Configuration start..."
echo

file=$PROPERTIES_FILE
while read line; do
    if [ "$line" = "${line%#*}" -a "$line" ]; then
	words=(${line//=/ })
	if [ ${words[0]} = database.driver ]
	then
	  DATABASE_DRIVER=${words[1]}
	fi
	if [ ${words[0]} = database.url ]
	then
	  DATABASE_URL=${words[1]}
	fi
	if [ ${words[0]} = database.username ]
	then
	  DATABASE_USERNAME=${words[1]}
	fi
	if [ ${words[0]} = database.password ]
	then
	  DATABASE_PASSWORD=${words[1]}
	fi
    fi
done < $PROPERTIES_FILE

echo DRIVER: $DATABASE_DRIVER
echo URL: $DATABASE_URL
echo USERNAME: $DATABASE_USERNAME
echo PASSWORD: $DATABASE_PASSWORD

OPTIONS='-Ddatabase.driver='
OPTIONS+=$DATABASE_DRIVER
OPTIONS+=' -Ddatabase.url='
OPTIONS+=$DATABASE_URL
OPTIONS+=' -Ddatabase.username='
OPTIONS+=$DATABASE_USERNAME
OPTIONS+=' -Ddatabase.password='
OPTIONS+=$DATABASE_PASSWORD
OPTIONS+=' -Dmaven.test.skip=true'

echo
echo "Configuration end."
echo
echo "Starting maven..."

mvn --file ../quest-database/pom.xml -e clean package -Pdatabase $OPTIONS
