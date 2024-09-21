package com.example.flashcardquizapp.models

class SQLiteModels(var id : String = " ",
    var questions : String = " ",
    var answer : String = " ") {
    constructor(questions: String) : this()
}