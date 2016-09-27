#!/bin/bash
mkdir -p lecture$1
find ../../master/CSULA-CS3220-Fall2016/ -name "lecture$1-*" | \
xargs -i cp -ua {}/slides/. lecture$1

git pull
git add .
git commit -m "updated slides for lecture$1"
git push -u origin gh-pages
