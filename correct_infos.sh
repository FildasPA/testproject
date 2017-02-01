#!/bin/sh

git filter-branch -f --env-filter '
OLD_EMAIL="fildas.pa3@gmail.com"
CORRECT_NAME="Julien Boge"
CORRECT_EMAIL="julien.boge@alumni.univ-avignon.fr"
if [ "$GIT_COMMITTER_EMAIL" = "$OLD_EMAIL" ]
then
    export GIT_COMMITTER_NAME="$CORRECT_NAME"
    export GIT_COMMITTER_EMAIL="$CORRECT_EMAIL"
fi
if [ "$GIT_AUTHOR_EMAIL" = "$OLD_EMAIL" ]
then
    export GIT_AUTHOR_NAME="$CORRECT_NAME"
    export GIT_AUTHOR_EMAIL="$CORRECT_EMAIL"
fi
' --tag-name-filter cat -- --branches --tags TVS..threads
