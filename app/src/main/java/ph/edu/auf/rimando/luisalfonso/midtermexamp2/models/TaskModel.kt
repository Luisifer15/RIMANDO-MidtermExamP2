package ph.edu.auf.rimando.luisalfonso.midtermexamp2.models

import java.time.LocalDateTime

data class TaskModel(
    var taskId: Int,
    var taskDesc: String,
    var taskDate: LocalDateTime = LocalDateTime.now()
)
