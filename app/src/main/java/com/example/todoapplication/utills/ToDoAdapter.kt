package com.example.todoapplication.utills

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.databinding.EachTodoItemBinding

/**
 * [ToDoAdapter] is a custom RecyclerView adapter used for displaying a list of ToDoData items.
 *
 * @param list A mutable list of [ToDoData] items to be displayed in the RecyclerView.
 */
class ToDoAdapter(private val list:MutableList<ToDoData>) :
RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){
    private var listener : ToDoAdapterClicksInterface?= null

    /**
     * Sets the click listener for the adapter.
     *
     * @param listener An instance of [ToDoAdapterClicksInterface] to handle item click events.
     */
    fun setListener (listener:ToDoAdapterClicksInterface){
        this.listener=listener
    }

    /**
     * [ToDoViewHolder] is an inner class responsible for holding views of each item in the RecyclerView.
     *
     * @param binding The data binding object for each item in the RecyclerView.
     */
    inner class ToDoViewHolder(val binding: EachTodoItemBinding):RecyclerView.ViewHolder(binding.root)

    /**
     * Called when the RecyclerView needs a new [ToDoViewHolder] to represent an item.
     *
     * @param parent The parent view group in which the new [ToDoViewHolder] will be added.
     * @param viewType The type of view to be created (not used in this case).
     * @return A new instance of [ToDoViewHolder] for an item view.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = EachTodoItemBinding.inflate(LayoutInflater.from(parent.context),parent ,false)
        return ToDoViewHolder(binding)
    }

    /**
     * Returns the total number of items in the dataset.
     *
     * @return The number of items in the [list].
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * Interface for handling click events on items in the [ToDoAdapter].
     */
    interface ToDoAdapterClicksInterface{
        /**
         * Called when the delete task button is clicked for a [ToDoData] item.
         *
         * @param toDoData The [ToDoData] item for which the delete button was clicked.
         */
        fun onDeleteTaskBtnClick(toDoData: ToDoData)

        /**
         * Called when the edit task button is clicked for a [ToDoData] item.
         *
         * @param toDoData The [ToDoData] item for which the edit button was clicked.
         */
        fun onEditTaskBtnClick(toDoData: ToDoData)
    }

    /**
     * Called by RecyclerView to display the data at a specified position.
     *
     * @param holder The [ToDoViewHolder] which should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                binding.todoTask.text = this.task

                binding.deleteTask.setOnClickListener {
                    listener?.onDeleteTaskBtnClick(this)
                }
                binding.editTask.setOnClickListener {
                    listener?.onEditTaskBtnClick(this)
                }
            }
        }
    }
}