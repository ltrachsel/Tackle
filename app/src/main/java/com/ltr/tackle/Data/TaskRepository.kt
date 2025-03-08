package com.ltr.tackle.Data

import com.ltr.tackle.Data.Entities.Task
import com.ltr.tackle.Data.Entities.TaskGroup
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor() {
    private val tasks = arrayListOf<Task>()

    init {
        generateTestData()
    }

    /* Queries */

    fun getTasks(): List<Task> {
        return tasks.map { it.copy() }
    }

    fun getTasks(date: LocalDate): List<Task> {
        return tasks.filter { it.date == date }
            .map { it.copy() }
    }

    fun getTaskGroups(includeOutdatedTasks: Boolean): List<TaskGroup> {
        /*val filteredTasks = if (includeOutdatedTasks) {
            tasks
        } else {
            tasks.filter { it.date.isAfter(LocalDate.now()) }
        }*/

        val groupedByDate = tasks.groupBy { it.date }

        return groupedByDate.map { (date, tasks) ->
            TaskGroup(
                date = date,
                tasks = tasks.map { it.copy() }
            )
        }
    }

    /* Create */

    fun createTask(title: String, description: String, date: LocalDate) {
        val id = generateUUID()

        val newTask = Task(id, false, title, date, description)
        tasks.add(newTask)
    }

    fun toggleTaskStatus(taskId: String): Task? {
        val task = tasks.find { it.id == taskId }

        if (task == null)
            return null

        task.completed = !task.completed

        return task.copy()
    }

    private fun generateUUID(): String{
        return UUID.randomUUID().toString()
    }

    private fun generateTestData() {
        val startDate = LocalDate.now()

        val testTasks = listOf(
            "Buy groceries" to "Milk, Eggs, Bread",
            "Go for a run" to "Run 5km in the park",
            "Finish coding task" to "Complete the Compose feature",
            "Read a book" to "Read 20 pages of a novel",
            "Cook dinner" to "Try a new pasta recipe",
            "Team meeting" to "Discuss project updates",
            "Write blog post" to "Topic: Jetpack Compose best practices",
            "Call Mom" to "Check in with her",
            "Go to the gym" to "Workout session at 6 PM",
            "Plan weekend trip" to "Look up hotels and attractions",
            "Pay bills" to "Electricity and internet bills due",
            "Grocery shopping" to "Stock up on fresh vegetables",
            "Work on side project" to "Build the authentication feature",
            "Watch a documentary" to "Learn about deep-sea exploration",
            "Clean the house" to "Vacuum and organize workspace",
            "Organize workspace" to "Declutter and rearrange desk",
            "Practice meditation" to "10-minute mindfulness session",
            "Update resume" to "Add recent projects and skills",
            "Meal prep" to "Prepare meals for the upcoming week",
            "Catch up on emails" to "Respond to pending messages"
        )

        repeat(10) { dayOffset ->
            val date = startDate.plusDays(dayOffset.toLong())
            val dailyTasks = testTasks.shuffled().take(5)  // Randomly select 5 tasks per day
            tasks.addAll(
                dailyTasks.map { (title, description) ->
                    Task(generateUUID(), false, title, date, description)
                }
            )
        }
    }
}