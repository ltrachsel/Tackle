package com.ltr.tackle.Data

import com.ltr.tackle.Data.Entities.Task
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor() {
    private val tasks = arrayListOf<Task>()



    init {
        val today = LocalDate.now()
        val tomorrow = today.plusDays(1)

        tasks.addAll(
            listOf(
                Task(generateUUID(), false, "Buy groceries", today, "Milk, Eggs, Bread"),
                Task(generateUUID(), false, "Go for a run", today, "Run 5km in the park"),
                Task(generateUUID(), false, "Finish coding task", today, "Complete the Compose feature"),
                Task(generateUUID(), false, "Read a book", today, "Read 20 pages of a novel"),
                Task(generateUUID(), false, "Cook dinner", today, "Try a new pasta recipe")
            )
        )

        tasks.addAll(
            listOf(
                Task(generateUUID(), false, "Team meeting", tomorrow, "Discuss project updates"),
                Task(generateUUID(), false, "Write blog post", tomorrow, "Topic: Jetpack Compose best practices"),
                Task(generateUUID(), false, "Call Mom", tomorrow, "Check in with her"),
                Task(generateUUID(), false, "Go to the gym", tomorrow, "Workout session at 6 PM"),
                Task(generateUUID(), false, "Plan weekend trip", tomorrow, "Look up hotels and attractions"),
                Task(generateUUID(), false, "Pay bills", tomorrow, "Electricity and internet bills due"),
                Task(generateUUID(), false, "Grocery shopping", tomorrow, "Stock up on fresh vegetables"),
                Task(generateUUID(), false, "Work on side project", tomorrow, "Build the authentication feature"),
                Task(generateUUID(), false, "Watch a documentary", tomorrow, "Learn about deep-sea exploration"),
                Task(generateUUID(), false, "Clean the house", tomorrow, "Vacuum and organize workspace")
            )
        )
    }

    fun getTasks(): List<Task> {
        return tasks.map { it.copy() }
    }

    fun getTasks(date: LocalDate): List<Task> {
        return tasks.filter { it.date == date }
            .map { it.copy() }
    }

    fun createTask(title: String, description: String, date: String) {
        val id = generateUUID()
        val today = LocalDate.now()

        val newTask = Task(id, false, title, today, description)
        tasks.add(newTask)
    }

    fun toggleTaskStatus(taskId: String): Task? {
        val task = tasks.find { it.id == taskId }

        if (task == null)
            return null

        task.completed = !task.completed

        return task.copy()
    }

    fun addTask(counter: Int): Task?{
        return null;
    }

    private fun generateUUID(): String{
        return UUID.randomUUID().toString()
    }
}