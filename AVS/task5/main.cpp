#include <iostream>
#include <thread>
#include <mutex>
#include <vector>
#include <chrono>
#include <string>
#include <random>
#include <utility>

using std::function;
using std::thread;
using std::vector;
using std::string;
using std::cin;
using std::cout;
using std::endl;
using std::pair;

std::mutex locker;

std::random_device rd;
std::mt19937 gen(rd());

vector<int> studentsTasks;
vector<int> studentsMarks;
vector<thread> students;

vector<pair<int, int>> completedStudentsTasks;

int getRandomWorkTime() {
    std::uniform_int_distribution<> distribution(500, 2000);
    return distribution(gen);
}

int getRandomTasksCompleted() {
    std::uniform_int_distribution<> distribution(0, 6);
    return distribution(gen);
}

int getRandomTaskNumber() {
    std::uniform_int_distribution<> distribution(10, 100);
    return distribution(gen);
}

void printStudentNumber(int number) {
    cout << "[Student number: " << number << "]  ";
}

void sendAnswerToTeacher(int taskCount, int studentNumber) {
    locker.lock();
    completedStudentsTasks.emplace_back(studentNumber, taskCount);
    locker.unlock();
}

void studentAction(int studentNumber) {
    printStudentNumber(studentNumber);
    cout << "Hello! I start waiting my task!" << endl;

    while (studentsTasks[studentNumber] == -1) {
        continue;
    }

    const int taskNumber = studentsTasks[studentNumber];
    printStudentNumber(studentNumber);
    cout << "I received task number " << taskNumber << endl;
    printStudentNumber(studentNumber);
    cout << "Started working on my task " << taskNumber << endl;

    int workTime = getRandomWorkTime();
    std::this_thread::sleep_for(std::chrono::milliseconds(workTime));

    int tasksCompleted = getRandomTasksCompleted();
    printStudentNumber(studentNumber);
    cout << "I completed my task!" << endl;
    printStudentNumber(studentNumber);
    cout << "Waiting for my turn!" << endl;
    sendAnswerToTeacher(tasksCompleted, studentNumber);

    while (studentsMarks[studentNumber] == -1) {
        continue;
    }

    int mark = studentsMarks[studentNumber];
    printStudentNumber(studentNumber);
    cout << "I receive my mark! Mark: " << mark << endl;

    printStudentNumber(studentNumber);
    cout << "I go home..." << endl;
}

void launchStudents(int count) {
    for (int i = 0; i < count; i++) {
        students[i].join();
    }
}

void printTeacherPrefix() {
    cout << "[Teacher] ";
}

int getStudentMark(int completedTasks) {
    if (completedTasks < 1) {
        return 1;
    }
    if (completedTasks < 2) {
        return 2;
    }
    if (completedTasks < 3) {
        return 3;
    }
    if (completedTasks < 4) {
        return 4;
    }
    return 5;
}

void startTeacherAction(int studentsCount) {
    printTeacherPrefix();
    cout << "Start carry tasks" << endl;

    for (int i = 0; i < studentsCount; i++) {
        int tasksNumber = getRandomTaskNumber();
        studentsTasks[i] = tasksNumber;
    }

    printTeacherPrefix();
    cout << "Start waiting students completed tasks" << endl;

    for (int i = 0; i < studentsCount; i++) {
        while (completedStudentsTasks[i].first == -1) {
            continue;
        }
        int studentNumber = completedStudentsTasks[i].first;
        int completedTasks = completedStudentsTasks[i].second;

        int studentMark = getStudentMark(completedTasks);
        studentsMarks[studentNumber] = studentMark;
    }

    printTeacherPrefix();
    cout << "Finished checking tasks" << endl;

    printTeacherPrefix();
    cout << "Go home..." << endl;
}

void initStudents(const int& count) {
    studentsTasks = vector<int>(count, -1);
    studentsMarks = vector<int>(count, -1);
    completedStudentsTasks = vector<pair<int, int>>(count, std::make_pair(-1, -1));

    for (int i = 0; i < count; i++) {
        students[i] = thread(studentAction, i);
    }

    launchStudents(count);

    startTeacherAction(count);
}

void start(const int& numberOfStudents) {
    initStudents(numberOfStudents);
}

int main() {
    start(5);

    return 0;
}
