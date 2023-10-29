package com.example.todoapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todoapplication.R
import com.example.todoapplication.databinding.FragmentAddTodoPopupBinding
import com.example.todoapplication.databinding.FragmentHomeBinding
import com.example.todoapplication.utills.ToDoData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText

/**
 * A [DialogFragment] subclass that allows the user to add or edit a to-do task.
 */
class AddTodoPopupFragment : DialogFragment() {

    private lateinit var binding: FragmentAddTodoPopupBinding
    private lateinit var listener: DialogNextBtnClickListener
    private var toDoData : ToDoData? = null

    /**
     * Sets the [DialogNextBtnClickListener] listener for this fragment.
     */
    fun setListener(listener: DialogNextBtnClickListener){
        this.listener = listener
    }

    companion object {
        const val TAG = "AddTodoPopupFragment"

        /**
         * Creates a new instance of [AddTodoPopupFragment] with task details.
         * @param taskId The ID of the task.
         * @param task The task description.
         */
        @JvmStatic
        fun newInstance (taskId: String , task: String) = AddTodoPopupFragment().apply {
            arguments = Bundle().apply {
                putString("taskId" , taskId)
                putString("task" , task)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddTodoPopupBinding.inflate(inflater , container , false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            toDoData = ToDoData(arguments?.getString("taskId").toString(),arguments?.getString("task").toString())
            binding.todoEt.setText(toDoData?.task)
        }
        registerEvents()
    }

    private fun registerEvents() {
        binding.todoNextBtn.setOnClickListener{
            val todoTask = binding.todoEt.text.toString()
            if (todoTask.isNotEmpty()) {

                if (toDoData == null) {
                    listener.onSaveTask(todoTask,binding.todoEt)
                } else {
                    toDoData?.task = todoTask
                    listener.onUpdateTask(toDoData!! , binding.todoEt)
                }

            } else {
                Toast.makeText(context , "Please type some task", Toast.LENGTH_SHORT).show()
            }
        }

        binding.todoClose.setOnClickListener {
            dismiss()
        }
    }

    /**
     * Interface for handling the save and update actions of a to-do task.
     */
    interface DialogNextBtnClickListener {
        /**
         * Called when the user wants to save a new to-do task.
         * @param todo The task description to be saved.
         * @param todoEt The input field for the task description.
         */
        fun onSaveTask(todo: String, todoEt: TextInputEditText)
        /**
         * Called when the user wants to update an existing to-do task.
         * @param toDoData The to-do task data to be updated.
         * @param todoEt The input field for the task description.
         */
        fun onUpdateTask(toDoData: ToDoData, todoEt: TextInputEditText)
    }

}