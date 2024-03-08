CPATH='.:../lib/hamcrest-core-1.3.jar:../lib/junit-4.13.2.jar'

rm -rf student-submission
git clone $1 student-submission 2> ta-output.txt

rm -rf grading-a

mkdir grading-area


echo 'Finished cloning'


# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests

if [[ -f ./student-submission/ListExamples.java ]]
then
    echo "File found."
else 
    echo "File not found."
    exit 1
fi 


cp TestListExamples.java grading-area
cp student-submission/ListExamples.java grading-area
cp -r lib grading-area

cd grading-area
javac -cp $CPATH *.java

if [[ $? -ne 0 ]]
then
    echo "Program failed to compile, see compile error above"
    exit 1
fi

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > junit-output.txt

if grep -q "FAILURES" junit-output.txt
then 
    lastline=$(cat junit-output.txt | tail -n 2 | head -n 1)
    tests=$(echo $lastline | awk -F'[, ]' '{print $3}')
    failures=$(echo $lastline | awk -F'[, ]' '{print $6}')
    successess=$((tests - failures))

    echo "Your score is $successess / $tests"
else 
    lastline=$(cat junit-output.txt | tail -n 2 | head -n 1)
    echo $lastline
    tests=$(echo $lastline | awk -F'[ ()]' '{print $3}')
    echo $tests
    
    echo "Your score is $tests / $tests"
fi