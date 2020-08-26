git config --global pull.rebase false
git checkout master
git pull
cd ./Project2

mvn clean test -Dtest=**Insert testing class**

if [[ "$?" -ne 0 ]]
then
  echo The build failed!
  exit 100
else
  echo All tests pass! 
  #****have docker start somehow here****
fi
